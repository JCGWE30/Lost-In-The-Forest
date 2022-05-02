package com.litf.death.Invens.CraftingMenu.RecipeBook;

import com.litf.death.Events.RecipeTierEvents;
import com.litf.death.Invens.CraftingMenu.CraftingMenuEvents;
import com.litf.death.Invens.CraftingMenu.CraftingMenuMain;
import net.minecraft.server.v1_8_R3.ItemSaddle;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class RecipeBookEvents implements Listener {

    @EventHandler
    public static void openbook(InventoryClickEvent e){
        if(e.getClickedInventory()!=null)
        if(e.getClickedInventory().getHolder() instanceof CraftingMenuMain){
            if(e.getSlot()==42){
                RecipeTierEvents.confirmRecipes((Player)e.getWhoClicked());
                RecipeBookMain gui = new RecipeBookMain((Player)e.getWhoClicked(), 1);
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
        if(e.getClickedInventory().getHolder() instanceof RecipeBookPageHidden){
            e.setCancelled(true);
        }
        if(e.getClickedInventory().getHolder() instanceof RecipeBookMain){
            if(e.getSlot()==e.getClickedInventory().getSize()-5){
                CraftingMenuMain gui = new CraftingMenuMain();
                e.getWhoClicked().openInventory(gui.getInventory());
            }else if(e.getSlot()==e.getClickedInventory().getSize()-1){
                RecipeBookMain gui = new RecipeBookMain((Player) e.getWhoClicked(), 2);
                e.getWhoClicked().openInventory(gui.getInventory());
            }
            e.setCancelled(true);
            String id = null;
            try {
                net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(e.getCurrentItem());
                NBTTagCompound comp = iten.getTag();
                NBTTagList lis = (NBTTagList) comp.get("comp");
                id = lis.get(0).getString("id");
            } catch (NullPointerException ex) {

            }
            if(id!=null){
                for(List<org.bukkit.inventory.ItemStack> stacks:CraftingMenuEvents.recs){
                    String ida = null;
                    try {
                        net.minecraft.server.v1_8_R3.ItemStack iten = CraftItemStack.asNMSCopy(stacks.get(9));
                        NBTTagCompound comp = iten.getTag();
                        NBTTagList lis = (NBTTagList) comp.get("comp");
                        ida = lis.get(0).getString("id");
                    } catch (NullPointerException ex) {

                    }
                    if(id.equals(ida)){
                        RecipeBookPage gui = new RecipeBookPage(stacks);
                        e.getWhoClicked().openInventory(gui.getInventory());
                    }
                }
            }
        }
        if(e.getInventory().getHolder() instanceof RecipeBookPage){
            e.setCancelled(true);
            if(e.getClickedInventory().getHolder() instanceof RecipeBookPage)
            if(e.getSlot()==40){
                RecipeBookMain gui = new RecipeBookMain((Player)e.getWhoClicked(), 1);
                e.getWhoClicked().openInventory(gui.getInventory());
            }
        }
    }
}
