package me.insanj.BlockChanger;

import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

public class BlockChangerListener extends BlockListener
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
