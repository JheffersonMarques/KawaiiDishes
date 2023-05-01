package com.hakimen.kawaiidishes.utils;

import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.time.Instant;
import java.time.Month;
import java.util.Date;
import java.util.Random;

public class MaidMobEventHandler {

    public static String[] colors = new String[]{
            "black",
            "blue",
            "brown",
            "cyan",
            "gray",
            "green",
            "light_blue",
            "light_gray",
            "lime",
            "magenta",
            "orange",
            "pink",
            "purple",
            "red",
            "white",
            "yellow"
    };

    public static String[] types = new String[]{
            "none",
            "fox",
            "cat",
            "bunny",
            "devil"
    };

    public static String[] catColors = new String[]{
            "black",
            "white",
            "caramel",
    };

    public static String[] foxColors = new String[]{
            "black",
            "white",
            "brown",
            "red",
    };

    public static String[] devilColors = new String[]{
            "red",
            "black",
            "purple",
    };



    public static ItemStack[] armorBuild(Random r) {

        String color, type, typeColor = "";

        color = colors[r.nextInt(colors.length)];

        type = types[r.nextInt(types.length)];

        switch (type) {
            case "fox" -> {
                typeColor = foxColors[r.nextInt(foxColors.length)];
            }
            case "cat","bunny" -> {
                typeColor = catColors[r.nextInt(catColors.length)];
            }
            case "devil" -> {
                typeColor = devilColors[r.nextInt(devilColors.length)];
            }
        }

        ItemStack[] stacks = new ItemStack[]{
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                ItemStack.EMPTY
        };
        if(r.nextFloat(0,1) < (Date.from(Instant.now()).getMonth()+1 == Month.APRIL.getValue() ? 0.25f : 0.05f)){
            typeColor = catColors[r.nextInt(catColors.length)];
            if(typeColor.equals("caramel")){
                stacks[0] = ItemRegister.caramelBunnyEars.get().getDefaultInstance();
                stacks[1] = ItemRegister.bunnySuitCaramelTail.get().getDefaultInstance();
            }else if(typeColor.equals("white")){
                stacks[0] = ItemRegister.whiteBunnyEars.get().getDefaultInstance();
                stacks[1] = ItemRegister.bunnySuitWhiteTail.get().getDefaultInstance();
            }else if(typeColor.equals("black")){
                stacks[0] = ItemRegister.blackBunnyEars.get().getDefaultInstance();
                stacks[1] = ItemRegister.bunnySuitBlackTail.get().getDefaultInstance();
            }
            stacks[2] = ItemRegister.bunnySuitSocks.get().getDefaultInstance();
            stacks[3] = r.nextInt(2) == 1 ? ItemRegister.whiteThighHighsShoes.get().getDefaultInstance() : ItemRegister.blackThighHighsShoes.get().getDefaultInstance();
            return stacks;
        }

        for (var item : ItemRegister.ITEMS.getEntries().stream().toList()) {
            var stack = item.get().getDescriptionId();
            if(!stacks[0].equals(ItemStack.EMPTY) && !stacks[1].equals(ItemStack.EMPTY)){
                break;
            }
            if (stack.contains(color + "_headband")) {
                if (!type.equals("none")) {
                    if (stack.endsWith("_" + type + "_ears_" + typeColor)) {
                        stacks[0] = item.get().getDefaultInstance();
                    }
                    if (stack.endsWith("_horns_" + typeColor)) {
                        stacks[0] = item.get().getDefaultInstance();
                    }
                } else if (!stack.contains("ears")) {
                    stacks[0] = item.get().getDefaultInstance();
                }
            }else if (stack.contains(color + "_maid_dress")) {
                if (!type.equals("none")) {
                    if (stack.endsWith("_" + type + "_tail_" + typeColor)) {
                        stacks[1] = item.get().getDefaultInstance();
                    }
                }else if(!stack.contains("tail")){
                    stacks[1] = item.get().getDefaultInstance();
                }
            }else if (stack.contains(color + "_thigh_highs")) {
                stacks[2] = item.get().getDefaultInstance();
            }
        }
        stacks[3] = r.nextInt(2) == 1 ? ItemRegister.whiteThighHighsShoes.get().getDefaultInstance() : ItemRegister.blackThighHighsShoes.get().getDefaultInstance();
        if(r.nextFloat(0,1) < (Date.from(Instant.now()).getMonth()+1 == Month.OCTOBER.getValue() ? 0.75f :  0.05f)){
            stacks[0] = Items.JACK_O_LANTERN.getDefaultInstance();
        }
        return stacks;
    }
}
