-- 补全月数据，
-- 第一部分取出
SELECT
	START_STATION, END_STATION, DURATION
FROM
	`avgtime201709`
WHERE
	DURATION = 0;

-- 第二部分，取出前一站，后一站的时间,取平均，
-- 若只取出一部分，就直接用，
-- 暂时不讨论前一站和后一站都没有的情况
SELECT
	DURATION
FROM 
	`avgtime201709`
WHERE START_STATION = '' 
AND END_STATION = '';

-- 更新到月表的对应记录
UPDATE `avgtime201709`
SET DURATION = 0;

