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
	
		public static final String					DATABASE_TABLE_BLOCKBREAK		= DATABASE_DBNAME + ".block";
		public static final String					DATABASE_TABLE_PLAYER			= DATABASE_DBNAME + ".player";
		public static final String					DATABASE_TABLE_BLOCKCREATE		= DATABASE_DBNAME + ".player";

		public static final String					DATABASE_CREATEDB_COMMAND		= "CREATE DATABASE IF NOT EXISTS "
				+ DATABASE_DBNAME;
	
		public static final String					COLUMNNAME_BLOCK_ID				= "Block_ID";
		public static final String					COLUMNNAME_BLOCK_TYP			= "Block_Typ";
		public static final String					COLUMNNAME_BLOCK_COORDS			= "Block_Coords";
		public static final String					COLUMNNAME_BLOCK_EDITTIME		= "Block_EditTime";
		public static final String					COLUMNNAME_PLAYER_UUID			= "Player_UUID";
		public static final String					COLUMNNAME_PLAYER_NAME			= "Player_Name";
		
		public static final String[]	DATABASE_CREATETABLE_COMMAND	=
		{ 	"CREATE TABLE IF NOT EXISTS `" + DATABASE_TABLE_PLAYER + "` ("
				+ "`" + COLUMNNAME_PLAYER_UUID + "` VARCHAR(36) NOT NULL,"
				+ "`" + COLUMNNAME_PLAYER_NAME + "` VARCHAR(20) NOT NULL,"
				+ "PRIMARY KEY (`" + COLUMNNAME_PLAYER_UUID + "`),"
				+ "UNIQUE INDEX `" + COLUMNNAME_PLAYER_UUID + "_UNIQUE` (`" + COLUMNNAME_PLAYER_UUID + "` ASC),"
				+ "UNIQUE INDEX `" + COLUMNNAME_PLAYER_UUID + "_UNIQUE` (`" + COLUMNNAME_PLAYER_UUID + "` ASC))",
			"CREATE TABLE IF NOT EXISTS `" + DATABASE_TABLE_BLOCKBREAK + "` ("
				+ "`" + COLUMNNAME_BLOCK_ID + "` INT NOT NULL AUTO_INCREMENT,"
				+ "`" + COLUMNNAME_BLOCK_TYP + "` VARCHAR(100) NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_COORDS + "` VARCHAR(50) NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_EDITTIME + "` DATETIME NOT NULL,"
				+ "`" + COLUMNNAME_PLAYER_UUID + "` VARCHAR(36) NOT NULL,"
				+ "PRIMARY KEY (`" + COLUMNNAME_BLOCK_ID + "`))",
			"CREATE TABLE IF NOT EXISTS `" + DATABASE_TABLE_BLOCKCREATE + "` ("
				+ "`" + COLUMNNAME_BLOCK_ID + "` INT NOT NULL AUTO_INCREMENT,"
				+ "`" + COLUMNNAME_BLOCK_TYP + "` VARCHAR(100) NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_COORDS + "` VARCHAR(50) NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_EDITTIME + "` DATETIME NOT NULL,"
				+ "`" + COLUMNNAME_PLAYER_UUID + "` VARCHAR(36) NOT NULL,"
				+ "PRIMARY KEY (`" + COLUMNNAME_BLOCK_ID + "`))"
		};

		// Messages
		
		public static final String WRONGCOMMANDUSE = "Use \"/lb help\"";
		public static final String POSITION1SET = "Position 1 gesetzt";
		public static final String POSITION2SET = "Position 2 gesetzt";
		
		// Utils
	
		public static final String					TIMEFORMAT						= ("dd.MM.yyyy HH:mm:ss");
		public static final String					TIMEFORMAT_DATABASE				= ("yyyy-MM-dd HH:mm:ss");
		public static HashMap<String, Integer[]>	areaHashMap						= new HashMap<String, Integer[]>();
}
