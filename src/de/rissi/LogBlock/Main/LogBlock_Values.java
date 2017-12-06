package de.rissi.LogBlock.Main;

import java.util.HashMap;

public class LogBlock_Values
{

	// Values

		// Database
	
		public static final String					DATABASE_IP						= "localhost";
		public static final String					DATABASE_PORT					= "3306";
		public static final String					DATABASE_USERNAME				= "DBUser";
		public static final String					DATABASE_USERPASSWORD			= "Passwort1";
		public static final String					DATABASE_DBNAME					= "logBlock";
	
		public static final String					DATABASE_TABLE_BLOCK			= "block";
		public static final String					DATABASE_TABLE_PLAYER			= "player";

		public static final String					DATABASE_CREATEDB_COMMAND		= "CREATE DATABASE IF NOT EXISTS "
				+ DATABASE_DBNAME;
	
		public static final String					ROWNAMEBLOCK_ID					= "Block_ID";
		public static final String					ROWNAMEBLOCK_TYP				= "Block_Typ";
		public static final String					ROWNAMEBLOCK_COORDS				= "Block_Coords";
		public static final String					ROWNAMEBLOCK_BREAKTIME			= "Block_BreakTime";
		public static final String					ROWNAMEPLAYER_UUID				= "Player_UUID";
		
		public static final String[]	DATABASE_CREATETABLE_COMMAND	=
		{ 	"CREATE TABLE IF NOT EXISTS `" + DATABASE_DBNAME + "`.`Player` ("
				+ "`Player_UUID` VARCHAR(36) NOT NULL,"
				+ "`Player_Name` VARCHAR(20) NOT NULL,"
				+ "PRIMARY KEY (`Player_UUID`),"
				+ "UNIQUE INDEX `Player_UUID_UNIQUE` (`Player_UUID` ASC),"
				+ "UNIQUE INDEX `Player_Name_UNIQUE` (`Player_Name` ASC))",
			"CREATE TABLE IF NOT EXISTS `" + DATABASE_DBNAME + "`.`Block` ("
				+ "`" + ROWNAMEBLOCK_ID + "` INT NOT NULL AUTO_INCREMENT,"
				+ "`" + ROWNAMEBLOCK_TYP + "` VARCHAR(100) NOT NULL,"
				+ "`" + ROWNAMEBLOCK_COORDS + "` VARCHAR(50) NOT NULL,"
				+ "`" + ROWNAMEBLOCK_BREAKTIME + "` DATETIME NOT NULL,"
				+ "`" + ROWNAMEPLAYER_UUID + "` VARCHAR(36) NOT NULL,"
				+ "PRIMARY KEY (`Block_ID`))"
		};

		// Messages
		
		public static final String WRONGCOMMANDUSE = "Use \"/lb help\"";
		public static final String POSITION1SET = "Position 1 gesetzt";
		public static final String POSITION2SET = "Position 2 gesetzt";
		
		// Utils
		
		public static final String TIMEFORMAT = ("dd.MM.yyyy HH:mm:ss");
		public static HashMap<String, Integer[]> areaHashMap = new HashMap<String, Integer[]>();
}
