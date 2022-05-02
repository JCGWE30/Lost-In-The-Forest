package com.litf.death.Invens.TyrantBossLoot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TyrantLoot implements InventoryHolder {
    private static Inventory inv;
    public TyrantLoot(List<ItemStack> strs){
        inv = Bukkit.createInventory(this,54, "Forest Tyrant Loot");
        init(strs);
    }
    private static void init(List<ItemStack> strs){
        boolean bonus = false;
        if(strs.size()>2)
            bonus=true;
        for(int i=0;i<54;i++){
            switch(i){
                case 14:
                case 15:
                case 16:
                case 23:
                case 25:
                case 32:
                case 33:
                case 34:
                    if(bonus) {
                        inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 5));
                    }else{
                        inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                    }
                    break;
                case 20:
                    inv.setItem(i,strs.get(0));
                    break;
                case 22:
                    if(strs.size()>1) {
                        inv.setItem(i, strs.get(1));
                    }else{
                        inv.setItem(i, createItem(ChatColor.AQUA+"No second drop", Material.BEDROCK, Collections.singletonList(ChatColor.GRAY + "Unlucky"),(byte)0));
                    }
                    break;
                case 24:
                    if(strs.size()>2) {
                        inv.setItem(i, strs.get(2));
                    }else{
                        inv.setItem(i, createItem(ChatColor.AQUA+"You got no bonus drop this time :(", Material.BEDROCK, Arrays.asList(
                                ChatColor.GRAY+"Bonus drops can be obtained by luck and damage",
                                ChatColor.GRAY+"They are very powerful drops!",
                                ChatColor.GRAY+"However if you claim a bonus drop you",
                                ChatColor.GRAY+"cannot claim any other rewards"
                        ),(byte)0));
                    }
                    break;
                case 39:
                    if(bonus) {
                        inv.setItem(i, createItem(ChatColor.YELLOW + "Claim your regular rewards", Material.CHEST, Arrays.asList(
                                ChatColor.GREEN + "Claim your rewards!"
                        ), (byte) 0));
                    }else{
                        inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                    }
                    break;
                case 40:
                    if(!bonus){
                        inv.setItem(i, createItem(ChatColor.YELLOW + "Claim your regular rewards", Material.CHEST, Arrays.asList(
                                ChatColor.GREEN + "Claim your rewards!"
                        ), (byte) 0));
                    }else{
                        inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                    }
                    break;
                case 41:
                    if(bonus) {
                        inv.setItem(i, createItem(ChatColor.YELLOW + "Claim your Bonus reward", Material.EMERALD_BLOCK, Arrays.asList(
                                ChatColor.GREEN + "Claim your Bonus reward!",
                                ChatColor.RED + "WARNING: If you claim this",
                                ChatColor.RED + "You cannot claim any other rewards"
                        ), (byte) 0));
                    }else{
                        inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
                    }
                    break;
                default:
                    inv.setItem(i, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 7));
            }
            }
        }
    public static ItemStack createItem(String name, Material mat, List<String> lore, Byte b){
        ItemStack item = new ItemStack(mat, 1, b);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createAir(){
        ItemStack item = new ItemStack(Material.AIR, 1);
        return item;
    }
    @Override
    public Inventory getInventory() {
        return inv;
    }
}