package org.cubeville.cvgames.commands;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cubeville.cvgames.managers.PlayerManager;
import org.cubeville.cvgames.models.Arena;

import java.util.List;

public class HostAnnounce extends RunnableCommand {

    @Override
    public TextComponent execute(CommandSender sender, List<Object> params) throws Error {
        Arena arena = PlayerManager.getPlayerArena((Player) sender);
        String gameName = arena.getQueue().getSelectedGame();
        String startingTime = "soon";
        if (!params.isEmpty()) {
            int minutes = (int) params.get(0);
            if (minutes == 1) {
                startingTime = "in 1 minute";
            } else {
                startingTime = "in " + minutes + " minutes";
            }
        }
        TextComponent announcement = new TextComponent("§e§l[§c§lC§9§lV§f§lGames§e§l]§e A game of " + gameName + " on the arena " + arena.getName() + " is starting " + startingTime + "!");
        // WILL EVENTUALLY BE REPLACED WITH A PROXY-WIDE BROADCAST
        Bukkit.getServer().spigot().broadcast(announcement);
        return new TextComponent();
    }
}
