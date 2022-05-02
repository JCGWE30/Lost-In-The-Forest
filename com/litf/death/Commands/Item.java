package com.litf.death.Commands;

import com.litf.death.Invens.ItemMenu.ItemMenuMain;
import com.litf.death.Ranks.Calls;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Item implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(Calls.getRank(p)>=3){
                ItemMenuMain gui = new ItemMenuMain();
                p.openInventory(gui.getInventory());
            }else{
                p.sendMessage(ChatColor.RED+"You must be an admin or higher to use this command");
            }

        }
        return true;
    }
}
