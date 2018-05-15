-- 十分钟的数据，先完全扫一遍，
-- 补十分钟的数据，
-- 第一部分取出
SELECT
	START_STATION, END_STATION, DURATION
FROM
	`avgtime201709`
WHERE
	DURATION = 0;

-- 第二部分，直接去前后半小时表中取，
-- 前3张表和后三张表，需要再写个decrement函数，
-- 边界判断，表是否存在，or因为时间范围是规定的，
-- 可以在increment和decrement函数中，加入这个边界常量提示。
SELECT
	DURATION
FROM 
	`avgtime201709`
WHERE START_STATION = '' 
AND END_STATION = '';

-- 更新到对应十分钟表的对应记录
UPDATE `avgtime201709`
SET DURATION = 0;

-- 全部扫描完之后，
-- 再扫一次，把所有剩余为0的数据和天表数据对应更新。
UPDATE `avgtime201709`
SET DURATION = 0;
