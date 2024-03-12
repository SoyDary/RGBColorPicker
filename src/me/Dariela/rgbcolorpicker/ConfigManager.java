package me.Dariela.rgbcolorpicker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {	
	public RGBColorPicker plugin;
	public String default_deny_message;
	public File configFile;
	public FileConfiguration config;
	public File messagesFile;
	public FileConfiguration messages;
	
	public ConfigManager(RGBColorPicker plugin) {
		this.configFile = new File(plugin.getDataFolder() + File.separator+ "config.yml");
		this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
		this.messagesFile = new File(plugin.getDataFolder() + File.separator+ "messages.yml");
		this.messages = (FileConfiguration)YamlConfiguration.loadConfiguration(this.messagesFile);
		this.plugin = plugin;
		
	}
	
	public void onStart() {
		if(!configFile.exists()) {	
            plugin.saveResource("config.yml", false);
            plugin.reloadConfig();
      }
		if(!messagesFile.exists()) {	
            plugin.saveResource("messages.yml", true);
      }
	}
	
	public void saveConfig() {
		try {
			this.config.save(this.configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveMessages() {
		try {
			this.messages.save(this.messagesFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        messages = YamlConfiguration.loadConfiguration(messagesFile);
    }
	public void updateConfig() {
		Path path = Paths.get(plugin.getDataFolder() + File.separator+ "config.yml");
		Charset charset = StandardCharsets.UTF_8;
		String content = null;
		try {
			content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll("      tile:", "      title:");
			Files.write(path, content.getBytes(charset));
			config = YamlConfiguration.loadConfiguration(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFile(File file, FileConfiguration config) {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}