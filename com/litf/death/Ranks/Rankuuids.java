package com.litf.death.Ranks;

import com.litf.death.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rankuuids {
    public static List<String> owner = Arrays.asList(
        "e34e9ea9-78de-32ce-b157-3c2351f0eb1a"
);
    public static List<String> admin = Arrays.asList(
            "e34e9ea9-78de-32ce-b157-3c2351f0eb1a"
    );
    public static List<String> builder = Arrays.asList(
            "e34e9ea9-78de-32ce-b157-3c2351f0eb1a"
    );
    public static List<String> mod = Arrays.asList(
            "e34e9ea9-78de-32ce-b157-3c2351f0eb1a"
    );
    public static void load(){
        if (Main.serverdata.getConfig().get("Ranks.owner") == null) {
            Main.serverdata.getConfig().set("Ranks.owner", owner);
            Main.serverdata.getConfig().set("Ranks.admin", admin);
            Main.serverdata.getConfig().set("Ranks.builder", builder);
            Main.serverdata.getConfig().set("Ranks.mod", mod);
            Main.serverdata.saveConfig();
        }
    }

}
