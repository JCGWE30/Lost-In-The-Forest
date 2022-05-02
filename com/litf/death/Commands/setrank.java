package com.litf.death.Commands;

import com.litf.death.Invens.ItemMenu.ItemMenuMain;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class setrank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(Calls.getRank(p)>=4){
                if(args.length<2){
                    p.sendMessage(ChatColor.RED+"More Args needed");
                }else{
                    if(Bukkit.getPlayer(args[0])==null){
                        p.sendMessage(ChatColor.RED+"Invalid Player");
                    }else{
                        List<String> admins = Main.serverdata.getConfig().getStringList("Ranks.admin");
                        List<String> mods = Main.serverdata.getConfig().getStringList("Ranks.mod");
                        List<String> builds = Main.serverdata.getConfig().getStringList("Ranks.builder");
                        switch(args[1]){
                            case "admin":
                                if(admins.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    p.sendMessage(ChatColor.RED+"User is already an admin");
                                }else{
                                    p.sendMessage(ChatColor.GREEN+"User has had new rank set");
                                    admins.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                if(mods.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    mods.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                if(builds.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    builds.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                Main.serverdata.getConfig().set("Ranks.admin", admins);
                                Main.serverdata.getConfig().set("Ranks.mod", mods);
                                Main.serverdata.getConfig().set("Ranks.builder", builds);
                                Main.serverdata.saveConfig();
                                Main.serverdata.reloadConfig();
                                break;
                            case "mod":
                                if(admins.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    admins.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                if(mods.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    p.sendMessage(ChatColor.RED+"User is already a mod");
                                }else{
                                    p.sendMessage(ChatColor.GREEN+"User has had new rank set");
                                    mods.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                if(builds.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    builds.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                Main.serverdata.getConfig().set("Ranks.admin", admins);
                                Main.serverdata.getConfig().set("Ranks.mod", mods);
                                Main.serverdata.getConfig().set("Ranks.builder", builds);
                                Main.serverdata.saveConfig();
                                Main.serverdata.reloadConfig();
                                break;
                            case "builder":
                                if(admins.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    admins.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                if(mods.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    mods.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                if(builds.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                    p.sendMessage(ChatColor.RED+"User is already a builder");
                                }else{
                                    p.sendMessage(ChatColor.GREEN+"User has had new rank set");
                                    builds.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                                }
                                Main.serverdata.getConfig().set("Ranks.admin", admins);
                                Main.serverdata.getConfig().set("Ranks.mod", mods);
                                Main.serverdata.getConfig().set("Ranks.builder", builds);
                                Main.serverdata.saveConfig();
                                Main.serverdata.reloadConfig();
                                break;
                            default:
                                p.sendMessage(ChatColor.RED+"Rank is invalid");
                        }
                    }
                }
            }else{
                p.sendMessage(ChatColor.RED+"You must be an owner or higher to use this command");
            }

        }else{
            if(args.length<2){
                System.out.println(ChatColor.RED+"More Args needed");
            }else{
                if(Bukkit.getPlayer(args[0])==null){
                    System.out.println(ChatColor.RED+"Invalid Player");
                }else{
                    List<String> admins = Main.serverdata.getConfig().getStringList("Ranks.admin");
                    List<String> mods = Main.serverdata.getConfig().getStringList("Ranks.mod");
                    List<String> builds = Main.serverdata.getConfig().getStringList("Ranks.builder");
                    switch(args[1]){
                        case "admin":
                            if(admins.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                System.out.println(ChatColor.RED+"User is already an admin");
                            }else{
                                System.out.println(ChatColor.GREEN+"User has had new rank set");
                                admins.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            if(mods.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                mods.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            if(builds.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                builds.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            Main.serverdata.getConfig().set("Ranks.admin", admins);
                            Main.serverdata.getConfig().set("Ranks.mod", mods);
                            Main.serverdata.getConfig().set("Ranks.builder", builds);
                            Main.serverdata.saveConfig();
                            Main.serverdata.reloadConfig();
                            break;
                        case "mod":
                            if(admins.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                admins.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            if(mods.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                System.out.println(ChatColor.RED+"User is already a mod");
                            }else{
                                System.out.println(ChatColor.GREEN+"User has had new rank set");
                                mods.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            if(builds.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                builds.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            Main.serverdata.getConfig().set("Ranks.admin", admins);
                            Main.serverdata.getConfig().set("Ranks.mod", mods);
                            Main.serverdata.getConfig().set("Ranks.builder", builds);
                            Main.serverdata.saveConfig();
                            Main.serverdata.reloadConfig();
                            break;
                        case "builder":
                            if(admins.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                admins.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            if(mods.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                mods.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            if(builds.contains(Bukkit.getPlayer(args[0]).getUniqueId().toString())){
                                System.out.println(ChatColor.RED+"User is already a builder");
                            }else{
                                System.out.println(ChatColor.GREEN+"User has had new rank set");
                                builds.add(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                            }
                            Main.serverdata.getConfig().set("Ranks.admin", admins);
                            Main.serverdata.getConfig().set("Ranks.mod", mods);
                            Main.serverdata.getConfig().set("Ranks.builder", builds);
                            Main.serverdata.saveConfig();
                            Main.serverdata.reloadConfig();
                            break;
                        default:
                            System.out.println(ChatColor.RED+"Rank is invalid");
                    }
                }
            }
        }
        return true;
    }
}
