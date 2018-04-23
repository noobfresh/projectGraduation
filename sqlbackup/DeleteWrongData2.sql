DELETE
FROM
	od20170901
WHERE
	(START_ID, END_ID) IN (
		SELECT
			M.START_ID,
			M.END_ID
		FROM
			(
				SELECT
					G.START_ID,
					G.END_ID
				FROM
					od20170901 AS G
				WHERE
					(G.TICKET_ID, G.END_TIME) IN (
						SELECT
							B.TICKET_ID,
							B.END_TIME
						FROM
							od20170901 AS B
						GROUP BY
							B.TICKET_ID,
							B.END_TIME
						HAVING
							COUNT(B.TICKET_ID) > 1
					)
				AND (G.START_ID, G.END_ID) NOT IN (
					SELECT
						C.START_ID,
						C.END_ID
					FROM
						(
							SELECT
								A.TICKET_ID,
								A.START_ID,
								A.END_ID,
								(A.END_ID - A.START_ID) AS MINUS
							FROM
								od20170901 AS A
							WHERE
								(A.TICKET_ID, A.END_TIME) IN (
									SELECT
										B.TICKET_ID,
										B.END_TIME
									FROM
										od20170901 AS B
									GROUP BY
										B.TICKET_ID,
										B.END_TIME
									HAVING
										COUNT(B.TICKET_ID) > 1
								)
						) AS C
					WHERE
						C.MINUS = (
							SELECT
								MIN(D.MINUS)
							FROM
								(
									SELECT
										AA.TICKET_ID,
										AA.START_ID,
										AA.END_ID,
										(AA.END_ID - AA.START_ID) AS MINUS
									FROM
										od20170901 AS AA
									WHERE
										(AA.TICKET_ID, AA.END_TIME) IN (
											SELECT
												BB.TICKET_ID,
												BB.END_TIME
											FROM
												od20170901 AS BB
											GROUP BY
												BB.TICKET_ID,
												BB.END_TIME
											HAVING
												COUNT(BB.TICKET_ID) > 1
										)
								) AS D
							WHERE
								D.TICKET_ID = C.TICKET_ID
						)
				)
			) AS M
	)