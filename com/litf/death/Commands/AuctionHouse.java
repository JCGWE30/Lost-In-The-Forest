package com.litf.death.Commands;

import com.litf.death.Entits.NPCS.NPC;
import com.litf.death.Invens.AuctionHouse.AuctionHouseMain;
import com.litf.death.Invens.ItemMenu.ItemMenuMain;
import com.litf.death.Ranks.Calls;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AuctionHouse implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            AuctionHouseMain gui = new AuctionHouseMain();
            p.openInventory(gui.getInventory());
        }
        return true;
    }
}
