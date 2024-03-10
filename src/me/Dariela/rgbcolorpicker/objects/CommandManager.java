package me.Dariela.rgbcolorpicker.objects;

import java.lang.reflect.Field;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import me.Dariela.rgbcolorpicker.RGBColorPicker;

public class CommandManager {

	private String cmdName;

	private CommandMap cmdMap;

	public CommandManager(String cmdName) {
		this.cmdName = cmdName;
		try {
			Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			bukkitCommandMap.setAccessible(true);
			this.cmdMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
		} catch (Exception e) {}
	}

	public void register(Command cmd) {
		cmdMap.register(RGBColorPicker.getInstance().getName(), cmd);
	}

	public void setDescription(String description) {
		this.cmdMap.getCommand(this.cmdName).setDescription(description);
	}

	public String getDescription() {
		return this.cmdMap.getCommand(this.cmdName).getDescription();
	}

	public void setPermission(String permission) {
		this.cmdMap.getCommand(this.cmdName).setPermission(permission);
	}

	public String getPermission() {
		return this.cmdMap.getCommand(this.cmdName).getPermission();
	}

	public void setPermissionMsg(String permissionMsg) {
		this.cmdMap.getCommand(this.cmdName).setPermissionMessage(permissionMsg);
	}

	public String getPermissionMsg() {
		return this.cmdMap.getCommand(this.cmdName).getPermissionMessage();
	}

	public void setUsage(String usage) {
		this.cmdMap.getCommand(this.cmdName).setUsage(usage);
	}

	public String getUsage() {
		return this.cmdMap.getCommand(this.cmdName).getUsage();
	}

	public void setAliases(List<String> aliases) {
		this.cmdMap.getCommand(this.cmdName).setAliases(aliases);
	}

	public List<String> getAliases() {
		return this.cmdMap.getCommand(this.cmdName).getAliases();
	}
}