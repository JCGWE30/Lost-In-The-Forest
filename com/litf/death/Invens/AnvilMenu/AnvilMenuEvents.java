package com.litf.death.Invens.AnvilMenu;

import com.litf.death.Events.DestinyEvents;
import com.litf.death.Events.UtilEvents;
import com.litf.death.Invens.CraftingMenu.CraftingMenuMain;
import com.litf.death.Items.Armor;
import com.litf.death.Items.Items;
import com.litf.death.Items.Weapons;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.omg.CORBA.NVList;
import sun.reflect.ReflectionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnvilMenuEvents implements Listener {
    public static List<List<ItemStack>> recs = new ArrayList<>();
    public AnvilMenuEvents(){
        initrecs();
    }
    @EventHandler
    public static void opencraft(PlayerInteractEvent e){
        if(e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType() == Material.ANVIL && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
                AnvilMenuMain gui = new AnvilMenuMain();
                e.getPlayer().openInventory(gui.getInventory());
            }
        }
    }
    @EventHandler
    public static void close(InventoryCloseEvent e){
        if(e.getInventory().getHolder() instanceof AnvilMenuMain){
            if(e.getInventory().getItem(19)!=null){
                e.getPlayer().getInventory().addItem(e.getInventory().getItem(19));
            }
            if(e.getInventory().getItem(25)!=null){
                e.getPlayer().getInventory().addItem(e.getInventory().getItem(25));
            }
            if(e.getInventory().getItem(40).getType()!=Material.BARRIER&&e.getInventory().getItem(25)==null&&e.getInventory().getItem(19)==null){
                e.getPlayer().getInventory().addItem(e.getInventory().getItem(40));
            }
        }
    }
    @EventHandler
    public static void click(InventoryClickEvent e){
        if(e.getInventory().getHolder() instanceof AnvilMenuMain) {
            if (e.getClickedInventory() != null) {
                if (e.getClickedInventory().getHolder() instanceof AnvilMenuMain) {
                    if(e.getSlot()!=19&&e.getSlot()!=25){
                        e.setCancelled(true);
                    }else{
                        if(e.getClickedInventory().getItem(e.getSlot())!=null){
                            e.setCancelled(true);
                            e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                            e.getClickedInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                            updateloks(e);
                        }
                    }
                    if(e.getSlot()==31&&e.getInventory().getItem(40).getType()!=Material.BARRIER) {
                        String id = " ";
                        try {
                            ItemStack item = e.getInventory().getItem(40).clone();
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            id = lis.get(0).getString("id");
                        }catch(NullPointerException ignored){

                        }
                        if (id.equals("GUARD_CUBE")) {
                            ItemStack item = e.getInventory().getItem(40).clone();
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            NBTTagCompound bufcomp = new NBTTagCompound();
                            NBTTagList buffs=null;
                            NBTTagCompound buff = new NBTTagCompound();
                            if(UtilEvents.getId(e.getInventory().getItem(25)).equals("CUBE_ROLLER")){
                                buff.setString("buff", "ROLLER");
                            }else if(UtilEvents.getId(e.getInventory().getItem(25)).equals("CUBE_NEURAL")){
                                buff.setString("buff", "LINK");
                            }else if(UtilEvents.getId(e.getInventory().getItem(25)).equals("CUBE_POWER")){
                                buff.setString("buff", "PSHIELD");
                            }
                            if(DestinyEvents.getCubeStats(e.getInventory().getItem(19))==null){
                                System.out.println("Fail");
                                 buffs= new NBTTagList();
                                buffs.add(buff);
                                bufcomp.set("buffs", buffs);
                                lis.add(bufcomp);
                            }else{
                                System.out.println("Failsss");
                                buffs=DestinyEvents.getCubeStats(e.getInventory().getItem(19));
                                buffs.add(buff);
                                bufcomp.set("buffs", buffs);
                                lis.a(4, bufcomp);
                            }
                            comp.set("comp", lis);
                            iten.setTag(comp);
                            item = CraftItemStack.asBukkitCopy(iten);
                            e.getInventory().setItem(19, new ItemStack(Material.AIR));
                            e.getInventory().setItem(25, new ItemStack(Material.AIR));
                            e.getInventory().setItem(40, item);
                        } else {
                            e.getInventory().setItem(19, new ItemStack(Material.AIR));
                            e.getInventory().setItem(25, new ItemStack(Material.AIR));
                            ItemStack item = e.getInventory().getItem(40).clone();
                            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                            NBTTagCompound comp = iten.getTag();
                            NBTTagList lis = (NBTTagList) comp.get("comp");
                            NBTTagCompound dest = new NBTTagCompound();
                            NBTTagCompound destc = new NBTTagCompound();
                            if (item.getItemMeta().getLore().get(item.getItemMeta().getLore().size() - 2).contains("Skiting")) {
                                dest.setString("Destiny", "SKITING");
                            } else {
                                dest.setString("Destiny", "TAILION");
                            }
                            destc.setInt("DestinyCount", 0);
                            lis.add(dest);
                            lis.add(destc);
                            comp.set("comp", lis);
                            iten.setTag(comp);
                            item = CraftItemStack.asBukkitCopy(iten);
                            e.getInventory().setItem(40, item);
                        }
                    }
                    if(e.getSlot()==40&&e.getInventory().getItem(40).getType()!=Material.BARRIER&&e.getInventory().getItem(19)==null&&e.getInventory().getItem(25)==null){
                        e.getWhoClicked().getInventory().addItem(e.getInventory().getItem(40));
                        e.getInventory().setItem(40,createItem(ChatColor.RED+"Anvil Output", Material.BARRIER, Arrays.asList(
                                ChatColor.GRAY+"if the 2 items above can be combined",
                                ChatColor.GRAY+"then the output item will appear in this slot"), (byte) 0));
                    }
                }else{
                    if(e.getCurrentItem().getType()!=Material.AIR) {
                        e.setCancelled(true);
                        if (e.getInventory().getItem(19)==null) {
                            e.getInventory().setItem(19, e.getCurrentItem());
                            e.getClickedInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                            updateloks(e);
                        } else {
                            e.getInventory().setItem(25, e.getCurrentItem());
                            e.getClickedInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                            updateloks(e);
                        }
                    }
                }
            }
        }
    }
    public static void updateloks(InventoryClickEvent e){
        if(e.getInventory().getItem(40).getType()!=Material.BARRIER&&e.getInventory().getItem(19)==null&&e.getInventory().getItem(25)==null)
            return;
        List<ItemStack> stacks=Arrays.asList(
                e.getInventory().getItem(19),
                e.getInventory().getItem(25)
        );
        if(stacks.get(0)!=null){
            e.getInventory().setItem(28, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
            e.getInventory().setItem(37, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
            e.getInventory().setItem(38, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
            e.getInventory().setItem(39, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
        }else{
            e.getInventory().setItem(28, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(37, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(38, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(39, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(40,createItem(ChatColor.RED+"Anvil Output", Material.BARRIER, Arrays.asList(
                    ChatColor.GRAY+"if the 2 items above can be combined",
                    ChatColor.GRAY+"then the output item will appear in this slot"), (byte) 0));
        }
        if(stacks.get(1)!=null){
            e.getInventory().setItem(34, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
            e.getInventory().setItem(41, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
            e.getInventory().setItem(42, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
            e.getInventory().setItem(43, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 1));
        }else{
            e.getInventory().setItem(34, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(41, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(42, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(43, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
            e.getInventory().setItem(40,createItem(ChatColor.RED+"Anvil Output", Material.BARRIER, Arrays.asList(
                    ChatColor.GRAY+"if the 2 items above can be combined",
                    ChatColor.GRAY+"then the output item will appear in this slot"), (byte) 0));
        }
        if(stacks.get(0)!=null&&stacks.get(1)!=null) {
            boolean smooth = false;
            Integer output = 0;
            for (List<ItemStack> rec : recs) {
                boolean ran = true;
                output= recs.indexOf(rec);
                for (int i = 0; i < 2; i++){
                    String id = "null";
                    String ida = "null";
                try {
                    net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(rec.get(i));
                    NBTTagCompound comm = ited.getTag();
                    NBTTagList lia = (NBTTagList) comm.get("comp");
                    ida = lia.get(0).getString("id");
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(stacks.get(i));
                    NBTTagCompound comp = iten.getTag();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    id = lis.get(0).getString("id");
                } catch (NullPointerException ignored) {

                }
                if(!id.equals(ida)){
                    ran=false;
                }

            }
                if(UtilEvents.getId(e.getInventory().getItem(25)).equals("TYRA_DUPER")) {
                    ItemStack item = e.getInventory().getItem(19).clone();
                    if (DestinyEvents.getDestiny(item).contains("5")) {
                        if (!item.getItemMeta().getLore().get(item.getItemMeta().getLore().size() - 1).toLowerCase().contains("duplicated")) {
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            lore.add(ChatColor.DARK_AQUA + "Duplicated Destiny");
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                            smooth = true;
                        }
                    }
                }
                if(ran){
                    if(output==0||output==11||output==13){
                        ItemStack item = e.getInventory().getItem(19).clone();
                        if(DestinyEvents.getDestiny(item)=="None") {
                            smooth=true;
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            lore.add(" ");
                            lore.add(ChatColor.DARK_GREEN + "Skiting Destiny 0");
                            lore.add(ChatColor.GRAY + "Kill " + ChatColor.LIGHT_PURPLE + "5 " + ChatColor.GRAY + "more skiters to level up to I");
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }else if(output<=4){
                        ItemStack item = e.getInventory().getItem(19).clone();
                        if(DestinyEvents.getDestiny(item)=="None") {
                            smooth=true;
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            lore.add(" ");
                            lore.add(ChatColor.DARK_GREEN + "Tailion Destiny 0");
                            lore.add(ChatColor.GRAY + "Deal " + ChatColor.LIGHT_PURPLE + "150 " + ChatColor.GRAY + "more forest guards to level up to I");
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }else if(output==5){
                            ItemStack item = e.getInventory().getItem(19).clone();
                        if (!DestinyEvents.checkCubeStat(item, "LINK")) {
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            String lastline =ChatColor.GRAY + "Buffs: ";
                            lastline=lastline+ChatColor.BLUE + "Neural link ";
                            if (DestinyEvents.checkCubeStat(item, "ROLLER")) {
                                lastline = lastline + ChatColor.DARK_PURPLE + "High Roller ";
                            }
                            if (DestinyEvents.checkCubeStat(item, "PSHIELD")) {
                                lastline = lastline + ChatColor.DARK_PURPLE + "Power Shield";
                            }
                            lore.set(lore.size()-1, lastline);
                            smooth = true;
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }else if(output<10&&5<output){
                        ItemStack item = e.getInventory().getItem(19).clone();
                        if(DestinyEvents.getDestiny(item)=="None") {
                            smooth=true;
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            lore.add(" ");
                            lore.add(ChatColor.DARK_GREEN + "Tailion Destiny 0");
                            lore.add(ChatColor.GRAY + "Deal " + ChatColor.LIGHT_PURPLE + "150 " + ChatColor.GRAY + "more forest guards to level up to I");
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }else if(output==10) {
                        ItemStack item = e.getInventory().getItem(19).clone();
                        if (!DestinyEvents.checkCubeStat(item, "ROLLER")) {
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            String lastline =ChatColor.GRAY + "Buffs: ";
                            if (DestinyEvents.checkCubeStat(item, "LINK")) {
                                lastline = lastline + ChatColor.BLUE + "Neural link ";
                            }
                            lastline=lastline+ChatColor.DARK_PURPLE + "Hign Roller ";
                            if (DestinyEvents.checkCubeStat(item, "PSHIELD")) {
                                lastline = lastline + ChatColor.DARK_PURPLE + "Power Shield";
                            }
                            lore.set(lore.size()-1, lastline);
                            smooth = true;
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }else if(output==12) {
                        ItemStack item = e.getInventory().getItem(19).clone();
                        if (!DestinyEvents.checkCubeStat(item, "PSHIELD")) {
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            String lastline = ChatColor.GRAY + "Buffs: ";
                            if (DestinyEvents.checkCubeStat(item, "LINK")) {
                                lastline = lastline + ChatColor.BLUE + "Neural link ";
                            }
                            if (DestinyEvents.checkCubeStat(item, "ROLLER")){
                                lastline = lastline + ChatColor.DARK_PURPLE + "High Roller ";
                        }
                            lastline=lastline+ChatColor.DARK_PURPLE + "Power Shield";
                            lore.set(lore.size()-1, lastline);
                            smooth = true;
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }else if(output<18&&13<output){
                        ItemStack item = e.getInventory().getItem(19).clone();
                        if(DestinyEvents.getDestiny(item)=="None") {
                            smooth=true;
                            ItemMeta meta = item.getItemMeta();
                            List<String> lore = meta.getLore();
                            lore.add(" ");
                            lore.add(ChatColor.DARK_GREEN + "Tailion Destiny 0");
                            lore.add(ChatColor.GRAY + "Deal " + ChatColor.LIGHT_PURPLE + "150 " + ChatColor.GRAY + "more forest guards to level up to I");
                            meta.setLore(lore);
                            item.setItemMeta(meta);
                            e.getInventory().setItem(40, item);
                        }
                    }
                }
        }
            if(smooth){
                e.getInventory().setItem(28, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(37, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(38, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(39, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(34, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(41, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(42, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
                e.getInventory().setItem(43, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 13));
            }else{
                e.getInventory().setItem(28, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(37, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(38, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(39, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(34, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(41, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(42, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
                e.getInventory().setItem(43, createItem(" ", Material.STAINED_GLASS_PANE, null, (byte) 14));
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
    public static void initrecs(){
        recs.add(Arrays.asList(
                Weapons.items.get(2),
                Items.items.get(0),
                Weapons.items.get(2)
        ));
        recs.add(Arrays.asList(
                Armor.items.get(0),
                Items.items.get(1),
                Armor.items.get(0).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(1),
                Items.items.get(1),
                Armor.items.get(1).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(2),
                Items.items.get(1),
                Armor.items.get(2).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(3),
                Items.items.get(1),
                Armor.items.get(3).clone()
        ));
        recs.add(Arrays.asList(
                Items.items.get(2),
                Items.items.get(3),
                Items.items.get(2).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(4),
                Items.items.get(1),
                Armor.items.get(4).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(5),
                Items.items.get(1),
                Armor.items.get(5).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(6),
                Items.items.get(1),
                Armor.items.get(6).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(7),
                Items.items.get(1),
                Armor.items.get(7).clone()
        ));
        recs.add(Arrays.asList(
                Items.items.get(2),
                Items.items.get(4),
                Items.items.get(2).clone()
        ));
        recs.add(Arrays.asList(
                Weapons.items.get(3),
                Items.items.get(0),
                Weapons.items.get(3)
        ));
        recs.add(Arrays.asList(
                Items.items.get(2),
                Items.items.get(7),
                Items.items.get(2).clone()
        ));
        recs.add(Arrays.asList(
                Weapons.items.get(5),
                Items.items.get(0),
                Weapons.items.get(5)
        ));
        recs.add(Arrays.asList(
                Armor.items.get(8),
                Items.items.get(1),
                Armor.items.get(8).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(9),
                Items.items.get(1),
                Armor.items.get(9).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(10),
                Items.items.get(1),
                Armor.items.get(10).clone()
        ));
        recs.add(Arrays.asList(
                Armor.items.get(11),
                Items.items.get(1),
                Armor.items.get(11).clone()
        ));
    }
}
