package me.Dariela.rgbcolorpicker.objects;

import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import me.Dariela.rgbcolorpicker.RGBColorPicker;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

@SuppressWarnings("deprecation")
public class HexMenu extends BukkitCommand {
	private RGBColorPicker plugin;
	public static CommandMap commandMap = null;
	public String key;
	public String command;
	String mainTitle;
	String subMenuTitle;
	String mainSymbol;
	String actionType;
	String action;
	String mainHover;
	String subHover;
	String subSymbol;
	String permission;
	String deny_message;
	
	
	public HexMenu(String key,String command, String permission, String deny_message, String mainTitle, String subMenuTitle, String mainSymbol, String subSymbol, String actionType, String action, String mainHover, String subHover, RGBColorPicker plugin) {
		super(command);
		setLabel(command);
		this.key = key;
		this.command = command;
		this.mainTitle = mainTitle;
		this.subMenuTitle = subMenuTitle;
		this.mainSymbol = mainSymbol;
		this.subSymbol = subSymbol;	
		this.actionType = actionType;
		this.action = action;	
		this.mainHover = mainHover;
		this.subHover = subHover;
		this.plugin = plugin;
		this.permission = permission;
		this.deny_message = deny_message;
	}
	
	@Override
	public boolean execute(CommandSender s, String l, String[] a) {
		if(s instanceof Player) {		
			Player p = (Player) s;	
			if(plugin.menus.containsKey(getLabel())) {		
				if(permission != null) {
					if(!p.hasPermission(permission)) {	
						if(deny_message == null) {
							p.sendMessage(plugin.getUtils().color(plugin.getConfigManager().messages.getString("default_deny_message")));
						} else {
							p.sendMessage(plugin.getUtils().color(deny_message));
							
						}
						
						return false;
						}
		
					}
				HexMenu menu = plugin.menus.get(getLabel());
				if(a.length == 0) {
					menu.getMainMenu(p);
				} else {
					try {
						menu.getSubMenu(p, Integer.valueOf(a[0]));
					} catch(NumberFormatException ex) {
						menu.getMainMenu(p);	
						
					}		
					
				}		
				
			}
		}
		return true;
	}
	
	
	public void getMainMenu(Player p) {
		TextComponent end = new TextComponent();
		String[] colores = plugin.getColors().getColors(100);
		int c = 0;
		for(int i = 0;i < colores.length; i++) {
			c++;
			if(c == 1) end.addExtra(new TextComponent("   "));
			if(c >= 9) {end.addExtra(new TextComponent("\n\n   ")); c = 1;}
			TextComponent txt = plugin.getUtils().getText(setPlaceholders(p, mainSymbol, colores[i], false));
			txt.addExtra(plugin.getUtils().getText("&l  "));
			txt.setColor(ChatColor.of(String.valueOf(colores[i])));
		    ComponentBuilder cba = new ComponentBuilder();
		    cba.append(plugin.getUtils().getText(setPlaceholders(p, mainHover, colores[i], false)));
		    cba.color(ChatColor.of(String.valueOf(colores[i])));
		    HoverEvent ev1 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, cba.create());
		    txt.setHoverEvent(ev1);
		    txt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+command+" "+i));    
		    end.addExtra((BaseComponent)txt);
		}
		end.addExtra(new TextComponent("\n"));
		p.spigot().sendMessage(plugin.getUtils().getText(setPlaceholders(p, mainTitle, "", false)+"\n"));
		p.spigot().sendMessage((BaseComponent)end);	
	}


	public void getSubMenu(Player p, int id) {
		TextComponent end = new TextComponent();
		String[] colores = plugin.getColors().getColors(id);
		int c = 0;
		for(int i = 0;i < 20; i++) {
			c++;
			if(c == 1) end.addExtra(new TextComponent("      "));
			if(c >= 6) {end.addExtra(new TextComponent("\n\n      ")); c = 1;}
			TextComponent txt = plugin.getUtils().getText(setPlaceholders(p, subSymbol, colores[i], false));
			txt.addExtra(plugin.getUtils().getText("&l  "));	
			txt.setColor(ChatColor.of(String.valueOf(colores[i])));
		    ComponentBuilder cba = new ComponentBuilder();
		    cba.append(plugin.getUtils().getText(setPlaceholders(p, subHover, colores[i], false)));
		    HoverEvent ev1 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, cba.create());
		    txt.setHoverEvent(ev1);
		    end.addExtra((BaseComponent)setClickAction(txt, p, colores[i]));
		}	
		end.addExtra(new TextComponent("\n\n"));
		end.addExtra((BaseComponent)mainMenu());
		end.addExtra(new TextComponent("\n"));
		p.spigot().sendMessage(plugin.getUtils().getText(setPlaceholders(p, subMenuTitle, "", false)+"\n"));
		p.spigot().sendMessage((BaseComponent)end);
		p.sendMessage("");		
		
	}
	private TextComponent mainMenu() {
		TextComponent end = plugin.getUtils().getText(plugin.getConfig().getString("Config.main_menu_button.text"));
		ComponentBuilder cba = new ComponentBuilder();
	    cba.append(plugin.getUtils().getText(plugin.getConfig().getString("Config.main_menu_button.hover")));
	    HoverEvent ev1 = new HoverEvent(HoverEvent.Action.SHOW_TEXT, cba.create());
	    end.setHoverEvent(ev1);
	    end.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/"+command)); 
	    return end;
		
	}
	
	public TextComponent setClickAction(TextComponent end, Player p, String hexColor) {	
		try {
			 end.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(actionType), setPlaceholders(p, action, hexColor, true))); 
		} catch (IllegalArgumentException ex) {
			end.setInsertion(setPlaceholders(p, action, hexColor, true));			
		}		
		return end;
	}
	public String setPlaceholders(Player p, String text, String hexColor, boolean noUwU) {
		String str = text.replaceAll("%player%", p.getName()).replaceAll("%color%", hexColor);
		if(plugin.getUtils().hasPlaceholderApi) str = PlaceholderAPI.setPlaceholders(p, str);
		if(noUwU) str = str.replaceAll("�", "&");
		return str;
		
	}
	public enum ClickAction {
		  RUN_COMMAND,
		  COPY_TO_CLIPBOARD,
		  SUGGEST_COMMAND,
		  SUGGEST_TEXT
		}
}
