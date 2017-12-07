package de.rissi.LogBlock.Main;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public class LogBlock_Values
{

	// Values

		// Database
	
		public static final String					DATABASE_IP						= "localhost";
		public static final String					DATABASE_PORT					= "3306";
		public static final String					DATABASE_USERNAME				= "DBUser";
		public static final String					DATABASE_USERPASSWORD			= "Passwort1";
		public static final String					DATABASE_DBNAME					= "logBlock";
	
		public static final String					DATABASE_TABLE_BLOCK		= "block";

		public static final String					DATABASE_CREATEDB_COMMAND		= "CREATE DATABASE IF NOT EXISTS "
				+ DATABASE_DBNAME;
	
		public static final String					COLUMNNAME_BLOCK_ID				= "Block_ID";
		public static final String					COLUMNNAME_BLOCK_TYP			= "Block_Typ";
		public static final String					COLUMNNAME_BLOCK_COORDS			= "Block_Coords";
		public static final String					COLUMNNAME_BLOCK_EDITTIME		= "Block_EditTime";
		public static final String					COLUMNNAME_BLOCK_ACTION			= "Block_Action";
		public static final String					COLUMNNAME_PLAYER_UUID			= "Player_UUID";
		
		public static final String					CREATE							= "create";
		public static final String					BREAK							= "break";
		
		public static final String[]	DATABASE_CREATETABLE_COMMAND	=
		{ 	"CREATE TABLE IF NOT EXISTS `" + DATABASE_DBNAME + "`.`" + DATABASE_TABLE_BLOCK + "` ("
				+ "`" + COLUMNNAME_BLOCK_ID + "` INT NOT NULL AUTO_INCREMENT,"
				+ "`" + COLUMNNAME_BLOCK_TYP + "` VARCHAR(100) NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_COORDS + "` VARCHAR(50) NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_EDITTIME + "` DATETIME NOT NULL,"
				+ "`" + COLUMNNAME_BLOCK_ACTION + "` VARCHAR(6) NOT NULL,"
				+ "`" + COLUMNNAME_PLAYER_UUID + "` VARCHAR(36) NOT NULL,"
				+ "PRIMARY KEY (`" + COLUMNNAME_BLOCK_ID + "`))",
		};

		// Messages
		
		public static final String WRONGCOMMANDUSE = "Use \"/lb help\"";
		public static final String POSITION1SET = "Position 1 gesetzt";
		public static final String POSITION2SET = "Position 2 gesetzt";
		
		// Utils
	
		public static final String					TIMEFORMAT						= ("dd.MM.yyyy HH:mm:ss");
		public static final String					TIMEFORMAT_DATABASE				= ("yyyy-MM-dd HH:mm:ss");
		public static HashMap<String, Integer[]>	areaHashMap						= new HashMap<String, Integer[]>();
		public static HashMap<String, ItemStack[]>	inventoryContents				= new HashMap<String, ItemStack[]>();
		public static HashMap<String, ItemStack[]>	inventoryArmorContents			= new HashMap<String, ItemStack[]>();
		public static HashMap<String, Boolean>		inEditMode						= new HashMap<String, Boolean>();
}
