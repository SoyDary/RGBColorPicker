package me.Dariela.rgbcolorpicker.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import me.Dariela.rgbcolorpicker.RGBColorPicker;



public class Commands implements CommandExecutor, TabCompleter {
	String title = "&#ff0000[&#ff9999R&#ffb399G&#ffcc99B &#ffe699C&#ffff99o&#e6ff99l&#ccff99o&#b3ff99r &#99ff99P&#99ffb3i&#99ffccc&#99ffe6k&#99ffffe&#99e6ffr&#0073e6]";
	String title_ver = "&#ff0000[&#ff9999R&#ffb399G&#ffcc99B &#ffe699C&#ffff99o&#e6ff99l&#ccff99o&#b3ff99r &#99ff99P&#99ffb3i&#99ffccc&#99ffe6k&#99ffffe&#99e6ffr &#80bfffv&#99ccff1&#99b3ff.&#9999ff0.1&#0073e6]";
	String nya = "&#b399ffb&#cc99ffy &#e699ffD&#ff99ffa&#ff99e6r&#ff99ccy &#ff0000♥";
	private RGBColorPicker plugin = RGBColorPicker.getPlugin(RGBColorPicker.class);
	int t = 0;
	int count = 0;
	List<String> skins = new ArrayList<String>();
	//List<File> skins = new ArrayList<File>();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] a) {
		if(cmd.getLabel().equalsIgnoreCase("rgbcolorpicker") || cmd.getLabel().equalsIgnoreCase("rcp")) {
			if(a.length == 0) {
				s.sendMessage(plugin.getUtils().color(title_ver+" "+nya));
				if(s.hasPermission("rcp.admin")) {
					s.sendMessage(plugin.getUtils().color("&e&l▸ &#ffc61a/&#ffff99rcp reload  &7-  &#b3ffec&oReload the configuration"));
				}
				
			} else {
				if(s.hasPermission("rcp.admin")) {
					if(a[0].equalsIgnoreCase("reload")) {
						plugin.reloadPlugin();
						
						String ss = plugin.menus.size() < 2 ? "": "s";
						s.sendMessage(plugin.getUtils().color(title+" &eConfiguration reloaded!"));
						s.sendMessage(plugin.getUtils().color("&#ffe6ff"+plugin.menus.size()+" &#ffff99registered command"+ss+"..."));
						
					} 
					
					
				}
				
			}
		}
		return true;
	}



	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String l, String[] a) {
	    if (l.equalsIgnoreCase("rgbcolorpicker") || l.equalsIgnoreCase("rcp")) {
		      if (a.length == 1) {
		        List<String> commandsList = new ArrayList<>();
		        List<String> preCommands = new ArrayList<>();
		        commandsList.add("test");
		        commandsList.add("skull");
		        commandsList.add("reload");
		        for (String text : commandsList) {
		          if (text.toLowerCase().startsWith(a[0].toLowerCase()))
		            preCommands.add(text); 
		        } 
		        return preCommands;
		      } 
		    } 
	    return null;
		
	}
	


}
