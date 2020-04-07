import java.sql.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

@SuppressWarnings("unused")
class SalaryStdDev
{
	static
	{
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    public static void main (String argv[])
    {
    	int count = 0;
    	float std_dev = 0;
    	float sum = 0;
    	float sum_sq = 0;
    	float mean = 0;
    	float variance = 0;
    	float mean_sq = 0;
    	try
    	{
    		Connection con = null;
    		String url = "jdbc:db2://localhost:50000/" + argv[0];
    		if(argv.length == 0)
    		{
    			con = DriverManager.getConnection(url);
    		}
    		else if(argv.length == 4)
    		{
    			String userid = argv[2];
    			String password = argv[3];
    			con = DriverManager.getConnection(url, userid, password);
    		}
    		else
    		{
    			throw new Exception("\n Usage: java SalaryStdDev[Database Name, Table Name, username,password]\n");
    		}
    		Statement stmt = con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT SALARY FROM "+ argv[1]);
    		while(rs.next())
    		{
    			count+=1;
    			sum = (float) (sum + rs.getFloat(1));
    			sum_sq = (float) (sum_sq + Math.pow(rs.getFloat(1),2));
    		}

    		mean = sum/count;
    		mean_sq = mean*mean;
    		variance = (sum_sq/count)-mean_sq;
    		System.out.print("Standard Deviation of Salary: $ ");
    		System.out.format("%.2f",Math.sqrt(variance));
    		System.out.println();

    		rs.close();
    		stmt.close();
    		con.close();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
 }
