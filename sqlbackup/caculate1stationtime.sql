DELETE
FROM
	od20170901
WHERE
	(START_ID, END_ID) IN (
		SELECT
			A.START_ID,
			A.END_ID
		FROM
			(
				SELECT
					START_ID,
					END_ID
				FROM
					od20170901
				WHERE
					ABS(
						CAST(
							START_STATION AS SIGNED INTEGER
						) - CAST(END_STATION AS SIGNED INTEGER)
					) = 1
				ORDER BY
					(
						(
							(SUBSTR(END_TIME FROM 1 FOR 2)) * 3600 + SUBSTR(END_TIME FROM 3 FOR 2) * 60 + SUBSTR(END_TIME FROM 5 FOR 2)
						) - (
							(
								SUBSTR(START_TIME FROM 1 FOR 2)
							) * 3600 + SUBSTR(START_TIME FROM 3 FOR 2) * 60 + SUBSTR(START_TIME FROM 5 FOR 2)
						)
					) ASC
				LIMIT 750
			) AS A
	)
OR (START_ID, END_ID) IN (
	SELECT
		B.START_ID,
		B.END_ID
	FROM
		(
			SELECT
				START_ID,
				END_ID
			FROM
				od20170901
			WHERE
				ABS(
					CAST(
						START_STATION AS SIGNED INTEGER
					) - CAST(END_STATION AS SIGNED INTEGER)
				) = 1
			ORDER BY
				(
					(
						(SUBSTR(END_TIME FROM 1 FOR 2)) * 3600 + SUBSTR(END_TIME FROM 3 FOR 2) * 60 + SUBSTR(END_TIME FROM 5 FOR 2)
					) - (
						(
							SUBSTR(START_TIME FROM 1 FOR 2)
						) * 3600 + SUBSTR(START_TIME FROM 3 FOR 2) * 60 + SUBSTR(START_TIME FROM 5 FOR 2)
					)
				) DESC
			LIMIT 750
		) AS B
)