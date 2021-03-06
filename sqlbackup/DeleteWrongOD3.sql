-- SELECT
-- 	TICKET_ID,
-- 	START_TIME,
-- 	END_TIME,
-- 	START_STATION,
-- 	END_STATION
-- FROM
-- 	od20170901
-- WHERE
-- 	ABS(
-- 		CAST(
-- 			START_STATION AS SIGNED INTEGER
-- 		) - CAST(END_STATION AS SIGNED INTEGER)
-- 	) = 1
DELETE
FROM
	od20170901
WHERE
-- 小十字
	(START_STATION = '0102' AND END_STATION = '0606')
OR (START_STATION = '0606' AND END_STATION = '0102')
-- 礼嘉
OR (START_STATION = '5601' AND END_STATION = '0620')
OR (START_STATION = '0620' AND END_STATION = '5601')
-- 红旗河沟
OR (START_STATION = '0612' AND END_STATION = '0322')
OR (START_STATION = '0322' AND END_STATION = '0612')
-- 较场口
OR (START_STATION = '0103' AND END_STATION = '0201')
OR (START_STATION = '0201' AND END_STATION = '0103')
-- 两路口
OR (START_STATION = '0105' AND END_STATION = '0318')
OR (START_STATION = '0318' AND END_STATION = '0105')
-- 大坪
OR (START_STATION = '0107' AND END_STATION = '0209')
OR (START_STATION = '0209' AND END_STATION = '0107')
-- 鱼洞
OR (START_STATION = '0301' AND END_STATION = '0225')
OR (START_STATION = '0225' AND END_STATION = '0301')
-- 牛角沱
OR (START_STATION = '0319' AND END_STATION = '0206')
OR (START_STATION = '0206' AND END_STATION = '0319')