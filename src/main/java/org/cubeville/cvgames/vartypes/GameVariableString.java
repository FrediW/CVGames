package org.cubeville.cvgames.vartypes;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.cubeville.cvgames.utils.GameUtils;
import org.w3c.dom.Text;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.regex.Matcher;

public class GameVariableString extends GameVariable {

	String item;

	@Override
	public void setItem(Player player, String input, String arenaName) throws Error {
		item = input;
	}

	@Override
	public String typeString() {
		return "String";
	}

	@Override
	public void setItem(@Nullable Object object, String arenaName) {
		if (!(object instanceof String)) {
			item = null;
		} else {
			item = (String) object;
		}
	}

	private String createColorString(String input) {
		Matcher matcher = GameUtils.HEX_PATTERN.matcher(input);
		StringBuilder stringBuilder = new StringBuilder();

		while(matcher.find()) {
			matcher.appendReplacement(stringBuilder, ChatColor.of("#" + matcher.group(1)).toString());
		}
		return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(stringBuilder).toString());
	}

	private TextComponent createColorTextComponent(String input) {
		Matcher matcher = GameUtils.HEX_PATTERN.matcher(input);
		String[] inBetweens = input.split(GameUtils.HEX_PATTERN.pattern());
		TextComponent tc = new TextComponent(ChatColor.translateAlternateColorCodes('&', inBetweens[0]));
		int i = 1;
		while (matcher.find()) {
			TextComponent colorArea = new TextComponent(ChatColor.translateAlternateColorCodes('&', inBetweens[i]));
			colorArea.setColor(ChatColor.of("#" + matcher.group(1)));
			tc.addExtra(colorArea);
			i++;
		}
		return tc;
	}

	@Override
	public Object getItem() {
		return createColorString(item);
	}

	@Override
	public String itemString() {
		return item;
	}

	@Override
	public TextComponent displayString() {
		return item == null ? new TextComponent("null") : createColorTextComponent(item);
	}

	@Override
	public boolean isValid() {
		return item != null;
	}

}
