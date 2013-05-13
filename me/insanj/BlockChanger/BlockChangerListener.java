package me.insanj.BlockChanger;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlockChangerListener implements Listener
{
	
	public static BlockChanger plugin;
	
	public BlockChangerListener(BlockChanger instance)
	{
		plugin = instance;
	}
	
	public void onBlockDamage(BlockDamageEvent event)
	{
		if(plugin.enabled(event.getPlayer()))
			event.getBlock().setTypeId(plugin.id);
	}
	
}
