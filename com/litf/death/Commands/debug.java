package com.litf.death.Commands;

import com.litf.death.Events.FTyrantEvents;
import com.litf.death.Invens.ItemMenu.ItemMenuMain;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class debug implements CommandExecutor {
    public static List<String> argss = Arrays.asList("TYRANT_RESET","TYRANT_FAKEKILL");
    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(Calls.getRank(p)>=3){
                String arge = "";
                for(String st:argss){
                    arge=arge+st;
                    if(argss.size()-1!=argss.indexOf(st))
                        arge=arge+", ";
                }
                if(args.length == 0){
                    p.sendMessage(ChatColor.RED+"Invalid Arguments. Valid args are "+arge);
                }else{
                    switch(args[0]){
                        case "TYRANT_RESET":
                            Main.serverdata.getConfig().set("FTyrantData.LastSpawn", 0);
                            p.sendMessage(ChatColor.GREEN+"Forest Tyrant Timer Reset");
                            break;
                        case "TYRANT_FAKEKILL":
                            try {
                                HashMap<Player, Double> ft = new HashMap<>();
                                ft.put(p,(double)90000000);
                                for (int i = 0; i < Integer.parseInt(args[1])-1; i++) {
                                    ft.put(null,(double)i);
                                }
                                FTyrantEvents.TyrantDeath(Integer.parseInt(args[2]), ft);
                            }catch(Exception e){
                            p.sendMessage(ChatColor.RED+"An error occured. You most likley forgot an argument");
                            e.printStackTrace();
                            }
                            break;
                        default:
                            p.sendMessage(ChatColor.RED+"Unknown debug type");
                    }
                }
            }else{
                p.sendMessage(ChatColor.RED+"You must be an admin or higher to use this command");
            }

        }
        return true;
    }
}
