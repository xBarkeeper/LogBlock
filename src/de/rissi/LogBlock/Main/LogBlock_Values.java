package de.rissi.LogBlock.Main;

public class LogBlock_Values
{

	// Values

	// Database
	public static String	DATABASE_IP						= "localhost";
	public static String	DATABASE_PORT					= "3306";
	public static String	DATABASE_USERNAME				= "DBUser";
	public static String	DATABASE_USERPASSWORD			= "Passwort1";
	public static String	DATABASE_DBNAME					= "logBlock";

	public static String	DATABASE_CREATEDB_COMMAND		= "CREATE DATABASE IF NOT EXISTS " + DATABASE_DBNAME;
	public static String[]	DATABASE_CREATETABLE_COMMAND	=
	{ 	"CREATE TABLE IF NOT EXISTS `" + DATABASE_DBNAME + "`.`Player` ("
			+ "`Player_UUID` VARCHAR(36) NOT NULL,"
			+ "`Player_Name` VARCHAR(20) NOT NULL,"
			+ "PRIMARY KEY (`Player_UUID`),"
			+ "UNIQUE INDEX `Player_UUID_UNIQUE` (`Player_UUID` ASC),"
			+ "UNIQUE INDEX `Player_Name_UNIQUE` (`Player_Name` ASC))",
		"CREATE TABLE IF NOT EXISTS `" + DATABASE_DBNAME + "`.`Block` ("
			+ "`Block_ID` INT NOT NULL AUTO_INCREMENT,"
			+ "`Block_Typ` VARCHAR(100) NOT NULL,"
			+ "`Block_Coords` VARCHAR(50) NOT NULL,"
			+ "`Block_BreakTime` DATETIME NOT NULL,"
			+ "`Player_UUID` VARCHAR(36) NOT NULL,"
			+ "PRIMARY KEY (`Block_ID`))"
	};

}
