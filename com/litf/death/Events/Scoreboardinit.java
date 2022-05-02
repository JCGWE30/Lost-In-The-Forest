package com.litf.death.Events;

import com.litf.death.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class Scoreboardinit implements Listener {
    private static HashMap<Player, Integer> coins = new HashMap<>();
    @EventHandler
    public void scoreset(PlayerJoinEvent e){
        coins.put(e.getPlayer(), (Main.data.getConfig().getInt(e.getPlayer().getUniqueId().toString()+".Coins")));
        createBoard(e.getPlayer());
    }
    @EventHandler
    public static void scoresave(PlayerQuitEvent e){
        Main.data.getConfig().set(e.getPlayer().getUniqueId().toString()+".Coins",coins.get(e.getPlayer()));
        Main.data.saveConfig();
    }
    public static void createBoard(Player p){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective(ChatColor.GOLD+""+ChatColor.BOLD+"LITF "+ChatColor.YELLOW+"ALPHA", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = obj.getScore(" ");
        score.setScore(1);
        score = obj.getScore(ChatColor.YELLOW+"Coins: "+ChatColor.GOLD+getcoins(p));
        score.setScore(0);
        p.setScoreboard(board);
    }
    public static void addcoins(Player p, Integer coin){
        if(coins.get(p)!=null){
            coins.put(p, coins.get(p)+coin);
        }
        Main.data.getConfig().set(p.getUniqueId().toString()+".Coins",coins.get(p));
        Main.data.saveConfig();
        createBoard(p);
    }
    public static void addcoinsoffline(String uid, Integer coins){
        if(!Bukkit.getPlayer(uid).isOnline()){
            if(Main.serverdata.getConfig().contains(uid)){
                Main.serverdata.getConfig().set(uid+".Coins", getcoins(Bukkit.getPlayer(uid))+coins);
                Main.serverdata.saveConfig();
            }
        }
    }
    public static void subcoins(Player p, Integer coin){
        if(coins.get(p)!=null){
            coins.put(p, coins.get(p)-coin);
        }
        Main.data.getConfig().set(p.getUniqueId().toString()+".Coins",coins.get(p));
        Main.data.saveConfig();
        createBoard(p);
    }
    public static void insureCoins(Player p){
        coins.put(p, (Main.data.getConfig().getInt(p.getUniqueId().toString()+".Coins")));
    }
    public static Integer getcoins(Player p){
        return(coins.get(p));
    }
}
