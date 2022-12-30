package com.ebs.entity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.ebs.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseConnection {

	private int   port			 = 0;
	private String driverClass 	 = "oracle.jdbc.OracleDriver";
	private String baseConnect 	 = "jdbc:oracle:thin:";
	private String userId 	  	 = null;
	private String userPassword  = null;
	private String netAddress 	 = null;
	private String sid 		  	 = null;
	private String connectOption = null;
	private String connectDesc	 = null;
	private Connection connect 	 = null;


	// Constructor used to initialize the database connection
	// parameters from the file passed
	public DatabaseConnection(String parmFile) throws IOException
	{
		FileInputStream fileInputStream 	= null;
		InputStreamReader inputStreamReader 	= null;
		BufferedReader  bufferedReader 		= null;

		try{
			fileInputStream 	= new FileInputStream(parmFile);
			inputStreamReader 	= new InputStreamReader(fileInputStream);
			bufferedReader 		= new BufferedReader(inputStreamReader);
			userId			 	= bufferedReader.readLine();
			userPassword 		= bufferedReader.readLine();
			netAddress 			= bufferedReader.readLine();
			port 				= Integer.parseInt(bufferedReader.readLine());
			sid 				= bufferedReader.readLine();
		}
		catch(Exception e){
			throw new BusinessException("Failed to connect to Database", "Check credential");
		}
		finally
		{
			bufferedReader  	= null;
			inputStreamReader 	= null;
			fileInputStream 	= null;
			bufferedReader.close();
		}
	}



	// getConnectString
	public String getConnectString()
	{
		if (!connectOption.equals("opt2")) {
			return(baseConnect +
					userId + "/" +
					userPassword + "@" +
					netAddress + ":" +
					port+ ":" +
					sid);
		}
		else
			return(baseConnect +
					userId + "/" +
					userPassword + "@" +
					connectDesc );
	}

	// Open a connection if it does not exist otherwise return the
	// existing connection.
	public Connection open() throws Exception
	{
		// if there is no connection, get one.
		if (connect == null)
		{
			try
			{
				// load the jdbc driver
				Class.forName(driverClass);
				// connect to the database
				connect = DriverManager.getConnection(getConnectString());
			}
			catch(Exception ex)
			{
				connect = null;
				
				throw ex;
			}
		}
		return(connect);
	}

	// Close the current connection.
	public void close()
	{
		// if there is a connection close it.
		if (connect != null)
		{
			try
			{
				connect.close();
			}
			catch(Exception ex)
			{
				// we don't care about any error on a close
			}
			connect = null;
		}
	}

	// Method Description: SQL error/exception handling.
	public void systemSQLException(SQLException sqle)
	{
		String sqls = new String("SQLState = '"+sqle.getSQLState()+"'");
		String vsec = new String("Vendor Error Code = '"+sqle.getErrorCode()+"'");
		String erMsg = new String("SQLException = '"+sqle+"'; "+sqls+"; "+vsec+"");
		System.out.println("Oops! "+erMsg);
	}

	// Method Description: Standard error/exception handling.
	public void systemException(Exception e)
	{
		System.out.println("Exception in  Class:DBConnect "+e.toString());
	}

}
