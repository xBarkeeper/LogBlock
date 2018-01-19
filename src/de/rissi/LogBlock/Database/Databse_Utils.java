package de.rissi.LogBlock.Database;

import java.sql.Connection;
import java.sql.Statement;

import de.rissi.LogBlock.Main.LogBlock_Values;

public class Databse_Utils
{

	public static Connection	connection;
	public static Statement		statement;
	public static MySQL			MySql	= new MySQL(LogBlock_Values.DATABASE_IP, LogBlock_Values.DATABASE_PORT,
			LogBlock_Values.DATABASE_USERNAME, LogBlock_Values.DATABASE_USERPASSWORD);

	//Setup the Database Connection
	
	public static void setup(String createDB, String[] createTable) {
		try
		{
			connection = MySql.openConnection();
			statement = connection.createStatement();

			statement.execute(createDB);
			for (int i = 0; i < createTable.length; i++)
			{
				statement.execute(createTable[i]);
			}

			connection.close();

		} catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

	//Method used to scribe in Database
	
	public static void scribe(String executeCommand) {

		try
		{
			connection = MySql.openConnection();
			statement = connection.createStatement();

			statement.executeUpdate(executeCommand);

			connection.close();
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}

	}

}
