package com.litf.death.Invens.CraftingMenu;

import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Items.Armor;
import com.litf.death.Items.Items;
import com.litf.death.Items.Misc;
import com.litf.death.Items.Weapons;
import com.sun.org.apache.xpath.internal.operations.Bool;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CraftingMenuEvents implements Listener {
    public static List<List<ItemStack>> recs = new ArrayList<>();
    public static List<List<ItemStack>> hiddenrecs=new ArrayList<>();
    public CraftingMenuEvents(){
        registerRecipes();
    }
    @EventHandler
    public static void opencraft(PlayerInteractEvent e){
        if(e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType() == Material.WORKBENCH && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
                CraftingMenuMain gui = new CraftingMenuMain();
                e.getPlayer().openInventory(gui.getInventory());
            }
        }
    }
    @EventHandler
    public static void craftclick(InventoryClickEvent e){
        if(e.getClickedInventory()!=null) {
            if (e.getInventory().getHolder() instanceof CraftingMenuMain) {
                if (e.getClickedInventory().getHolder() instanceof CraftingMenuMain) {
                    List<Integer> craftslots = Arrays.asList(10, 11, 12, 19, 20, 21, 28, 29, 30);
                    if (!craftslots.contains(e.getSlot())) {
                        e.setCancelled(true);
                    }
                    if(e.getSlot()==33&&e.getClickedInventory().getItem(24).getType().equals(Material.BARRIER)){
                        List<ItemStack> craftgrid = new ArrayList<>();
                        for(int i:craftslots){
                            if(e.getClickedInventory().getItem(i)==null){
                                craftgrid.add(null);
                            }else{
                                ItemStack item = e.getClickedInventory().getItem(i).clone();
                                item.setAmount(1);
                                craftgrid.add(item);
                            }
                        }
                        for(List<ItemStack> rec:recs){
                            String output = null;
                            Boolean fail = true;
                            for(int i=0;i<8;i++){
                                output=String.valueOf(recs.indexOf(rec));
                                if(rec.get(i)==null&&craftgrid.get(i)!=null){
                                    fail=false;
                                }else if(rec.get(i)!=null&&craftgrid.get(i)==null){
                                    fail=false;
                                }else if(rec.get(i)==null&&craftgrid.get(i)==null){
                                }else {
                                    String id = null;
                                    String ida = null;
                                    try {
                                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(craftgrid.get(i));
                                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(rec.get(i));
                                        NBTTagCompound comp = iten.getTag();
                                        NBTTagCompound comm = ited.getTag();
                                        NBTTagList lis = (NBTTagList) comp.get("comp");
                                        NBTTagList lia = (NBTTagList) comm.get("comp");
                                        id = lis.get(0).getString("id");
                                        ida = lia.get(0).getString("id");
                                    } catch (NullPointerException ex) {

                                    }
                                    try{
                                        if (!id.equals(ida)) {
                                            fail = false;
                                        }
                                    }catch(NullPointerException ex){
                                        fail = false;
                                    }
                                }
                            }
                            if(fail&&RecipeTierEvents.isUnlocked(rec, (Player)e.getWhoClicked())){
                                Boolean ust = false;
                                try{
                                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(rec.get(9));
                                    NBTTagCompound comp = iten.getTag();
                                    NBTTagList lis = (NBTTagList) comp.get("comp");
                                    ust = lis.get(1).getBoolean("ust");
                                }catch(NullPointerException ex){

                                }
                                ItemStack item = null;
                                if(!ust) {
                                    e.getClickedInventory().setItem(24, recs.get(Integer.parseInt(output)).get(9));
                                }else{
                                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(rec.get(9));
                                    NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                                    NBTTagList lia = (NBTTagList) comm.get("comp");
                                    String value = lia.get(2).getString("SKULL_VALUE");
                                    if (value == "0") {
                                        item = new ItemStack(rec.get(9).getType(), 1, rec.get(9).getDurability());
                                        ItemMeta meta = item.getItemMeta();
                                        if(rec.get(9).getItemMeta().getLore()!=null) {
                                            meta.setLore(rec.get(9).getItemMeta().getLore());
                                        }
                                        meta.setDisplayName(rec.get(9).getItemMeta().getDisplayName());
                                        item.setItemMeta(meta);
                                    }else{
                                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                                        ItemMeta meta = item.getItemMeta();
                                        if(rec.get(9).getItemMeta().getLore()!=null) {
                                            meta.setLore(rec.get(9).getItemMeta().getLore());
                                        }
                                        meta.setDisplayName(rec.get(9).getItemMeta().getDisplayName());
                                        item.setItemMeta(meta);
                                    }
                                    ItemMeta meta = item.getItemMeta();
                                    if(rec.get(9).getItemMeta().getLore()!=null) {
                                        meta.setLore(rec.get(9).getItemMeta().getLore());
                                    }
                                    meta.setDisplayName(rec.get(9).getItemMeta().getDisplayName());
                                    item.setItemMeta(meta);
                                    net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                                    NBTTagCompound comp = (NBTTagCompound) iten.getTag().clone();
                                    NBTTagList lis = (NBTTagList) comp.get("comp");
                                    NBTTagCompound uuid = new NBTTagCompound();
                                    uuid.setString("uuid", UUID.randomUUID().toString());
                                    lis.add(uuid);
                                    comp.set("comp", lis);
                                    ited.setTag(comp);
                                    item = CraftItemStack.asBukkitCopy(ited);
                                    e.getClickedInventory().setItem(24, item);
                                }
                                RecipeTierEvents.callCraft((Player)e.getWhoClicked(), item);
                                for(int i=0;i<9;i++){
                                    if(rec.get(i)!=null){
                                        if(e.getClickedInventory().getItem(craftslots.get(i)).getAmount()==1){
                                            e.getClickedInventory().setItem(craftslots.get(i), new ItemStack(Material.AIR));
                                        }else{
                                            e.getClickedInventory().getItem(craftslots.get(i)).setAmount(e.getClickedInventory().getItem(craftslots.get(i)).getAmount() - 1);
                                        }}
                                }
                            }
                        }
                        for(List<ItemStack> rec:hiddenrecs){
                            String output = null;
                            Boolean fail = true;
                            for(int i=0;i<8;i++){
                                output=String.valueOf(hiddenrecs.indexOf(rec));
                                if(rec.get(i)==null&&craftgrid.get(i)!=null){
                                    fail=false;
                                }else if(rec.get(i)!=null&&craftgrid.get(i)==null){
                                    fail=false;
                                }else if(rec.get(i)==null&&craftgrid.get(i)==null){
                                }else {
                                    String id = null;
                                    String ida = null;
                                    try {
                                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(craftgrid.get(i));
                                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(rec.get(i));
                                        NBTTagCompound comp = iten.getTag();
                                        NBTTagCompound comm = ited.getTag();
                                        NBTTagList lis = (NBTTagList) comp.get("comp");
                                        NBTTagList lia = (NBTTagList) comm.get("comp");
                                        id = lis.get(0).getString("id");
                                        ida = lia.get(0).getString("id");
                                    } catch (NullPointerException ex) {

                                    }
                                    try{
                                        if (!id.equals(ida)) {
                                            fail = false;
                                        }
                                    }catch(NullPointerException ex){
                                        fail = false;
                                    }
                                }
                            }
                            if(fail){
                                Boolean ust = false;
                                try{
                                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(rec.get(9));
                                    NBTTagCompound comp = iten.getTag();
                                    NBTTagList lis = (NBTTagList) comp.get("comp");
                                    ust = lis.get(1).getBoolean("ust");
                                }catch(NullPointerException ex){

                                }
                                ItemStack item = null;
                                if(!ust) {
                                    e.getClickedInventory().setItem(24, hiddenrecs.get(Integer.parseInt(output)).get(9));
                                }else{
                                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(rec.get(9));
                                    NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                                    NBTTagList lia = (NBTTagList) comm.get("comp");
                                    String value = lia.get(2).getString("SKULL_VALUE");
                                    if (value == "0") {
                                        item = new ItemStack(rec.get(9).getType(), 1, rec.get(9).getDurability());
                                        ItemMeta meta = item.getItemMeta();
                                        if(rec.get(9).getItemMeta().getLore()!=null) {
                                            meta.setLore(rec.get(9).getItemMeta().getLore());
                                        }
                                        meta.setDisplayName(rec.get(9).getItemMeta().getDisplayName());
                                        item.setItemMeta(meta);
                                    }else{
                                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                                        ItemMeta meta = item.getItemMeta();
                                        if(rec.get(9).getItemMeta().getLore()!=null) {
                                            meta.setLore(rec.get(9).getItemMeta().getLore());
                                        }
                                        meta.setDisplayName(rec.get(9).getItemMeta().getDisplayName());
                                        item.setItemMeta(meta);
                                    }
                                    ItemMeta meta = item.getItemMeta();
                                    if(rec.get(9).getItemMeta().getLore()!=null) {
                                        meta.setLore(rec.get(9).getItemMeta().getLore());
                                    }
                                    meta.setDisplayName(rec.get(9).getItemMeta().getDisplayName());
                                    item.setItemMeta(meta);
                                    net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                                    NBTTagCompound comp = (NBTTagCompound) iten.getTag().clone();
                                    NBTTagList lis = (NBTTagList) comp.get("comp");
                                    NBTTagCompound uuid = new NBTTagCompound();
                                    uuid.setString("uuid", UUID.randomUUID().toString());
                                    lis.add(uuid);
                                    comp.set("comp", lis);
                                    ited.setTag(comp);
                                    item = CraftItemStack.asBukkitCopy(ited);
                                    e.getClickedInventory().setItem(24, item);
                                }
                                RecipeTierEvents.callCraft((Player)e.getWhoClicked(), item);
                                for(int i=0;i<9;i++){
                                    if(rec.get(i)!=null){
                                        if(e.getClickedInventory().getItem(craftslots.get(i)).getAmount()==1){
                                            e.getClickedInventory().setItem(craftslots.get(i), new ItemStack(Material.AIR));
                                        }else{
                                            e.getClickedInventory().getItem(craftslots.get(i)).setAmount(e.getClickedInventory().getItem(craftslots.get(i)).getAmount() - 1);
                                        }}
                                }
                            }
                        }
                        //cheack with item in bracket
                    }else if(e.getSlot()==33&&!e.getClickedInventory().getItem(24).getType().equals(Material.BARRIER)){
                        List<ItemStack> craftgrid = new ArrayList<>();
                        for(int i:craftslots){
                            if(e.getClickedInventory().getItem(i)==null){
                                craftgrid.add(null);
                            }else{
                                ItemStack item = e.getClickedInventory().getItem(i).clone();
                                item.setAmount(1);
                                craftgrid.add(item);
                            }
                        }
                        String output = null;
                        Boolean fail = true;
                        for(List<ItemStack> rec:recs){
                            String idc = null;
                            String idac = null;
                            Boolean ust = false;
                            try{
                                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(rec.get(9));
                                net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(e.getClickedInventory().getItem(24));
                                NBTTagCompound comp = iten.getTag();
                                NBTTagCompound comm = ited.getTag();
                                NBTTagList lis = (NBTTagList) comp.get("comp");
                                NBTTagList lia = (NBTTagList) comm.get("comp");
                                idc = lis.get(0).getString("id");
                                idac = lia.get(0).getString("id");
                                ust = lis.get(1).getBoolean("ust");
                            }catch(NullPointerException ex){

                            }
                            if(idc!=null&&idac!=null&&idc.equals(idac)&&!ust&&e.getClickedInventory().getItem(24).getAmount()<64) {
                                for (int i = 0; i < 8; i++) {
                                    output = String.valueOf(recs.indexOf(rec));
                                    if (rec.get(i) == null && craftgrid.get(i) != null) {
                                        fail = false;
                                    } else if (rec.get(i) != null && craftgrid.get(i) == null) {
                                        fail = false;
                                    } else if (rec.get(i) == null && craftgrid.get(i) == null) {
                                    } else {
                                        String id = null;
                                        String ida = null;
                                        try {
                                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(craftgrid.get(i));
                                            net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(craftgrid.get(i));
                                            NBTTagCompound comp = iten.getTag();
                                            NBTTagCompound comm = ited.getTag();
                                            NBTTagList lis = (NBTTagList) comp.get("comp");
                                            NBTTagList lia = (NBTTagList) comm.get("comp");
                                            id = lis.get(0).getString("id");
                                            ida = lia.get(0).getString("id");
                                        } catch (NullPointerException ex) {

                                        }
                                        if (id == null || !id.equals(ida)) {
                                            fail = false;
                                        } else {
                                        }
                                    }
                                }
                                if (fail) {
                                    ItemStack item = e.getClickedInventory().getItem(24);
                                    item.setAmount(item.getAmount()+1);
                                    RecipeTierEvents.callCraft((Player)e.getWhoClicked(), item);
                                    for (int i = 0; i < 9; i++) {
                                        if (rec.get(i) != null) {
                                                if(e.getClickedInventory().getItem(craftslots.get(i)).getAmount()==1){
                                                    e.getClickedInventory().setItem(craftslots.get(i), new ItemStack(Material.AIR));
                                                }else{
                                            e.getClickedInventory().getItem(craftslots.get(i)).setAmount(e.getClickedInventory().getItem(craftslots.get(i)).getAmount() - 1);
                                        }}
                                    }
                                }
                            }
                        }
                    }else if(e.getSlot()==24&&e.getClickedInventory().getItem(24).getType()!=Material.BARRIER){
                        if(e.getClick()==ClickType.LEFT){
                            e.getWhoClicked().setItemOnCursor(e.getCurrentItem());
                            e.getClickedInventory().setItem(24, CraftingMenuMain.createItem(ChatColor.RED+"Crafting Output", Material.BARRIER, Arrays.asList(
                                    ChatColor.GRAY+"This is where your item will",
                                    ChatColor.GRAY+"appear, if the recipe is correct"
                            ), (byte) 0));
                        }
                        if(e.getClick()==ClickType.SHIFT_LEFT){
                            e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                            e.getClickedInventory().setItem(24, CraftingMenuMain.createItem(ChatColor.RED+"Crafting Output", Material.BARRIER, Arrays.asList(
                                    ChatColor.GRAY+"This is where your item will",
                                    ChatColor.GRAY+"appear, if the recipe is correct"
                            ), (byte) 0));
                        }
                        }
                    }
                }
            }
        }
    @EventHandler
    public static void close(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof CraftingMenuMain){
            List<Integer> craftslots = Arrays.asList(10, 11, 12, 19, 20, 21, 28, 29, 30);
            for(int i:craftslots){
                if(e.getInventory().getItem(i)!=null){
                    e.getPlayer().getInventory().addItem(e.getInventory().getItem(i));
                }
            }
        }
    }
    public static void registerRecipes(){
        recs.add(Arrays.asList(
                null,
                Misc.items.get(1).clone(),
                null,
                null,
                Misc.items.get(1).clone(),
                null,
                null,
                Misc.items.get(1).clone(),
                null,
                Weapons.items.get(0)
        ));
        recs.add(Arrays.asList(
                null,
                Misc.items.get(1).clone(),
                null,
                Misc.items.get(1).clone(),
                Misc.items.get(1).clone(),
                Misc.items.get(1).clone(),
                null,
                Misc.items.get(1).clone(),
                null,
                Misc.items.get(2)
        ));
        recs.add(Arrays.asList(
                null,
                Misc.items.get(2).clone(),
                null,
                null,
                Misc.items.get(2).clone(),
                null,
                null,
                Weapons.items.get(0).clone(),
                null,
                Weapons.items.get(1)
        ));
        recs.add(Arrays.asList(
                null,
                Misc.items.get(2).clone(),
                null,
                Misc.items.get(2).clone(),
                Misc.items.get(2).clone(),
                Misc.items.get(2).clone(),
                null,
                Misc.items.get(2).clone(),
                null,
                Misc.items.get(3)
        ));
        recs.add(Arrays.asList(
                null,
                Misc.items.get(3).clone(),
                null,
                Misc.items.get(3).clone(),
                Weapons.items.get(1),
                Misc.items.get(3).clone(),
                null,
                Weapons.items.get(0).clone(),
                null,
                Weapons.items.get(2)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                null,
                null,
                null,
                Armor.items.get(0)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                null,
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Armor.items.get(1)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                null,
                Misc.items.get(4).clone(),
                Armor.items.get(2)
        ));
        recs.add(Arrays.asList(
                null,
                null,
                null,
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                null,
                Misc.items.get(4).clone(),
                Armor.items.get(3)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(3).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(5)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(5).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Items.items.get(0)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Items.items.get(1)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(4).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(4).clone(),
                Misc.items.get(8)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(7).clone(),
                null,
                Misc.items.get(7).clone(),
                null,
                Misc.items.get(8).clone(),
                null,
                Misc.items.get(7).clone(),
                null,
                Misc.items.get(7).clone(),
                Items.items.get(2)
        ));
        recs.add(Arrays.asList(
                null,
                null,
                null,
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(5).clone(),
                Misc.items.get(8).clone(),
                Items.items.get(3)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(0).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(4)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(1).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(5)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(2).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(6)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(3).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Armor.items.get(7)
        ));
        recs.add(Arrays.asList(
                null,
                Misc.items.get(7).clone(),
                null,
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                null,
                Misc.items.get(7).clone(),
                null,
                Misc.items.get(11)
        ));
        recs.add(Arrays.asList(
                null,
                Misc.items.get(11).clone(),
                null,
                null,
                Misc.items.get(11).clone(),
                null,
                null,
                Misc.items.get(11).clone(),
                null,
                Misc.items.get(9)
        ));
        recs.add(Arrays.asList(
                Weapons.items.get(2),
                Weapons.items.get(2),
                null,
                null,
                Misc.items.get(9).clone(),
                null,
                null,
                Misc.items.get(9).clone(),
                null,
                Weapons.items.get(3)
        ));
        recs.add(Arrays.asList(
                null,
                null,
                null,
                Misc.items.get(9).clone(),
                Misc.items.get(9).clone(),
                Misc.items.get(8).clone(),
                null,
                null,
                null,
                Items.items.get(5)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(12).clone(),
                Misc.items.get(14)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(8).clone(),
                Misc.items.get(14).clone(),
                Misc.items.get(8).clone(),
                Misc.items.get(14).clone(),
                Misc.items.get(5).clone(),
                Misc.items.get(14).clone(),
                Misc.items.get(8).clone(),
                Misc.items.get(14).clone(),
                Misc.items.get(8).clone(),
                Items.items.get(8)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(14).clone(),
                null,
                Misc.items.get(14).clone(),
                Misc.items.get(14).clone(),
                Misc.items.get(9).clone(),
                Misc.items.get(14).clone(),
                Misc.items.get(14).clone(),
                null,
                Misc.items.get(14).clone(),
                Items.items.get(9)
        ));
        //Remove when 115 misc items exist
        //Underground
        recs.add(Arrays.asList(
                Misc.items.get(15).clone(),
                Misc.items.get(15).clone(),
                Misc.items.get(15).clone(),
                null,
                Weapons.items.get(0).clone(),
                null,
                null,
                Weapons.items.get(0).clone(),
                null,
                Items.items.get(11)
        ));
        recs.add(Arrays.asList(
                Misc.items.get(17).clone(),
                Misc.items.get(17).clone(),
                Misc.items.get(17).clone(),
                null,
                Items.items.get(11),
                null,
                null,
                Weapons.items.get(0).clone(),
                null,
                Items.items.get(12)
        ));
        //Hidden recs
        hiddenrecs.add(Arrays.asList(
                null,
                null,
                null,
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(10).clone(),
                Items.items.get(4)
        ));
        hiddenrecs.add(Arrays.asList(
                null,
                null,
                null,
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(7).clone(),
                Misc.items.get(6).clone(),
                Items.items.get(7)
        ));
        hiddenrecs.add(Arrays.asList(
                null,
                Misc.items.get(14).clone(),
                null,
                null,
                Misc.items.get(14).clone(),
                null,
                null,
                Misc.items.get(13).clone(),
                null,
                Weapons.items.get(5)
        ));
    }
}
