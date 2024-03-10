package me.Dariela.rgbcolorpicker.objects;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Dariela.rgbcolorpicker.RGBColorPicker;

public class NekoSkull {
	
	public String color;
	public UUID uuid;
	public String texture_b4;
	public Integer id;
	private ItemStack head;
	private RGBColorPicker plugin = RGBColorPicker.getInstance();
	
	public NekoSkull(String color, String uuid, String texture_b4, int id) {
		this.color = color;
		this.uuid = UUID.fromString(uuid);
		this.texture_b4 = texture_b4;
		this.id = id;
		this.head = plugin.getColors().getSkullFromBase64(texture_b4, uuid, color);	
	}
	
	public ItemStack getHead() {
		ItemStack head = this.head.clone();
		ItemMeta meta = head.getItemMeta();
		meta.setDisplayName(plugin.getUtils().color(color)+color);
		head.setItemMeta(meta);
		return head;
		
	}
	public ItemStack getStrippedHead() {
		return this.head;
	}


}
