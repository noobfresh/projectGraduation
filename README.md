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
select tableA.TICKET_ID as ticketId, tableA.TXN_DATE as txnDate,tableA.TXN_TIME as startTime, tableB.TXN_TIME as endTime,tableA.TXN_STATION_ID as startStation, tableB.TXN_STATION_ID as endStation from graduationproject.`20170930` as tableA inner join graduationproject.`20170930` as tableB on tableA.TICKET_ID = tableB.TICKET_ID and tableA.TXN_TIME < tableB.TXN_TIME and tableA.TRANS_CODE = '21' and tableB.TRANS_CODE = '22'
目前还有点慢

原始数据表结构：如下
ticker_id   txn_date    txn_time   txn_station    ticket_type   trans_code   txn_amt
卡编号       刷卡日期    刷卡时间    刷卡站点        卡类型         进出站       
