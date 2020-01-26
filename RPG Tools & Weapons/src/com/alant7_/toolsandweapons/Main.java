package com.alant7_.toolsandweapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	private static List<ProgressRequirement> reqs = new ArrayList<>();
	private static List<ItemProgressLore> lores = new ArrayList<>();
	
	@Override
	public void onEnable() {
		
		System.out.println("[RPG Tools & Weapons] Enabling...");
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		init();
		
	}
	
	private void init() {
		reqs.clear();
		lores.clear();
		
		reqs.add(new ProgressRequirement(Material.DIAMOND_PICKAXE, new int[] {
				1, 2, 3, 4
		}, new int[] {
				100, 200, 300, 400
		}));
		
		lores.add(new ItemProgressLore(Material.DIAMOND_PICKAXE));
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack s = p.getInventory().getItemInMainHand();
		
		ProgressibleItem pi = new ProgressibleItem(s);
		pi.addExp(10);
		
		if (pi.hasLeveledUp()) {
			p.sendMessage("Your " + pi.item().getItemMeta().getLocalizedName() + " has leveled up!");
		}
		
		pi.updateItem();
		
		p.getInventory().setItemInMainHand(pi.item());
		p.sendMessage("Level: " + pi.exp());
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("rpgtoolsandweapons.admin")) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
				
			});
			p.sendMessage("&cRPG Tools & Weapons &8&l>> &7New version is available. Please update for new features and bugfixes");
		}
	}
	
	public static ItemProgressLore lore(Material m) {
		for (ItemProgressLore l : lores) {
			System.out.println("Mat1: " + l.material() + ", Mat2: " + m);
			if (l.material().equals(m))
				return l;
		}
		return null;
	}
	
	public static ProgressRequirement requirement(Material m) {
		for (ProgressRequirement r : reqs)
			if (r.material().equals(m))
				return r;
		return null;
	}
}
