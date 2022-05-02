package com.litf.death.Commands;

import com.litf.death.Invens.ItemMenu.ItemMenuMain;
import com.litf.death.Ranks.Calls;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class setdestinycount implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cms, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(Calls.getRank(p)>=3){
                String id = "null";
                String dest = "null";
                int count = 0;
                try {
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    id = lis.get(0).getString("id");
                    dest = lis.get(4).getString("Destiny");
                    count = lis.get(5).getInt("DestinyCount");
                }catch(NullPointerException ex){
                }
                if(id.equals("null")){
                    p.sendMessage(ChatColor.RED+"Your item must have a destiny on it to use this command");
                }else if(args.length==0){
                    p.sendMessage(ChatColor.RED+"You must state the number you wise to put on the item");
                }else{
                    try {
                        count = Integer.parseInt(args[0]);
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        lis.get(5).setInt("DestinyCount", count);
                        comp.set("comp", lis);
                        p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                    }catch(NullPointerException ex){

                    }
                }
            }else{
                p.sendMessage(ChatColor.RED+"You must be an admin or higher to use this command");
            }

        }
        return true;
    }
}
