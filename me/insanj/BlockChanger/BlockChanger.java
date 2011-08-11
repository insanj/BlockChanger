package me.insanj.BlockChanger;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockChanger extends JavaPlugin
{
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private final BlockChangerListener blockListener = new BlockChangerListener(this);
	public final ArrayList<Player> BlockChangerUsers = new ArrayList<Player>();
    public static PermissionHandler permissionHandler;
    
    public int id = 35;
	
	@Override
	public void onEnable()
	{
		log.info("[BlockChanger] has been enabled!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Event.Priority.Normal, this );
		setupPermissions();
	}
	
	@Override
	public void onDisable()
	{
		log.info("[BlockChanger] has been disabled!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("BlockChanger") && permissionsCheck(sender)){
			if(args.length > 0)
				id = Integer.parseInt(args[0]);
			else
				toggleBlockChanger(sender);
		}
		
		return true;
		
	}
	
	private void toggleBlockChanger(CommandSender sender)
	{
		if( !enabled((Player) sender) )
		{
			BlockChangerUsers.add((Player) sender);
			((Player) sender).sendMessage(ChatColor.BLUE + "BlockChanger has been enabled!");
		}
		
		else 
		{
			BlockChangerUsers.remove((Player) sender);
			((Player) sender).sendMessage(ChatColor.RED + "BlockChanged has been disabled!");
		}
	}
	
	public boolean enabled(Player player)
	{
		return BlockChangerUsers.contains(player);
	}
	
	boolean permissionsCheck(CommandSender sender){
		
		if( permissionHandler == null ){
			if( sender.isOp() )
				return true;
		}
		
		else{
			if( permissionHandler.has((Player) sender, "BlockChanger.use") )
					return true;
		}
		
		return false;
	}
	
	void setupPermissions(){
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

		if (permissionsPlugin == null) {
		    log.warning("[BlockChanger] permission system not detected, defaulting to OP.");
		    return;
		}

		permissionHandler = ((Permissions) permissionsPlugin).getHandler();
		log.info("[BlockChanger] Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}
	
}
