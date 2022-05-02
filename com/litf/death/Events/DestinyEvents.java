package com.litf.death.Events;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DestinyEvents implements Listener {
    @EventHandler
    public static void kill(EntityDeathEvent e){
        if(e.getEntity().getKiller()!=null){
            Player p = e.getEntity().getKiller();
            if(p.getInventory().getItemInHand()!=null){
                String id = " ";
                String dest = " ";
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
                if(dest!=null){
                    if(dest.equals("SKITING")){
                        if(count<4){
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(5-count)+ChatColor.GRAY;
                            lore.set(lore.size()-1, (ChatColor.GRAY+"Kill % more skiters to level up to I").replace(cs, ca));
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count==4){
                            RecipeTierEvents.callDestinyLevel(e.getEntity().getKiller());
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(30-count)+ChatColor.GRAY;
                            lore.set(lore.size()-2,ChatColor.DARK_GREEN+"Skiting Destiny I");
                            lore.set(lore.size()-1, (ChatColor.GRAY+"Kill % more skiters to level up to II").replace(cs, ca));
                            lore.add(ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"2% More luck");
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count<29){
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(30-count)+ChatColor.GRAY;
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to II").replace(cs, ca));
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count==29){
                            RecipeTierEvents.callDestinyLevel(e.getEntity().getKiller());
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(80-count)+ChatColor.GRAY;
                            lore.set(lore.size()-3,ChatColor.DARK_GREEN+"Skiting Destiny II");
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to III").replace(cs, ca));
                            lore.set(lore.size()-1, ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"4% More luck");
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count<79){
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(80-count)+ChatColor.GRAY;
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to III").replace(cs, ca));
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count==79){
                            RecipeTierEvents.callDestinyLevel(e.getEntity().getKiller());
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(180-count)+ChatColor.GRAY;
                            lore.set(lore.size()-3,ChatColor.DARK_GREEN+"Skiting Destiny III");
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to IV").replace(cs, ca));
                            lore.set(lore.size()-1, ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"6% More luck");
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count<179){
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(180-count)+ChatColor.GRAY;
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to IV").replace(cs, ca));
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count==179){
                            RecipeTierEvents.callDestinyLevel(e.getEntity().getKiller());
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(430-count)+ChatColor.GRAY;
                            lore.set(lore.size()-3,ChatColor.DARK_GREEN+"Skiting Destiny IV");
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to V").replace(cs, ca));
                            lore.set(lore.size()-1, ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"8% More luck");
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count<429){
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            CharSequence cs = "%";
                            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(430-count)+ChatColor.GRAY;
                            lore.set(lore.size()-2, (ChatColor.GRAY+"Kill % more skiters to level up to V").replace(cs, ca));
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }else if(count==429){
                            RecipeTierEvents.callDestinyLevel(e.getEntity().getKiller());
                            count++;
                            ItemMeta meta = p.getInventory().getItemInHand().getItemMeta();
                            List<String> lore = new ArrayList<>();
                            lore = meta.getLore();
                            lore.set(lore.size()-3,ChatColor.GOLD+"Skiting Destiny V ");
                            lore.set(lore.size()-2, (ChatColor.translateAlternateColorCodes('&', "&e&lMAXED OUT")));
                            lore.set(lore.size()-1, ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"10% More luck");
                            lore.add(ChatColor.GOLD+"MAXED BONUS: "+ChatColor.GRAY+"Skiters now only drop refined scales");
                            meta.setLore(lore);
                            p.getInventory().getItemInHand().setItemMeta(meta);
                            try {
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(p.getItemInHand().clone());
                                NBTTagCompound comp = iten.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                lis.get(5).setInt("DestinyCount", count);
                                comp.set("comp", lis);
                                p.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(iten));
                            }catch(Exception ex){
                            }
                        }
                    }
                }
            }
        }
    }
    public static String getDestiny(ItemStack item){
        String id = "";
        String dest = "None";
        int count = 0;
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            id = lis.get(0).getString("id");
            dest = lis.get(4).getString("Destiny");
            count = lis.get(5).getInt("DestinyCount");
        }catch(NullPointerException ex){
            return null;
        }
        if(dest.equals("SKITING")) {
            if (count < 5) {
                count = 0;
            } else if (count < 30) {
                count = 1;
            } else if (count < 80) {
                count = 2;
            } else if (count < 180) {
                count = 3;
            } else if (count < 430) {
                count = 4;
            } else if (count == 430) {
                count = 5;
            }
            if(item.getItemMeta().getLore().get(item.getItemMeta().getLore().size()-1).toLowerCase().contains("duplicated"))
                count = 10;
            System.out.println("duped");
            return dest+"."+count;
        }else if(dest.equals("TAILION")){
            if (count < 150) {
                count = 0;
            } else if (count < 650) {
                count = 1;
            } else if (count < 2150) {
                count = 2;
            } else if (count < 6150) {
                count = 3;
            } else if (count < 15650) {
                count = 4;
            } else if (count == 15650) {
                count = 5;
            }
            if(item.getItemMeta().getLore().get(item.getItemMeta().getLore().size()-1).toLowerCase().contains("duplicated"))
                count = 10;
            return dest+"."+count;
        }
        return "None";
    }
    public static ItemStack calldmg(ItemStack item, Double dmg, Player p){
        String id = " ";
        String dest = " ";
        int count = 0;
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            id = lis.get(0).getString("id");
            dest = lis.get(4).getString("Destiny");
            count = lis.get(5).getInt("DestinyCount");
        }catch(NullPointerException ex){
        }
        if(dest!=null){
            if(dest.equals("TAILION")){
                if(count<150){
                    if(count+(int)Math.round(dmg)>149){
                        RecipeTierEvents.callDestinyLevel(p);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        lore.set(lore.size()-1, (ChatColor.GRAY+"Deal 500 more forest guards to level up to II"));
                        lore.set(lore.size()-2, (ChatColor.DARK_GREEN+"Tailion Destiny I"));
                        lore.add(ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"Take 0.5% less damage for every hitpoint lost (5 max)");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count+(int)Math.round(dmg));
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }else{
            count=count+(int)Math.round(dmg);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore = meta.getLore();
            CharSequence cs = "%";
            CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(150-count)+ChatColor.GRAY;
            lore.set(lore.size()-1, (ChatColor.GRAY+"Deal % more forest guards to level up to I").replace(cs, ca));
            meta.setLore(lore);
            item.setItemMeta(meta);
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                NBTTagCompound comp = iten.getTag();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                lis.get(5).setInt("DestinyCount", count);
                comp.set("comp", lis);
                return CraftItemStack.asBukkitCopy(iten);
            }catch(Exception ex){
            }
        }}else if(count<650){
                    if(count+(int)Math.round(dmg)>649){
                        RecipeTierEvents.callDestinyLevel(p);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal 1500 more forest guards to level up to III"));
                        lore.set(lore.size()-3, (ChatColor.DARK_GREEN+"Tailion Destiny II"));
                        lore.set(lore.size()-1,ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"Take 1.5% less damage for every hitpoint lost (5 max)");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count+(int)Math.round(dmg));
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }else{
                        count=count+(int)Math.round(dmg);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        CharSequence cs = "%";
                        CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(650-count)+ChatColor.GRAY;
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal % more forest guards to level up to II").replace(cs, ca));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count);
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }}else if(count<2150){
                    if(count+(int)Math.round(dmg)>2149){
                        RecipeTierEvents.callDestinyLevel(p);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal 4000 more forest guards to level up to IV"));
                        lore.set(lore.size()-3, (ChatColor.DARK_GREEN+"Tailion Destiny III"));
                        lore.set(lore.size()-1,ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"Take 2% less damage for every hitpoint lost (5 max)");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count+(int)Math.round(dmg));
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }else{
                        count=count+(int)Math.round(dmg);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        CharSequence cs = "%";
                        CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(2150-count)+ChatColor.GRAY;
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal % more forest guards to level up to III").replace(cs, ca));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count);
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }}else if(count<6150){
                    if(count+(int)Math.round(dmg)>6149){
                        RecipeTierEvents.callDestinyLevel(p);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal 9500 more forest guards to level up to V"));
                        lore.set(lore.size()-3, (ChatColor.DARK_GREEN+"Tailion Destiny IV"));
                        lore.set(lore.size()-1,ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"Take 2.5% less damage for every hitpoint lost (5 max)");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count+(int)Math.round(dmg));
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }else{
                        count=count+(int)Math.round(dmg);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        CharSequence cs = "%";
                        CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(6150-count)+ChatColor.GRAY;
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal % more forest guards to level up to IV").replace(cs, ca));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count);
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }}else if(count<15650){
                    if(count+(int)Math.round(dmg)>15649){
                        RecipeTierEvents.callDestinyLevel(p);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal 9500 more forest guards to level up to V"));
                        lore.set(lore.size()-2, (ChatColor.translateAlternateColorCodes('&', "&e&lMAXED OUT")));
                        lore.set(lore.size()-1,ChatColor.RED+"Destiny Bonus "+ChatColor.GRAY+"Take 3.5% less damage for every hitpoint lost (5 max)");
                        lore.add(ChatColor.GOLD+"MAXED BONUS: "+ChatColor.GRAY+"Deal 1% extra damage per hitpoint you have");
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count+(int)Math.round(dmg));
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }else{
                        count=count+(int)Math.round(dmg);
                        ItemMeta meta = item.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore = meta.getLore();
                        CharSequence cs = "%";
                        CharSequence ca = ChatColor.LIGHT_PURPLE+String.valueOf(15650-count)+ChatColor.GRAY;
                        lore.set(lore.size()-2, (ChatColor.GRAY+"Deal % more forest guards to level up to IV").replace(cs, ca));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        try {
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            lis.get(5).setInt("DestinyCount", count);
                            comp.set("comp", lis);
                            return CraftItemStack.asBukkitCopy(iten);
                        }catch(Exception ex){
                        }
                    }}}}
        return item;
    }
    public static boolean checkCubeStat(ItemStack item, String checkid){
        String id = " ";
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            id = lis.get(0).getString("id");
        }catch(NullPointerException ignored){

        }
        if(id.equals("GUARD_CUBE")){
            List<String> list = new ArrayList<>();
            NBTTagList buffs = new NBTTagList();
            try{
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                NBTTagCompound comp = iten.getTag();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                NBTTagCompound compie = lis.get(4);
                buffs = (NBTTagList) compie.get("buffs");
                id = lis.get(0).getString("id");
            }catch(NullPointerException ignored){

            }
            try {
                for (int i = 0; i < buffs.size(); i++) {
                    if (buffs.get(i).getString("buff").equals(checkid)) {
                        return true;
                    }
                }
            }catch(NullPointerException ignored){

            }
        }
        return false;
    }
    public static NBTTagList getCubeStats(ItemStack item){
        String id = " ";
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
            NBTTagCompound comp = iten.getTag();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            id = lis.get(0).getString("id");
        }catch(NullPointerException ignored){

        }
        if(id.equals("GUARD_CUBE")){
            NBTTagList buffs = new NBTTagList();
            try{
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item.clone());
                NBTTagCompound comp = iten.getTag();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                NBTTagCompound compie = lis.get(4);
                buffs = (NBTTagList) compie.get("buffs");
                return buffs;
            }catch(NullPointerException ignored){

            }
        }
        return null;
    }
}
