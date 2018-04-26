# projectGraduation
Personal 毕设相关资料

一些当前思路：
可选择方案
平均旅程时间预测
（可选择出发时间）推荐最佳出发时间
建议出行时间
出行成本
为旅客服务
拥挤程度
基础数据准备好   站点之间数据结构想好
历史同期思考（周为周期）


该app核心
为旅客推荐基于出行时间的出行路线（包含轨道交通以及公交交通），并为乘客预估旅程时间，并比较相邻时间比较出行成本，推荐较优出行时间。

首先计算任务:站点与站点之间的平均旅程时间（轨道交通（通过od数据计算，平均旅程时间只算列车站点运行时间，其中包含了等待时间的平均），公交交通（路面拥挤问题，通过gps定位数据分析每个时间段内，站点间旅程时间））。 然后 之后的预测？根据历史数据 推今天的出行时间

数据结构定义:id，direction，duration，distance，isBus，起点，终点，起点经过的线路，终点经过的线路，时间段（区分不同时间，所需要时间），

计算数据id，direction，startTime，endTime，distance（需单独计算，Or不用计算），isBus

平均旅程数据 需与历史同期数据比较

//数据处理框架，知识预备
https://www.cnblogs.com/misswangxing/p/7903595.html


转换处理思路：
将csv数据（5g）导入mysql，通过sql语句进行匹配计算，生成匹配好的OD数据(这个方法太慢了目前)
索引：id_time_transcode_station

sql 生成OD 语句：
select tableA.TICKET_ID as ticketId, tableA.TXN_DATE as txnDate,tableA.TXN_TIME as startTime, tableB.TXN_TIME as endTime,tableA.TXN_STATION_ID as startStation, tableB.TXN_STATION_ID as endStation from graduationproject.`20170930` as tableA inner join graduationproject.`20170930` as tableB on tableA.TICKET_ID = tableB.TICKET_ID and tableA.TXN_TIME < tableB.TXN_TIME and tableA.TRANS_CODE = '21' and tableB.TRANS_CODE = '22' and tableA.txn_station_id <> tableB.txn_station_id
目前还有点慢
上述查询出了个问题，由于字段类型都是varchar，那第二个条件就是去了意义，
该sql并没有去找离他最近的记录。哎
连接表时，并没有按最近距离原则匹配OD，导致过滤出错误数据，
少判断了一个OD票的类型，发现存在不同类型的票，但是存在相同的id（这算小部分了）

更换存储引擎，查询速度爆表，但还是挺久的。还有加速方法，再细分表，使表的粒度更小

原始数据表结构：如下
ticker_id   txn_date    txn_time   txn_station    ticket_type   trans_code   txn_amt
卡编号       刷卡日期    刷卡时间    刷卡站点        卡类型         进出站       

（ps.应该自己加个自增主键的


当前情况，已经按天，计算出OD数据，但暂且不保证脏数据是否存在。

next，
计算出我想要的站点间数据，设计算法。

其实根本没必要将过滤数据导出到csv再重新导入数据库，中间过程有点浪费时间


当前情况（2018.4.18）：
处理了一天的OD数据（伪正确），欠缺正确的OD脏数据过滤，先利用od20170901表数据，进行以天为单位的站点平均旅程时间计算（先忽略转线问题），小时级的有个最简单的思路，以出发时间划分数据，（暂时忽略进出站时间，其实感觉忽略与不忽略没有问题，将进出站时间分摊到平均旅程时间中，仿佛也行，又好像不太行）

建立数据结构（之前建立好的）->
根据数据结构建立相应表->
先从od数据中取出出发站点和目的站点只相差正负1的数据，先将其时间计算，
并填入表中，若原来表中有数据，则将原来数据取出，进行求均值->
然后求正负差为2的，3的，4的->
转线问题(为未解决)->  


突然意识到有特殊情况，有些线有延长线。。。
//站点记录
线路：
56
最小站点： 02    最大站点： 06
ps：5601 = 0620(礼嘉)


线路：
06
最小站点： 01    最大站点： 28
ps: 换成点 ： 0620 = 5601（礼嘉） ， 0612 = 0322（红旗河沟），0606
 = 0102（小十字） 

线路：
01
最小站点： 02	 最大站点： 24
ps:估计因为朝天门还未开启。
	换乘点 ： 0102 = 0606（小十字）， 0103 = 0201（较场口）， 0105 = 0318（两路口）， 0107 = 0209（大坪）

线路：
02
最小站点： 01 	 最大站点： 24
ps： 0225 = 0301 (鱼洞), 0206 = 0319（牛角沱），   0209 = 0107（大坪）， 

线路：
03	
最小站点： 01	 最大站点： 45
ps: （存在延长线 38->40->41->42->43->44->45）
	换乘点： 0301 = 0225 （鱼洞）， 0318 = 0105（两路口）， 0319 = 0206（牛角沱）， 0322 = 0612（红旗河沟）

线路：
暂时还没有十号线，因为数据是去年的

经个人人工统计，20170901 该天，一天的单条线上的OD超过50% ，约58.5%
先利用这些数据进行，日的平均旅程时间统计


com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Application was streaming results when the connection failed. Consider raising value of 'net_write_timeout' on the server.

当OD距离大于1时，计算站点之间的平均旅程时间的两种方法
1）取出该段OD，以1为窗口单位，保留窗口覆盖的OD区间，减去表中的平均数据，等到的值再与表中对应OD区间段作平均取值，以此滑动窗口。
2）取出该段OD，根据OD的长度为单位，统计类似站点与站点之间的平均时间，现将站点与站点之间的距离调大为2、3、4....然后保存下每一条记录（存在集合中）,然后通过原来的方法，为每条记录计算平均旅程时间，最终得出平均旅程时间后，再用（1）的方法


存在坐一个站竟然用了 2个小时

一段 + 一段  > 两段
是否应该专门计算上下车时间，然后减掉，令  尽可能   一段 + 一段 ≈ 两段


思路变更，总共123个站点，以123 和 123 分别为 x，y轴 1）计算出每两两组合的OD，的平均旅程时间，不光只计算站点与站点之间的，把每一种可能的乘车OD可能列举出来，并计算，大概就是123 * 123 - 123，除去 相同的起点终点，（另外还需要减去转乘点）。
然后还要做成 十分钟级的，目前的大概思路是，取出在该段时间的出发的 相同OD区间，来计算十分钟级的预估平均旅程时间，若不存在，则用前十分钟的相同OD区间数据来补。

2）另外一个分析点，分析每个站点的上下车时间，利用od = 1 计算和 od = 2 计算，以此来分析，每个站点的上下车时间是否和每个站点的体积大小的相关性。

轻轨运营时间 ： 6.30 - 23.30 中间有 102 个十分钟区块， 以周为规律的话， 102 * 7 = 714


74535 = 
87 + 171 + 321 + 3085 + 9329 + 12811 + 12581 + 10396 + 7480 + 4783 + 3126 + 2203 + 1687 + 1305 + 
最终做法，删除前百分之1和后百分之1的数据