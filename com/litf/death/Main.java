package com.litf.death;

import com.litf.death.Commands.*;
import com.litf.death.Commands.Item;
import com.litf.death.Entits.ForestGuard;
import com.litf.death.Entits.ForestSkiter;
import com.litf.death.Entits.NPCS.NPC;
import com.litf.death.Entits.NPCS.NPCEvents;
import com.litf.death.Packeets.PacketReader;
import com.litf.death.Events.*;
import com.litf.death.Files.DataManager;
import com.litf.death.Files.LocationManager;
import com.litf.death.Invens.AnvilMenu.AnvilMenuEvents;
import com.litf.death.Invens.AuctionHouse.AuctionHouseBrowse;
import com.litf.death.Invens.AuctionHouse.AuctionHouseEvents;
import com.litf.death.Invens.CraftingMenu.CraftingMenuEvents;
import com.litf.death.Invens.CraftingMenu.RecipeBook.RecipeBookEvents;
import com.litf.death.Invens.Deliveries.DeliveryMenuEvents;
import com.litf.death.Invens.ItemMenu.ItemMenuEvents;
import com.litf.death.Invens.PlayerStats.PlayerStatsEvents;
import com.litf.death.Invens.TabletMenu.TablemMenuEvents;
import com.litf.death.Invens.TyrantBossLoot.TyrantLootEvents;
import com.litf.death.Items.*;
import com.litf.death.Items.Items;
import com.litf.death.Ranks.Rankuuids;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.List;

public class Main extends JavaPlugin {
    public static Plugin plg;
    public static DataManager data;
    public static LocationManager serverdata;
    public void onEnable(){
        if(!Bukkit.getOnlinePlayers().isEmpty())
            for(Player p:Bukkit.getOnlinePlayers()){
                PacketReader reader = new PacketReader();
                reader.inject(p);
            }
        data=new DataManager(this);
        serverdata=new LocationManager(this);
        Rankuuids.load();
        getCommand("item").setExecutor(new Item());
        getCommand("startspawn").setExecutor(new StartSpawn());
        getCommand("cfgreload").setExecutor(new Reload());
        getCommand("ah").setExecutor(new AuctionHouse());
        getCommand("setdestinycount").setExecutor(new setdestinycount());
        getCommand("deliver").setExecutor(new deliver());
        getCommand("deliveries").setExecutor(new deliveries());
        getCommand("setrank").setExecutor(new setrank());
        getCommand("setid").setExecutor(new setworlduuid());
        getCommand("refreshspawns").setExecutor(new refreshspawn());
        getCommand("disablelitf").setExecutor(new Disable());
        getCommand("debug").setExecutor(new debug());
        getCommand("debug").setTabCompleter(new debug_TC());
        getCommand("stats").setExecutor(new stats());
        Misc.init();
        Weapons.init();
        Armor.init();
        Items.init();
        Deliveries.init();
        getServer().getPluginManager().registerEvents(new TablemMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new FTyrantEvents(this), this);
        getServer().getPluginManager().registerEvents(new TyrantLootEvents(), this);
        getServer().getPluginManager().registerEvents(new AbilityEvents(this), this);
        getServer().getPluginManager().registerEvents(new Scoreboardinit(), this);
        getServer().getPluginManager().registerEvents(new DeliveryMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new com.litf.death.Ranks.Events(this), this);
        getServer().getPluginManager().registerEvents(new AuctionHouseEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerStatsEvents(), this);
        getServer().getPluginManager().registerEvents(new DamageEvents(), this);
        getServer().getPluginManager().registerEvents(new DestinyEvents(), this);
        getServer().getPluginManager().registerEvents(new AnvilMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new UtilEvents(), this);
        getServer().getPluginManager().registerEvents(new SpawnEvents(), this);
        getServer().getPluginManager().registerEvents(new UndegroundMineEvents(this), this);
        getServer().getPluginManager().registerEvents(new ItemMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new CraftingMenuEvents(), this);
        getServer().getPluginManager().registerEvents(new RecipeTierEvents(), this);
        getServer().getPluginManager().registerEvents(new RecipeBookEvents(), this);
        getServer().getPluginManager().registerEvents(new InventoryStoringEvents(), this);
        getServer().getPluginManager().registerEvents(new NPCEvents(), this);
        plg = (Plugin) this;
        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for(Player online:Bukkit.getOnlinePlayers()) {
                Scoreboardinit.insureCoins(online);
                Scoreboardinit.createBoard(online);
            }
            if(Main.serverdata.getConfig().getInt("Auctions.Count")==0){
                Main.serverdata.getConfig().set("Auctions.Count", 0);
                Main.serverdata.saveConfig();
            }
        }
        if(serverdata.getConfig().getString("ServerInfo.MainWorld")!=null) {
            FTyrantEvents.enableFTyrant();
            UndegroundMineEvents.initores(Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))));
            if (Main.serverdata.getConfig().get("NPCS.Miner.Location") == null) {
                Main.serverdata.getConfig().set("NPCS.Miner.Location", Arrays.asList(1, 1, 1));
                Main.serverdata.saveConfig();
            }
            if (Main.serverdata.getConfig().get("NPCS.Miner2.Location") == null) {
                Main.serverdata.getConfig().set("NPCS.Miner2.Location", Arrays.asList(1, 1, 1));
                Main.serverdata.saveConfig();
            }
            List<Integer> locs = Main.serverdata.getConfig().getIntegerList("NPCS.Miner.Location");
            Location loc = Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(locs.get(0), locs.get(1), locs.get(2)).getLocation();
            loc.setX(loc.getX()+0.5);
            loc.setZ(loc.getZ()+0.5);
            EntityPlayer npc = NPC.createNPC(loc, ChatColor.AQUA+""+ChatColor.BOLD+"Miner Jeff", Arrays.asList("ewogICJ0aW1lc3RhbXAiIDogMTYyNjM2OTk3OTgzMSwKICAicHJvZmlsZUlkIiA6ICI2MWUwNmYxMjFjNTk0ZDdiYjY5MmQ0ZDg1MDljNDdmOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJJdHpfU2NhcmxldHQ3MDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk0ZDIwZjRkNzcwYTkyNzUyMzU3MDBkYjJkNWU3NGIyZWFjN2M5MTliMzhkYzgzYmYzZTViZDYxYzk4NDQ5ZCIKICAgIH0KICB9Cn0=", "W7KdlAOqvCCuHjt4YmowfDYxOIpcHFFXYjP5jrTBTgKG5xM4ITHq0tVQ6HhoLDggsUBweV57F2tmVOCEZ4wNeV9q9a+rGKhYXARHigNVNbi2IEoc7emqh7458ngY8VSylExhl8ksP7kRea2iRg02Gd7sUNV7udEnmf2O7yNAmJydlgt1vSahjFlFmqWHvhgbenttQsFYQkI9ohYED+2BJ/z6d8O+gXsU8EKfHE00cSmbQN199gcRw8HP8v9Z6i/dA9Y/h6fvRVvdTUUpI2GILf4b9GvMJs2QAMG1ZudohH2zPydUzmr2UG+pB8naIs0YcIQWaJcDjbph26ZrgUPdWaZRE4L10Byj1M2LEHgq0422MLDqQ5rrNYRmMEFhGmXm8H+N5dZ5p8QHJ1P/xsSy6C0tBge5qGK1OQhV47VsSPBFcZjAlsOQve12s7/N8mvf5pXJtchyuqhm0Zgnszvu7m8F09s3Rh3/vSZeNQ1LSvcvrhGz1h5YTpHKsHUXaI2YfZfGotZbBjoaYCeQ/ogNezFs0FMtfKYSjqkNH/rk1cGs4mKAE7dksc7BXm6KjDabTXjNw39nCyoxUbRuWBdLbJfpOePXy+ZNIqCq5ZsVWlTBUbhlxtKxlYswJreaUmY78+zr0ryxtfMNDvwTp/avfgieHWfuarMJ7IUtglLLt6k="));
            NPCEvents.constalooks.add(npc);
            for(Player p:Bukkit.getOnlinePlayers()) {
                if (Main.data.getConfig().get(p.getUniqueId().toString() + ".Undergrounddata.quests.1") == null) {
                    Main.data.getConfig().set(p.getUniqueId().toString() + ".Undergrounddata.quests.1", 0);
                    Main.data.saveConfig();
                }
                int i = Main.data.getConfig().getInt(p.getUniqueId().toString() + ".Undergrounddata.quests.1");
                if(i<7) {
                    PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                    connection.sendPacket(new PacketPlayOutEntityEquipment(npc.getId(), 0, CraftItemStack.asNMSCopy(new ItemStack(Material.WOOD_PICKAXE))));
                }
                NPC.makeNPClookAtPlayer(npc, p);
                locs = Main.serverdata.getConfig().getIntegerList("NPCS.Miner2.Location");
                loc = Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(locs.get(0), locs.get(1), locs.get(2)).getLocation();
                loc.setX(loc.getX()+0.5);
                loc.setZ(loc.getZ()+0.5);
                EntityPlayer npc2 = NPC.createNPC(loc, ChatColor.AQUA+""+ChatColor.BOLD+"Miner Sam", Arrays.asList("ewogICJ0aW1lc3RhbXAiIDogMTYxNzg3MDg2NjQyOCwKICAicHJvZmlsZUlkIiA6ICIzM2ViZDMyYmIzMzk0YWQ5YWM2NzBjOTZjNTQ5YmE3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEYW5ub0JhbmFubm9YRCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS83NzYxMzk1NzFmZGU0OGI5NWQyMTVkMWE0Nzc0Njk0NWEzZWUxOTBlMzNlMDhlOWQ2NWU1MWRlZjA4MGExZWMwIgogICAgfQogIH0KfQ==","adyK6mwKi+xiEYMKDdfMXljqys5dAGmpVrZgsPxpPTjlSTB+NtmGoeYO42YeUcBWtqweBKQYeRO0hwlk5hX+4umB7/H1oe176q/Gtq5jJl0+1MbgeDtI686rCkXDiuEvgymPsvGFiT17JsPn52p73xsPDqPjWzB8zhWeD+OQVX+UZvV6hhrVM8ELK9/D5XDyR/4GwL8H6KddGwscfUgd2RfSYvatmd4p12yMJ5NoA5invk0TaKOuB3hCNeMbRAVZJxiM9gfL4JpKurQ9EGbu4NXUoAk/tMce+gr6DZx/2OUFrGNFnite1QStzYzp1gScd9NtLRxM2cvoYt38GHkJ63+89ivvAtFkSRwypBbgEghTBvf1+n0Qj3adEt5rUv3qWkQE3mWHcTMmb3jD1YMLRiSgV5si+Z8YzGBOTxiXUPnRuzmW4ypnNF3oC948V2O1tempYC6MWMLhuYa8Sr10nEezybZPh+oSCmRehQ8QaArsoudLQvrvscs9uGqltjzp0yJeypbzABuW2rOi5qxC3o2+e1Hg2Ju2dJy7N5WxhHjEiN0ZetiVQ2mqi7DzVke8He96SMWOTV0tZLvSDrtOnLU/UCQWcII2CNzV8AJO9G18DqLjfCMwCnshxovceAoZCxD4DOUroW8wJkH6s9mgZTrFyUxMdOK6O0ELoD/nU88="));
                NPCEvents.constalooks.add(npc2);
                locs = Main.serverdata.getConfig().getIntegerList("NPCS.Miner3.Location");
                loc = Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(locs.get(0), locs.get(1), locs.get(2)).getLocation();
                loc.setX(loc.getX()+0.5);
                loc.setZ(loc.getZ()+0.5);
                EntityPlayer OreGolem = NPC.createNPC(loc, ChatColor.DARK_RED+""+ChatColor.BOLD+"Ore Golem", Arrays.asList("ewogICJ0aW1lc3RhbXAiIDogMTYyODAxNTcxODIyNCwKICAicHJvZmlsZUlkIiA6ICI0ODBkMmFiMmY1ZTk0OWQ0YWM2Mzk0NDdmNjIzYTYwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJIb3RhbXBhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQyMWM0Y2NmMGQzNWI0YTg3NjRmNDgxZTk2OWY0MThlMzA3ZjQ4MTNmODhmYWQwNzQxOTdlYzAyMWM1OGYzNDIiCiAgICB9CiAgfQp9", "SiTykn9AqMMQG846Br6BJByUF2fhRpaqR5xQcg0N9snrXzfu2h45wHijQ+75qwMnfgyLdrFXP3vAb3p/I96SISu3Ic4kZMZ53X4n+JF1uXRsUctHhfh8tpzp4gsW9u1W84CLQcnxYfL3W9H6tV5wj/TXj4zWi96s7WAJ3YwS4Wxv/7gnZzD0Zvo168S0zMos+w4LKXPrSpFlHL6dntur3ZOykM6dCTnib7IUvyFhlzQyl81KdvZ+yZlgxW0L4re7aWXKg9BPI2o2zWV/XVR09gW2Ma7gpzanIx51EMdCXJsJ3aXSp1OQH0ejBwpzW8v1pdnKa5zxwmHYXT/eck8zKrhNxzdy4w33KO5FFMnr5cJ5AxqoUNPPk0QZ9GVBeIZtfc2od8A14ctOLXEZvkejHYWr+yw2HpjYG2Z3dC/kZ4NzpJd5Shp2x8tbmRVb/AjOwSPwfAZAfPLAIO2XEGMtQJl9R020Vvav3tVUS5HmVeqSEAl6UKOxox3mckei/Z4PVFpQ0C5fkTfpeQBJnFNW/xeESXn1zQSSF9PCNgTIqyQ2da7a7On5d6StUHddggOxMsta4+ewfJQ72CLYbwNUO/rJ8nrtaGHCv7TZO6m1o5kHTJW/Egf61ZrBGFnl6Sc3txQK6we9Rr9zetOZyJ2NFjuZMfJCyjhuvnynCrvMuoc="));
                NPCEvents.constalooks.add(OreGolem);
                getServer().getPluginManager().registerEvents(new GolemEvents(this,OreGolem),this);
            }
        }
    }
    public void onDisable(){
        for(int i=0; i<Main.serverdata.getConfig().getInt("Underground.oredata.num");i++){
            List<Integer> ints = Main.serverdata.getConfig().getIntegerList("Underground.oredata."+i);
            Block b =Bukkit.getWorld(UUID.fromString(serverdata.getConfig().getString("ServerInfo.MainWorld"))).getBlockAt(ints.get(0), ints.get(1), ints.get(2));
            b.setType(Material.SPONGE);
        }
        if(!Bukkit.getOnlinePlayers().isEmpty())
            for(Player p:Bukkit.getOnlinePlayers()){
                PacketReader reader = new PacketReader();
                reader.uninject(p);
            }
        for(EntityPlayer npc: NPC.getNPCs()){
            NPC.addShutdownPacket(npc);
            npc.getBukkitEntity().remove();
        }
        FTyrantEvents.disableFTryant();
        if(!Bukkit.getOnlinePlayers().isEmpty()) {
            for (Player online : Bukkit.getOnlinePlayers())
                Main.data.getConfig().set(online.getUniqueId().toString() + ".Coins", Scoreboardinit.getcoins(online));
            Main.data.saveConfig();
        }
        for (ForestSkiter spid : SpawnEvents.spids) {
            Entity ent = spid.getBukkitEntity();
            ent.remove();
        }
        for (ForestGuard spid : SpawnEvents.grds) {
            Entity ent = spid.getBukkitEntity();
            ent.remove();
        }
        SpawnEvents.spids.clear();
        SpawnEvents.grds.clear();
    }
    public static void HandleIndicator(ArmorStand e){
        Main.plg.getServer().getScheduler().scheduleSyncDelayedTask(Main.plg, new Runnable() {
            @Override
            public void run() {
                e.remove();
            }
        }, 10);
    }

    public static void UpdateAuctionHouse(Player p){
        Main.plg.getServer().getScheduler().scheduleSyncDelayedTask(Main.plg, new Runnable() {
            @Override
            public void run() {
                if(p.getOpenInventory().getTopInventory()!=null){
                    if(p.getOpenInventory().getTopInventory().getHolder() instanceof AuctionHouseBrowse){
                        int cp = 0;
                        if(AuctionHouseEvents.cpage.containsKey(p))
                            cp = AuctionHouseEvents.cpage.get(p);
                        AuctionHouseBrowse gui = new AuctionHouseBrowse(cp);
                        p.openInventory(gui.getInventory());
                        UpdateAuctionHouse(p);
                    }
                }
            }
        }, 20);
    }
}
