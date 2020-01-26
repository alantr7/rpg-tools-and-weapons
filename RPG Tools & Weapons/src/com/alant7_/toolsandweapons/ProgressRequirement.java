package com.alant7_.toolsandweapons;

import org.bukkit.Material;

public class ProgressRequirement {
	
	private Material m;

	private int[] level;
	private int[] value;
	
	public ProgressRequirement(Material m, int[] level, int[] value) {
		this.m = m;
		this.level = level;
		this.value = value;
	}
	
	public Material material() {
		return this.m;
	}
	
	public int[] requirements(int level) {
		for (int i = 0; i < this.level.length; i++) {
			if (this.level[i] == level)
				return new int[] {
						this.level[i], this.value[i]
				};
		}
		return new int[] {
				0, 0
		};
	}
	
}
