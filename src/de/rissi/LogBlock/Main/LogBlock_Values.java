package de.rissi.LogBlock.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
		
		public static final String					WRONGCOMMANDUSE					= "Use \"/lb help\"";
		public static final String					POSITION1SET					= "Position 1 gesetzt";
		public static final String					POSITION2SET					= "Position 2 gesetzt";
		public static final String					ENTEREDITORMODE					= "§3Du bist nun im Editor Mode!";
		public static final String					LEAVEEDITOTMODE					= "§3Du bist nun nicht mehr im Editor Mode!";
		
		// Tools
		
		public static final Material				POSITIONTOOL_MATERIAL			= Material.WOOD_SPADE;
		public static final Material				BACKTOOL_MATERIAL				= Material.BARRIER;
		public static final Material				SETFIRSTPOSITIONTOOL_MATERIAL	= Material.LAVA_BUCKET;
		public static final Material				SETSECONDPOSITIONTOOL_MATERIAL	= Material.WATER_BUCKET;
		
		// Commands
	
		public static final String					MAINCOMMAND						= "LogBlock";
		public static final String					BLOCKBREAKHISTORYCOMMAND		= "history";
		public static final String					EDITMODECOMMAND					= "gui";
		public static final String					HELPCOMMAND						= "help";
		public static final String					RESETAREACOMMAND				= "reset";
		public static final String					SETAREACOMMAND					= "setpos";

		// Utils
		
		public static final String					TIMEFORMAT						= ("dd.MM.yyyy HH:mm:ss");
		public static final String					TIMEFORMAT_DATABASE				= ("yyyy-MM-dd HH:mm:ss");
		
		// HashMaps
		public static HashMap<String, Integer[]>	areaHashMap						= new HashMap<String, Integer[]>();
		public static HashMap<UUID, ItemStack[]>	inventoryContents				= new HashMap<UUID, ItemStack[]>();
		public static HashMap<UUID, ItemStack[]>	inventoryArmorContents			= new HashMap<UUID, ItemStack[]>();
		public static HashMap<UUID, Boolean>		inEditMode						= new HashMap<UUID, Boolean>();
		public static HashMap<UUID, Integer>		food							= new HashMap<UUID, Integer>();
		public static HashMap<UUID, Double>			health							= new HashMap<UUID, Double>();
		public static HashMap<UUID, Location>		location						= new HashMap<UUID, Location>();
		
	//Methods
		
		public static String getCurrentTimeStamp() {
		    SimpleDateFormat sdfDate = new SimpleDateFormat(TIMEFORMAT_DATABASE);
		    Date now = new Date();
		    String strDate = sdfDate.format(now);
		    return strDate;
		}
		
		public static boolean playerIsInEditMode(Player p) {
			if(LogBlock_Values.inEditMode.containsKey(p.getUniqueId()) && LogBlock_Values.inEditMode.get(p.getUniqueId()) == true || !(LogBlock_Values.inEditMode.containsKey(p.getUniqueId())) ) {
				return false;
			}
			return true;
		}
}
