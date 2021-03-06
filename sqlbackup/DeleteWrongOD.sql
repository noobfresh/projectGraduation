SELECT TICKET_ID
FROM
	od20170901
WHERE
	(
		TICKET_ID,
		START_TIME,
		END_TIME
	) IN (
		SELECT A.TICKET_ID, A.START_TIME, A.END_TIME FROM
		(SELECT
			C.TICKET_ID,
			C.START_TIME,
			MAX(C.END_TIME) AS END_TIME
		FROM
			od20170901 AS C
		GROUP BY
			C.TICKET_ID,
			C.START_TIME
		HAVING
			COUNT(*) > 1) AS A
	)