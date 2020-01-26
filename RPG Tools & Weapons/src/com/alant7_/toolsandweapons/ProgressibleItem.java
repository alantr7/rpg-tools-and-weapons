package com.alant7_.toolsandweapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_15_R1.NBTTagCompound;

public class ProgressibleItem {
	
	private int level = 1, exp = 0;
	private boolean hasLeveledUp = false;
	
	private ItemStack item;
	private net.minecraft.server.v1_15_R1.ItemStack item1;
	
	private NBTTagCompound tag;
	
	public ProgressibleItem(ItemStack item) {
		this.item = item;
		
		item1 = CraftItemStack.asNMSCopy(item);
		
		tag = item1.getTag();
		if (tag == null) {
			tag = new NBTTagCompound();
			return;
		}
		
		level = tag.getInt("alant7_.level");
		if (level == 0) {
			level = 1;
			return;
		}		
		exp = tag.getInt("alant7_.exp");
		
	}
	
	public void updateItem() {
		
		tag.setInt("alant7_.level", level);
		tag.setInt("alant7_.exp", exp);
		
		item1.setTag(tag);
		item = CraftItemStack.asBukkitCopy(item1);
		
		List<String> ilore = new ArrayList<>();
		
		ItemProgressLore l = Main.lore(item.getType());
		for (int i = 0; i < l.lore().length; i++) {
			String lore = l.lore()[i];
			
			lore = lore.replace("%level%", "" + this.level);
			lore = lore.replace("%progress%", "" + ((double)this.exp / (double)requiredExp(level) * 100.0d) + "%");
			ilore.add(ChatColor.translateAlternateColorCodes('&', lore));			
		}
		
		ItemMeta im = item.getItemMeta();
		im.setLore(ilore);
			
		item.setItemMeta(im);
		
	}
	
	public ItemStack item() {
		return this.item;
	}
	
	public void addExp(int exp) {
		this.exp += exp;
		
		int reqExp = requiredExp(level) - this.exp;
		
		// ON PLAYER LEVEL-UP
		if (exp > reqExp) {
			this.exp = 0;
			exp -= reqExp;
			level++;
			
			this.hasLeveledUp = true;
			
			addExp(exp);
		}
		
		updateItem();
	}
	
	public int level() {
		return this.level;
	}
	public int exp() {
		return this.exp;
	}
	public int requiredExp(int level) {
		return Main.requirement(this.item.getType()).requirements(level)[1];
	}
	public boolean hasLeveledUp() {
		return this.hasLeveledUp;
	}
	
}
