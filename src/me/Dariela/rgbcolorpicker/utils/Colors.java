package me.Dariela.rgbcolorpicker.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.Dariela.rgbcolorpicker.RGBColorPicker;
import me.Dariela.rgbcolorpicker.objects.NekoSkull;

public class Colors {
	
	RGBColorPicker plugin;
	public HashMap<String, NekoSkull> colors = new HashMap<String, NekoSkull>();
	
	public Colors(RGBColorPicker plugin) {
		this.plugin = plugin;
		
		
	}
	
	public ItemStack getSkullFromBase64(String b64, String uuid, String name) {	
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);  
		SkullMeta skullMeta = (SkullMeta)item.getItemMeta();
	
		GameProfile profile = new GameProfile(UUID.fromString(uuid), name);
		profile.getProperties().put("textures", new Property("textures", b64));
		try {
		    Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", new Class[] { GameProfile.class });
		    mtd.setAccessible(true);
		    mtd.invoke(skullMeta, new Object[] { profile });
		} catch (IllegalAccessException|java.lang.reflect.InvocationTargetException|NoSuchMethodException ex) {
		    ex.printStackTrace();
		}
		item.setItemMeta((ItemMeta)skullMeta);
	    return item;	
	}
	
	public String[] getColors(int i) {
		
		if(i == 100) {
			String[] RGBMain = {"#ff0000","#ff4000","#ff8000","#ffbf00","#ffff00","#bfff00","#80ff00","#40ff00","#00ff00","#00ff40","#00ff80","#00ffbf","#00ffff","#00bfff","#0080ff","#0040ff","#0000ff","#4000ff","#8000ff","#bf00ff","#ff00ff","#ff00bf","#ff0080","#ff0040","#ffffff","#996633"};
			return RGBMain;
		}
		if(i == 0) {
			String[] RGB = {"#ffffff","#ffe6e6","#ffcccc","#ffb3b3","#ff9999","#ff8080","#ff6666","#ff4d4d","#ff3333","#ff1a1a","#ff0000","#e60000","#cc0000","#b30000","#990000","#800000","#660000","#4d0000","#330000","#1a0000"};
			return RGB;
		}
		if(i == 1) {
			String[] RGB = {"#ffffff","#ffece6","#ffd9cc","#ffc6b3","#ffb399","#ff9f80","#ff8c66","#ff794d","#ff6633","#ff531a","#ff4000","#e63900","#cc3300","#b32d00","#992600","#802000","#661a00","#4d1300","#330d00","#1a0600"};
			return RGB;
		}
		if(i == 2) {
			String[] RGB = {"#ffffff","#fff2e6","#ffe6cc","#ffd9b3","#ffcc99","#ffbf80","#ffb366","#ffa64d","#ff9933","#ff8c1a","#ff8000","#e67300","#cc6600","#b35900","#994d00","#804000","#663300","#4d2600","#331a00","#1a0d00"};
			return RGB;
		}
		if(i == 3) {
			String[] RGB = {"#ffffff","#fff9e6","#fff2cc","#ffecb3","#ffe699","#ffdf80","#ffd966","#ffd24d","#ffcc33","#ffc61a","#ffbf00","#e6ac00","#cc9900","#b38600","#997300","#806000","#664d00","#4d3900","#332600","#1a1300"};
			return RGB;
		}
		if(i == 4) {
			String[] RGB = {"#ffffff","#ffffe6","#ffffcc","#ffffb3","#ffff99","#ffff80","#ffff66","#ffff4d","#ffff33","#ffff1a","#ffff00","#e6e600","#cccc00","#b3b300","#999900","#808000","#666600","#4d4d00","#333300","#1a1a00"};
			return RGB;
		}
		if(i == 5) {
			String[] RGB = {"#ffffff","#f9ffe6","#f2ffcc","#ecffb3","#e6ff99","#dfff80","#d9ff66","#d2ff4d","#ccff33","#c6ff1a","#bfff00","#ace600","#99cc00","#86b300","#739900","#608000","#4d6600","#394d00","#263300","#131a00"};
			return RGB;
		}
		if(i == 6) {
			String[] RGB = {"#ffffff","#f2ffe6","#e6ffcc","#d9ffb3","#ccff99","#bfff80","#b3ff66","#a6ff4d","#99ff33","#8cff1a","#80ff00","#73e600","#66cc00","#59b300","#4d9900","#408000","#336600","#264d00","#1a3300","#0d1a00"};
			return RGB;
		}
		if(i == 7) {
			String[] RGB = {"#ffffff","#ecffe6","#d9ffcc","#c6ffb3","#b3ff99","#9fff80","#8cff66","#79ff4d","#66ff33","#53ff1a","#40ff00","#39e600","#33cc00","#2db300","#269900","#208000","#1a6600","#134d00","#0d3300","#061a00"};
			return RGB;
		}
		if(i == 8) {
			String[] RGB = {"#ffffff","#e6ffe6","#ccffcc","#b3ffb3","#99ff99","#80ff80","#66ff66","#4dff4d","#33ff33","#1aff1a","#00ff00","#00e600","#00cc00","#00b300","#009900","#008000","#006600","#004d00","#003300","#001a00"};
			return RGB;
		}
		if(i == 9) {
			String[] RGB = {"#ffffff","#e6ffec","#ccffd9","#b3ffc6","#99ffb3","#80ff9f","#66ff8c","#4dff79","#33ff66","#1aff53","#00ff40","#00e639","#00cc33","#00b32d","#009926","#008020","#00661a","#004d13","#00330d","#001a06"};
			return RGB;
		}
		if(i == 10) {
			String[] RGB = {"#ffffff","#e6fff2","#ccffe6","#b3ffd9","#99ffcc","#80ffbf","#66ffb3","#4dffa6","#33ff99","#1aff8c","#00ff80","#00e673","#00cc66","#00b359","#00994d","#008040","#006633","#004d26","#00331a","#001a0d"};
			return RGB;
		}
		if(i == 11) {
			String[] RGB = {"#ffffff","#e6fff9","#ccfff2","#b3ffec","#99ffe6","#80ffdf","#66ffd9","#4dffd2","#33ffcc","#1affc6","#00ffbf","#00e6ac","#00cc99","#00b386","#009973","#008060","#00664d","#004d39","#003326","#001a13"};
			return RGB;
		}
		if(i == 12) {
			String[] RGB = {"#ffffff","#e6ffff","#ccffff","#b3ffff","#99ffff","#80ffff","#66ffff","#4dffff","#33ffff","#1affff","#00ffff","#00e6e6","#00cccc","#00b3b3","#009999","#008080","#006666","#004d4d","#003333","#001a1a"};
			return RGB;
		}
		if(i == 13) {
			String[] RGB = {"#ffffff","#e6f9ff","#ccf2ff","#b3ecff","#99e6ff","#80dfff","#66d9ff","#4dd2ff","#33ccff","#1ac6ff","#00bfff","#00ace6","#0099cc","#0086b3","#007399","#006080","#004d66","#00394d","#002633","#00131a"};
			return RGB;
		}
		if(i == 14) {		
			String[] RGB = {"#ffffff","#e6f2ff","#cce6ff","#b3d9ff","#99ccff","#80bfff","#66b3ff","#4da6ff","#3399ff","#1a8cff","#0080ff","#0073e6","#0066cc","#0059b3","#004d99","#004080","#003366","#00264d","#001a33","#000d1a"};
			return RGB;
		}
		if(i == 15) {
			String[] RGB = {"#ffffff","#e6ecff","#ccd9ff","#b3c6ff","#99b3ff","#809fff","#668cff","#4d79ff","#3366ff","#1a53ff","#0040ff","#0039e6","#0033cc","#002db3","#002699","#002080","#001a66","#00134d","#000d33","#00061a"};
			return RGB;
		}
		if(i == 16) {
			String[] RGB = {"#ffffff","#e6e6ff","#ccccff","#b3b3ff","#9999ff","#8080ff","#6666ff","#4d4dff","#3333ff","#1a1aff","#0000ff","#0000e6","#0000cc","#0000b3","#000099","#000080","#000066","#00004d","#000033","#00001a"};
			return RGB;
		}
		if(i == 17) {
			String[] RGB = {"#ffffff","#ece6ff","#d9ccff","#c6b3ff","#b399ff","#9f80ff","#8c66ff","#794dff","#6633ff","#531aff","#4000ff","#3900e6","#3300cc","#2d00b3","#260099","#200080","#1a0066","#13004d","#0d0033","#06001a"};
			return RGB;
		}
		if(i == 18) {	
			String[] RGB = {"#ffffff","#f2e6ff","#e6ccff","#d9b3ff","#cc99ff","#bf80ff","#b366ff","#a64dff","#9933ff","#8c1aff","#8000ff","#7300e6","#6600cc","#5900b3","#4d0099","#400080","#330066","#26004d","#1a0033","#0d001a"};
			return RGB;
		}
		if(i == 19) {
			String[] RGB = {"#ffffff","#f9e6ff","#f2ccff","#ecb3ff","#e699ff","#df80ff","#d966ff","#d24dff","#cc33ff","#c61aff","#bf00ff","#ac00e6","#9900cc","#8600b3","#730099","#600080","#4d0066","#39004d","#260033","#13001a"};
			return RGB;
		}
		if(i == 20) {
			String[] RGB = {"#ffffff","#ffe6ff","#ffccff","#ffb3ff","#ff99ff","#ff80ff","#ff66ff","#ff4dff","#ff33ff","#ff1aff","#ff00ff","#e600e6","#cc00cc","#b300b3","#990099","#800080","#660066","#4d004d","#330033","#1a001a"};
			return RGB;
		}
		if(i == 21) {		
			String[] RGB = {"#ffffff","#ffe6f9","#ffccf2","#ffb3ec","#ff99e6","#ff80df","#ff66d9","#ff4dd2","#ff33cc","#ff1ac6","#ff00bf","#e600ac","#cc0099","#b30086","#990073","#800060","#66004d","#4d0039","#330026","#1a0013"};
			return RGB;
		}
		if(i == 22) {
			String[] RGB = {"#ffffff","#ffe6f2","#ffcce6","#ffb3d9","#ff99cc","#ff80bf","#ff66b3","#ff4da6","#ff3399","#ff1a8c","#ff0080","#e60073","#cc0066","#b30059","#99004d","#800040","#660033","#4d0026","#33001a","#1a000d"};
			return RGB;
		}
		if(i == 23) {
			String[] RGB = {"#ffffff","#ffe6ec","#ffccd9","#ffb3c6","#ff99b3","#ff809f","#ff668c","#ff4d79","#ff3366","#ff1a53","#ff0040","#e60039","#cc0033","#b3002d","#990026","#800020","#66001a","#4d0013","#33000d","#1a0006"};
			return RGB;
		}
		if(i == 24) {
			String[] RGB = {"#ffffff","#f2f2f2","#e6e6e6","#d9d9d9","#cccccc","#bfbfbf","#b3b3b3","#a6a6a6","#999999","#8c8c8c","#808080","#737373","#666666","#595959","#4d4d4d","#404040","#333333","#262626","#1a1a1a","#000000"};
			return RGB;
		}
		if(i == 25) {
			String[] RGB = {"#ffffff","#f9f2ec","#f2e6d9","#ecd9c6","#e6ccb3","#dfbf9f","#d9b38c","#d2a679","#cc9966","#c68c53","#bf8040","#ac7339","#996633","#86592d","#734d26","#604020","#4d3319","#392613","#261a0d","#130d06"};
			return RGB;
		}
		return null;
	}

}
