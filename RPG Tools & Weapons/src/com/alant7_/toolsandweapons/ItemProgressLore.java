package com.alant7_.toolsandweapons;

import org.bukkit.Material;

public class ItemProgressLore {
	
	private String[] lore = new String[] {
			"&7&m--------&7[&r &eSTATS &7]&m--------",
			"  &cLevel &8&l>> &7%level%",
			"  &cExp   &8&l>> &7%progress%"
	};
	
	private Material m;
	
	public ItemProgressLore(Material m) {
		this.m = m;
	}
	public ItemProgressLore(Material m, String[] lore) {
		this.m = m;
		this.lore = lore;
	}
	
	public String[] lore() {
		return this.lore;
	}
	public Material material() {
		return this.m;
	}
	
}
