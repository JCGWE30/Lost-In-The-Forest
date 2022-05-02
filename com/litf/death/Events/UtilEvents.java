package com.litf.death.Events;

import com.litf.death.Entits.NPCS.NPC;
import com.litf.death.Packeets.PacketReader;
import com.litf.death.Entits.Tyrant.ForestTyrant;
import com.litf.death.Invens.CraftingMenu.RecipeBook.RecipeBookPageHidden;
import com.litf.death.Invens.TabletMenu.TabletMenuMain;
import com.litf.death.Items.*;
import com.litf.death.Main;
import com.litf.death.Ranks.Calls;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class UtilEvents implements Listener {
    public static HashMap<Player, List<ItemStack>> tryadrops = new HashMap<>();
    public static HashMap<Player, Double> tryadamage = new HashMap<>();
    public static HashMap<Player, List<ItemStack>> restrectidpickups = new HashMap<>();
    @EventHandler
    public static void onplace(BlockPlaceEvent e){
        if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void survnobreak(BlockBreakEvent e){
        if(e.getPlayer().getGameMode()!= GameMode.CREATIVE){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void keyevents(PlayerInteractAtEntityEvent e){
        if(e.getRightClicked() instanceof ArmorStand){
            e.setCancelled(true);
            try {
                if (FTyrantEvents.key.equals(e.getRightClicked())) {
                    if (e.getPlayer().getInventory().getItemInHand() != null) {
                        if (getId(e.getPlayer().getItemInHand()).equals("GUARD_KEY")) {
                            RecipeTierEvents.callSummon(e.getPlayer(), EntityType.IRON_GOLEM);
                            Date date = new Date();
                            Date result = new Date(Main.serverdata.getConfig().getLong("FTyrantData.LastSpawn") - date.getTime() + 180000);
                            if (result.getTime() <= 0) {
                                ItemStack item = e.getPlayer().getItemInHand();
                                String lastlore = item.getItemMeta().getLore().get(item.getItemMeta().getLore().size() - 1);
                                String[] split = lastlore.split(" ");
                                split = split[1].split("/");
                                int uses = Integer.valueOf(split[0]);
                                tryadamage.put(e.getPlayer(), 150d);
                                if (uses == 1) {
                                    e.getPlayer().getInventory().setItemInHand(new ItemStack(Material.AIR));
                                    e.getPlayer().playEffect(FTyrantEvents.key.getLocation().add(0, 2, 0), Effect.EXPLOSION_LARGE, 0);
                                    e.getPlayer().playEffect(FTyrantEvents.key.getLocation().add(0, 1, 0), Effect.EXPLOSION_LARGE, 0);
                                    ForestTyrant ent = new ForestTyrant(e.getPlayer().getWorld());
                                    ForestTyrant.EntityTypes.spawnEntity(ent, FTyrantEvents.key.getEyeLocation());
                                    SpawnEvents.tynts.add(ent);
                                    DamageEvents.mobhp.put(ent.getBukkitEntity(), 250d);
                                    FTyrantEvents.disableFTryant();
                                } else {
                                    uses--;
                                    ItemMeta meta = item.getItemMeta();
                                    List<String> lore = meta.getLore();
                                    lore.set(lore.size() - 1, ChatColor.DARK_GRAY + "Uses: " + uses + "/5");
                                    meta.setLore(lore);
                                    item.setItemMeta(meta);
                                    e.getPlayer().getInventory().setItemInHand(item);
                                    e.getPlayer().playEffect(FTyrantEvents.key.getLocation().add(0, 2, 0), Effect.EXPLOSION_LARGE, 0);
                                    e.getPlayer().playEffect(FTyrantEvents.key.getLocation().add(0, 1, 0), Effect.EXPLOSION_LARGE, 0);
                                    ForestTyrant ent = new ForestTyrant(e.getPlayer().getWorld());
                                    ForestTyrant.EntityTypes.spawnEntity(ent, FTyrantEvents.key.getEyeLocation());
                                    SpawnEvents.tynts.add(ent);
                                    DamageEvents.mobhp.put(ent.getBukkitEntity(), 250d);
                                    FTyrantEvents.disableFTryant();
                                }
                            }
                        }
                    }
                } else if (FTyrantEvents.tyradrop.equals(e.getRightClicked())) {
                    if (tryadrops.containsKey(e.getPlayer())) {
                        FTyrantEvents.DropTyeants(e.getPlayer(), e, 0, tryadrops.get(e.getPlayer()));
                        tryadrops.remove(e.getPlayer());
                    } else {
                        e.getPlayer().sendMessage(ChatColor.RED + "You dont have any drops to claim");
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                    }
                }
            }catch (NullPointerException ignored){}
        }
        if(e.getRightClicked() instanceof EntityPlayer){
            NPC.haveNPCSwing((EntityPlayer) e.getRightClicked(), e.getPlayer());
        }
    }
    public static void checkplayers(){
        if(tryadrops.keySet().isEmpty()){
            if(FTyrantEvents.tyradrop!=null){
                FTyrantEvents.tyradrop.getWorld().getBlockAt(FTyrantEvents.tyradrop.getLocation().add(0,1,0)).setType(Material.AIR);
                FTyrantEvents.tyradrop.remove();

            }
        }
    }
    @EventHandler
    public static void antidamage(PlayerItemDamageEvent e){
            e.setDamage(0);
    }
    @EventHandler
    public static void nostarve(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
    }
    @EventHandler
    public static void logjoindate(PlayerJoinEvent e){
        if (Main.data.getConfig().get(e.getPlayer().getUniqueId().toString()+".JoinDate")==null) {
            Date date = new Date();
            Main.data.getConfig().set(e.getPlayer().getUniqueId().toString()+".JoinDate", date.getTime());
            Main.data.saveConfig();
        }
        if(NPC.getNPCs()==null)
            return;
        if(NPC.getNPCs().isEmpty())
            return;
        NPC.addJoinPacket(e.getPlayer());
        PacketReader reader = new PacketReader();
        reader.inject(e.getPlayer());
    }
    @EventHandler
    public static void onquit(PlayerQuitEvent e){
        PacketReader reader = new PacketReader();
        reader.uninject(e.getPlayer());
    }
    @EventHandler
    public static void changegamemode(PlayerGameModeChangeEvent e){
        if(Calls.getRank(e.getPlayer())>=3){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void till(PlayerInteractEvent e){
        if(e.getPlayer().getInventory().getItemInHand()!=null){
            try {
                if (getId(e.getPlayer().getInventory().getItemInHand()).equals("TYRA_SPIRIT")) {
                    e.setCancelled(true);
                    e.getPlayer().getInventory().setItemInHand(UtilEvents.getItemWithID("TYRA_SPIRIT"));
                }
                if (getId(e.getPlayer().getInventory().getItemInHand()).equals("TYRA_TABLET")) {
                    TabletMenuMain gui = new TabletMenuMain();
                    e.getPlayer().openInventory(gui.getInventory());
                    e.setCancelled(true);
                }
            }catch(NullPointerException ignored){}
        }
        if(e.getAction()== Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType()==Material.GRASS){
                if(e.getPlayer().getGameMode()==GameMode.SURVIVAL)
                e.setCancelled(true);
            }
        }
        if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK){
            if(e.getPlayer().getItemInHand()!=null){
                try {
                    if (getId(e.getPlayer().getItemInHand()).equals("SCALE_RNG")) {
                        e.setCancelled(true);
                        RecipeBookPageHidden gui = new RecipeBookPageHidden(Arrays.asList(
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
                        e.getPlayer().openInventory(gui.getInventory());
                    }else if(getId(e.getPlayer().getItemInHand()).equals("TYRA_FIERCE")){
                        e.setCancelled(true);
                        RecipeBookPageHidden gui = new RecipeBookPageHidden(Arrays.asList(
                                null,
                                null,
                                null,
                                Misc.items.get(7).clone(),
                                Misc.items.get(7).clone(),
                                Misc.items.get(7).clone(),
                                Misc.items.get(7).clone(),
                                Misc.items.get(7).clone(),
                                Items.items.get(6).clone(),
                                Items.items.get(7)
                        ));
                        e.getPlayer().openInventory(gui.getInventory());
                    }else if(getId(e.getPlayer().getItemInHand()).equals("TYRA_HANDLE")){
                        e.setCancelled(true);
                        RecipeBookPageHidden gui = new RecipeBookPageHidden(Arrays.asList(
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
                        e.getPlayer().openInventory(gui.getInventory());
                    }
                }catch(NullPointerException ignored){}
            }
        }
    }
    @EventHandler
    public static void weather(WeatherChangeEvent e){
        e.getWorld().setWeatherDuration(0);
    }
    public static String getId(ItemStack item){
        try {
            net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
            NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
            NBTTagList lis = (NBTTagList) comp.get("comp");
            String id = lis.get(0).getString("id");
            return id;
        }catch(NullPointerException ex){
        }
        return null;
    }
    public static ItemStack getItemWithID(String idc){
        for(ItemStack item :Weapons.items){
                try {
                    net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                    NBTTagList lis = (NBTTagList) comp.get("comp");
                    String id = lis.get(0).getString("id");
                if (id.equals(idc)) {
                    boolean ust = lis.get(1).getBoolean("ust");
                    if (!ust) {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        return item;
                    } else {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        comp = (NBTTagCompound) iten.getTag().clone();
                        lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound uuid = new NBTTagCompound();
                        uuid.setString("uuid", UUID.randomUUID().toString());
                        lis.add(uuid);
                        comp.set("comp", lis);
                        ited.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(ited);
                        return item.clone();
                    }
                }
                }catch(NullPointerException ex){
                }
        }
        for(ItemStack item : Misc.items){
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc)) {
                    boolean ust = lis.get(1).getBoolean("ust");
                    if (!ust) {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        ItemStack itema = null;
                        if (value == "0") {
                            itema = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().hasLore()) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            itema.setItemMeta(meta);
                        } else {
                           itema = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            itema.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        itema.setItemMeta(meta);
                        return itema;
                    } else {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        comp = (NBTTagCompound) iten.getTag().clone();
                        lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound uuid = new NBTTagCompound();
                        uuid.setString("uuid", UUID.randomUUID().toString());
                        lis.add(uuid);
                        comp.set("comp", lis);
                        ited.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(ited);
                        return item.clone();
                    }
                }
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Items.items){
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc)) {
                    boolean ust = lis.get(1).getBoolean("ust");
                    if (!ust) {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        return item;
                    } else {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        comp = (NBTTagCompound) iten.getTag().clone();
                        lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound uuid = new NBTTagCompound();
                        uuid.setString("uuid", UUID.randomUUID().toString());
                        lis.add(uuid);
                        comp.set("comp", lis);
                        ited.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(ited);
                        return item.clone();
                    }
                }
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Deliveries.items){
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc)) {
                    boolean ust = lis.get(1).getBoolean("ust");
                    if (!ust) {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        return item;
                    } else {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        comp = (NBTTagCompound) iten.getTag().clone();
                        lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound uuid = new NBTTagCompound();
                        uuid.setString("uuid", UUID.randomUUID().toString());
                        lis.add(uuid);
                        comp.set("comp", lis);
                        ited.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(ited);
                        return item.clone();
                    }
                }
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Armor.items){
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc)) {
                    boolean ust = lis.get(1).getBoolean("ust");
                    if (!ust) {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        return item;
                    } else {
                        NBTTagCompound comm = (NBTTagCompound) iten.getTag().clone();
                        NBTTagList lia = (NBTTagList) comm.get("comp");
                        String value = lia.get(2).getString("SKULL_VALUE");
                        if (value == "0") {
                            item = new ItemStack(item.getType(), 1, item.getDurability());
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                            ItemMeta meta = item.getItemMeta();
                            if (item.getItemMeta().getLore() != null) {
                                meta.setLore(item.getItemMeta().getLore());
                            }
                            meta.setDisplayName(item.getItemMeta().getDisplayName());
                            item.setItemMeta(meta);
                        }
                        ItemMeta meta = item.getItemMeta();
                        if (item.getItemMeta().getLore() != null) {
                            meta.setLore(item.getItemMeta().getLore());
                        }
                        meta.setDisplayName(item.getItemMeta().getDisplayName());
                        item.setItemMeta(meta);
                        net.minecraft.server.v1_8_R3.ItemStack ited = CraftItemStack.asNMSCopy(item);
                        comp = (NBTTagCompound) iten.getTag().clone();
                        lis = (NBTTagList) comp.get("comp");
                        NBTTagCompound uuid = new NBTTagCompound();
                        uuid.setString("uuid", UUID.randomUUID().toString());
                        lis.add(uuid);
                        comp.set("comp", lis);
                        ited.setTag(comp);
                        item = CraftItemStack.asBukkitCopy(ited);
                        return item.clone();
                    }
                }
            }catch(NullPointerException ex){
            }
        }
        return null;
    }
    public static Integer getItemCata(String idc){
        for(ItemStack item :Weapons.items){
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc))
                    return 0;
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Misc.items) {
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc))
                    return 3;
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Items.items) {
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc))
                    return 1;
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Deliveries.items) {
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc))
                    return 3;
            }catch(NullPointerException ex){
            }
        }
        for(ItemStack item : Armor.items) {
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(item);
                NBTTagCompound comp = (iten.hasTag()) ? iten.getTag() : new NBTTagCompound();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                String id = lis.get(0).getString("id");
                if (id.equals(idc))
                    return 2;
            }catch(NullPointerException ex){
            }
        }
        return null;
    }
    @EventHandler
    public static void speedup(PlayerMoveEvent e){
        float speed = 0.2f;
        e.getPlayer().setWalkSpeed(speed);
        if(e.getPlayer().getInventory().getItemInHand()!=null) {
            try {
                if (getId(e.getPlayer().getInventory().getItemInHand()).equals("GUARD_SWORD")) {
                    e.getPlayer().setWalkSpeed(speed + (speed * 0.5f));
                }else{
                    e.getPlayer().setWalkSpeed(speed);
                }
        }catch(NullPointerException ignored){}
    }
}}
