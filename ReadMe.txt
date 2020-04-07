JDBC:

To run java code, use following command:
java  -cp "path2jdbcdriver.jar" SalaryStdDev databasename tablename login password

Stored Proc:

To run Stored Proc, use following command: 
db2 -td@ -f stddev.sql

The above command will load the stored proc, run it and drop it