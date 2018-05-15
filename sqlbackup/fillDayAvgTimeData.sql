-- 循环5次，我取出的五天数据
-- 补全天数据，
-- 第一部分取出
SELECT
	START_STATION, END_STATION, DURATION
FROM
	`avgtime201709`
WHERE
	DURATION = 0;

-- 第二部分，直接去月表中取
SELECT
	DURATION
FROM 
	`avgtime201709`
WHERE START_STATION = '' 
AND END_STATION = '';

-- 更新到天表的对应记录
UPDATE `avgtime201709`
SET DURATION = 0;