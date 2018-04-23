select tableA.TICKET_ID as ticketId, tableA.TXN_DATE as txnDate,tableA.TXN_TIME as startTime,
 tableB.TXN_TIME as endTime,tableA.TXN_STATION_ID as startStation, tableB.TXN_STATION_ID as endStation 
from graduationproject.`20170901` as tableA inner join graduationproject.`20170901` as tableB on 
tableA.TICKET_ID = tableB.TICKET_ID and tableB.TXN_TIME - tableA.TXN_TIME < 30000 and tableA.TRANS_CODE = '21' 
and tableB.TRANS_CODE = '22' and tableA.TXN_STATION_ID <> tableB.TXN_STATION_ID LIMIT 10000;