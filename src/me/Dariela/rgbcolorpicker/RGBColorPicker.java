package me.Dariela.rgbcolorpicker;


import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.Dariela.rgbcolorpicker.commands.Commands;
import me.Dariela.rgbcolorpicker.listeners.Eventos;
import me.Dariela.rgbcolorpicker.objects.CommandManager;
import me.Dariela.rgbcolorpicker.objects.HexMenu;
import me.Dariela.rgbcolorpicker.utils.Colors;

public class RGBColorPicker extends JavaPlugin {
	Utils utils;
	ConfigManager cm;
	Colors colors;
	public HashMap<String, HexMenu> menus = new HashMap<String, HexMenu>();
	public String version = getDescription().getVersion();
	
	public void onEnable() {
		this.cm = new ConfigManager(this);
		this.utils = new Utils(this);
		this.colors = new Colors(this);
		Bukkit.getPluginManager().registerEvents(new Eventos(), (Plugin)this);
		cm.onStart();
		cm.updateConfig();
		registerCommands();
		Bukkit.getConsoleSender().sendMessage("[RGBColorPicker] Enabled uwu");
		loadMenus();
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("[RGBColorPicker] Disabled unu");
		unregisterCommands();
		}
	
	public void loadMenus() {
		
		for(String str : cm.config.getConfigurationSection("Menus").getKeys(false)) {
			String command = cm.config.getString("Menus."+str+".command").toLowerCase(); if(command == null || command.length() == 0) {error(str, "INVALID COMMAND"); return;}
			if(menus.containsKey(command)) {error(str,"INVALID COMMAND"); return;}
			String permission = cm.config.getString("Menus."+str+".permission");
			String deny_message = cm.config.getString("Menus."+str+".deny_message");
			String button_action = cm.config.getString("Menus."+str+".button_action"); if(button_action == null) {error(str, "INVALID BUTTON ACTION"); return;}
			String button_action_text = cm.config.getString("Menus."+str+".button_action_text"); if(button_action_text == null) {error(str, "INVALID ACTION TEXT"); return;}
			String main_button_text = cm.config.getString("Menus."+str+".MainMenu.button_text");  if(main_button_text == null) {error(str, "INVALID MAIN BUTTON TEXT"); return;}
			String sub_button_text = cm.config.getString("Menus."+str+".SubMenu.button_text");  if(sub_button_text == null) {error(str, "INVALID SUB BUTTON TEXT"); return;}
			String main_title = cm.config.getString("Menus."+str+".MainMenu.title"); if(main_title == null) {error(str, "INVALID MAIN MENU TITLE"); return;}
			String main_hover = cm.config.getString("Menus."+str+".MainMenu.button_hover"); if(main_hover == null) {error(str, "INVALID MAIN MENU BUTTON HOVER"); return;}
			String sub_title = cm.config.getString("Menus."+str+".SubMenu.title"); if(sub_title == null) {error(str, "INVALID SUB MENU TITLE"); return;}
			String sub_hover =cm.config.getString("Menus."+str+".SubMenu.button_hover"); if(sub_hover == null) {error(str, "INVALID SUB MENU BUTTON HOVER"); return;}
			HexMenu menu = new HexMenu(str, command,permission,deny_message,  main_title, sub_title, main_button_text, sub_button_text, button_action, button_action_text, main_hover, sub_hover, this);		
			CommandManager cm = new CommandManager(command);
	        cm.register(menu);
			menus.put(command, menu);
			Bukkit.getConsoleSender().sendMessage("[RGB Color Picker] Registed the menu '"+str+"'  --->  /"+command.toLowerCase()); 
		}
			
	}
    public void unregisterCommands() {
    	for (HexMenu menu : menus.values()) {
    		CommandManager cm = new CommandManager(menu.command);
    		cm.setPermission(null);
    		cm.setPermissionMsg(null);
    		unregisterCommand(menu); 
    	}
    	menus.clear();
    		
    }
    
	private boolean unregisterCommand(HexMenu command) {
    	try {		
    		Field commandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		      commandMap.setAccessible(true);
		      Field knownCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
		      knownCommands.setAccessible(true);
		      ((Map)knownCommands.get(commandMap.get(Bukkit.getServer()))).remove(command.getName());
		      command.unregister((CommandMap)commandMap.get(Bukkit.getServer()));
		      return true;
    		
	    } catch (Exception e) {
		      return false;
	    }
    }
    
	public void reloadPlugin() {
		unregisterCommands();
		cm.reloadConfig();
		cm.saveMessages();
		loadMenus();
		
	}
	
	public void error(String menu, String reason) {
		Bukkit.getConsoleSender().sendMessage("[RGBColorPicker] Failed to register the menu '"+menu+"' ("+reason+")");
		
	}
	public void registerCommands() {
		getCommand("rgbcolorpicker").setExecutor(new Commands());
		getCommand("rgbcolorpicker").setTabCompleter(new Commands());	
	}
	
	public ConfigManager getConfigManager() {
		return this.cm;
	}
	public Colors getColors() {
		return this.colors;
	}
	public Utils getUtils() {		
	    return this.utils;    
	}

	public static RGBColorPicker getInstance() {
		return (RGBColorPicker)JavaPlugin.getPlugin(RGBColorPicker.class);
	}
	
	
}