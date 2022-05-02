package com.litf.death.Commands;

import com.litf.death.Ranks.Calls;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class debug_TC implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
            if(Calls.getRank((Player) commandSender)>=3){
                return debug.argss;
            }
        return null;
    }
}
