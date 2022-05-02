package com.litf.death.Commands;

import com.litf.death.Invens.ItemMenu.ItemMenuMain;
import com.litf.death.Items.Deliveries;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class deliver implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(Calls.getRank(p)>=3){
                if(args.length==0){
                    p.sendMessage(ChatColor.RED+"Provide a player name");
                }else if(args.length==1){
                    if(args[0].equals("list")){
                        for(int i = 0; i<Deliveries.items.size(); i++){
                            p.sendMessage(ChatColor.GREEN+""+i+" - "+Deliveries.items.get(i).getItemMeta().getDisplayName());
                        }
                    }else {
                        p.sendMessage(ChatColor.RED + "Specify item number");
                    }
                }else{
                    if(Bukkit.getPlayer(args[0])==null){
                        if(!Main.data.getConfig().contains(args[0])){
                            p.sendMessage(ChatColor.RED + "That player is invalid");
                        }else {

                        }
                    }else
                    {
                        try{
                                ItemStack item = Deliveries.items.get(Integer.parseInt(args[1]));
                                Player p2 = Bukkit.getPlayer(args[0]);
                                p2.sendMessage(ChatColor.GREEN+"*Knock* *Knock*. You have a delivery! Use /deliveries to view it");
                                p2.playSound(p.getLocation(),Sound.NOTE_PLING, 2f, 2f);
                            if (Main.data.getConfig().get(p2.getUniqueId().toString()+".Deliveries.Items")==null) {
                                Main.data.getConfig().set(p2.getUniqueId().toString()+".Deliveries.Items", Arrays.asList(Integer.parseInt(args[1])));
                                Main.data.getConfig().set(p2.getUniqueId().toString()+".Deliveries.Names",Arrays.asList(Calls.getStringRank(p)+" "+p.getName()));
                                Main.data.saveConfig();
                            }else{
                                List<Integer> list = Main.data.getConfig().getIntegerList(p2.getUniqueId().toString()+".Deliveries.Items");
                                List<String> liss = Main.data.getConfig().getStringList(p2.getUniqueId().toString()+".Deliveries.Names");
                                list.add(Integer.parseInt(args[1]));
                                liss.add(Calls.getStringRank(p)+" "+p.getName());
                                Main.data.getConfig().set(p2.getUniqueId().toString()+".Deliveries.Names",liss);
                                Main.data.getConfig().set(p2.getUniqueId().toString()+".Deliveries.Items", list);
                                Main.data.saveConfig();
                            }
                            }catch(Exception ex){
                            if(args[1]=="list"){
                                for(int i=0;i<Deliveries.items.size();i++){
                                    p.sendMessage(i+" "+Deliveries.items.get(i).getItemMeta().getDisplayName());
                                }
                            }
                        }
                    }
                }
            }else{
                p.sendMessage(ChatColor.RED+"You must be an admin or higher to use this command");
            }

        }
        return true;
    }
}
