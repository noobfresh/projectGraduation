-- ALTER TABLE od20170901 ADD PRIMARY KEY (START_ID, END_ID);
-- ALTER TABLE od20170901 ADD INDEX ALL(TICKET_ID, START_TIME, END_TIME, START_ID, END_ID);

SELECT * , END_ID - START_ID FROM od20170901 WHERE TICKET_ID = '000020451       '

