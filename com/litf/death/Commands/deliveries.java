package com.litf.death.Commands;

import com.litf.death.Invens.Deliveries.DeliveryMenuMain;
import com.litf.death.Items.Deliveries;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class deliveries implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
         Player p = (Player) sender;
            DeliveryMenuMain gui = new DeliveryMenuMain(p);
            p.openInventory(gui.getInventory());
        }
        return true;
    }
}