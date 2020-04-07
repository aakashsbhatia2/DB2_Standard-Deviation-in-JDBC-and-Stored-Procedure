CREATE PROCEDURE standard_deviation(OUT std_dev DECFLOAT)
LANGUAGE SQL
BEGIN
	DECLARE SQLSTATE CHAR(5) DEFAULT '00000';
	DECLARE p_sum DECFLOAT;
	DECLARE p_sal DECFLOAT;
	DECLARE p_sum_sq DECFLOAT;
	DECLARE mean DECFLOAT;
	DECLARE mean_sq DECFLOAT;
	DECLARE count DECFLOAT;
	DECLARE variance DECFLOAT;
	DECLARE c CURSOR FOR SELECT SALARY FROM EMPLOYEE;
	SET p_sum = 0;
	SET p_sum_sq = 0;
	SET count = 0;
	SET mean = 0;
	SET mean_sq = 0;
	SET variance = 0;
	OPEN c;
		FETCH FROM c INTO p_sal;
		WHILE(SQLSTATE = '00000') DO
			SET count = count + 1;
			SET p_sum_sq = p_sum_sq + (p_sal*p_sal);
			SET p_sum = p_sum + p_sal;
			FETCH FROM c INTO p_sal;
		END WHILE;
	CLOSE c;
	SET mean = p_sum/count;
	SET mean_sq = mean*mean;
	SET variance = (p_sum_sq/count) - mean_sq;
	SET std_dev = ROUND(SQRT(variance),2);
END
@

CALL standard_deviation(?)
@
DROP procedure standard_deviation
@
