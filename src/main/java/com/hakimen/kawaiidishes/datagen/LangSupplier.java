package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.EffectRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.HashMap;

public class LangSupplier extends LanguageProvider {
    public LangSupplier(DataGenerator gen, String locale) {
        super(gen, KawaiiDishes.modId, locale);
    }

    @Override
    protected void addTranslations() {
        HashMap<String,String> dressNames = new HashMap<String,String>();
                dressNames.put("blue_maid_dress","Blue Maid Dress");
                dressNames.put("brown_maid_dress","Brown Maid Dress");
                dressNames.put("cyan_maid_dress","Cyan Maid Dress");
                dressNames.put("gray_maid_dress","Gray Maid Dress");
                dressNames.put("green_maid_dress","Green Maid Dress");
                dressNames.put("light_blue_maid_dress","Light Blue Maid Dress");
                dressNames.put("light_gray_maid_dress","Light Gray Maid Dress");
                dressNames.put("lime_maid_dress","Lime Maid Dress");
                dressNames.put("magenta_maid_dress","Magenta Maid Dress");
                dressNames.put("orange_maid_dress","Orange Maid Dress");
                dressNames.put("pink_maid_dress","Pink Maid Dress");
                dressNames.put("red_maid_dress","Red Maid Dress");
                dressNames.put("white_maid_dress","White Maid Dress");
                dressNames.put("black_maid_dress","Black Maid Dress");
                dressNames.put("yellow_maid_dress","Yellow Maid Dress");
                dressNames.put("purple_maid_dress","Purple Maid Dress");
                
        for (var items:ItemRegister.ITEMS.getEntries().stream().toList()) {
            String itemPath = items.get().getRegistryName().getPath();
            for (int i = 0; i < dressNames.keySet().size(); i++) {
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString()+"_cat_tail_black")){
                    add(items.get(),dressNames.get(itemPath.replace("_cat_tail_black",""))+" Black Cat Tail");
                }
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString()+"_cat_tail_caramel")){
                    add(items.get(),dressNames.get(itemPath.replace("_cat_tail_caramel",""))+" Caramel Cat Tail");
                }
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString()+"_cat_tail_white")){
                    add(items.get(),dressNames.get(itemPath.replace("_cat_tail_white",""))+" White Cat Tail");
                }
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString()+"_fox_tail_black")){
                    add(items.get(),dressNames.get(itemPath.replace("_fox_tail_black",""))+" Black Fox Tailed");
                }
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString()+"_fox_tail_red")){
                    add(items.get(),dressNames.get(itemPath.replace("_fox_tail_red",""))+" Red Fox Tailed");
                }
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString()+"_fox_tail_white")){
                    add(items.get(),dressNames.get(itemPath.replace("_fox_tail_white",""))+" White Fox Tailed");
                }
                if(itemPath.equals(dressNames.keySet().stream().toArray()[i].toString())){
                    add(items.get(),dressNames.get(itemPath));
                }
            }
            if(itemPath.endsWith("_coffee")){
                add(items.get(),setFirstLetterUpperCase(itemPath.replaceAll("_"," ")));
            }
            if(itemPath.endsWith("_stool")){
                add(items.get(),setFirstLetterUpperCase(itemPath.replaceAll("_"," ")));
            }
        }

        add(ItemRegister.coffeeFruit.get(),"Coffee Fruit");
        add(ItemRegister.roastedCoffeeBeans.get(),"Roasted Coffee Beans");
        add(ItemRegister.driedCoffeeBeans.get(),"Dried Coffee Beans");
        add(ItemRegister.coffeePowder.get(),"Coffee Powder");

        add(ItemRegister.roastedCocoaBeans.get(),"Roasted Cocoa Beans");
        add(ItemRegister.driedCocoaBeans.get(),"Dried Cocoa Beans");
        add(ItemRegister.cocoaPowder.get(),"Cocoa Powder");

        add(ItemRegister.whiteChocolateBar.get(),"White Chocolate Bar");
        add(ItemRegister.darkChocolateBar.get(),"Dark Chocolate Bar");
        add(ItemRegister.milkChocolateBar.get(),"Milk Chocolate Bar");

        add(ItemRegister.mortarAndPestle.get(),"Mortar And Pestle");

        add(ItemRegister.mug.get(),"Mug");

        add(ItemRegister.caramelCatTail.get(),"Caramel Cat Tail");
        add(ItemRegister.whiteCatTail.get(),"White Cat Tail");
        add(ItemRegister.blackCatTail.get(),"Black Cat Tail");

        add(ItemRegister.redFoxTail.get(),"Red Fox Tail");
        add(ItemRegister.whiteFoxTail.get(),"White Fox Tail");
        add(ItemRegister.blackFoxTail.get(),"Black Fox Tail");

        add(ItemRegister.caramelCatEars.get(),"Caramel Cat Ears");
        add(ItemRegister.blackCatEars.get(),"Black Cat Ears");
        add(ItemRegister.whiteCatEars.get(),"White Cat Ears");

        add(ItemRegister.blackThighHighs.get(),"Black Thigh Highs");
        add(ItemRegister.whiteThighHighs.get(),"White Thigh Highs");

        add(ItemRegister.blackThighHighsShoes.get(),"Dark Brown Shoes");
        add(ItemRegister.whiteThighHighsShoes.get(),"Brown Shoes");

        add(BlockRegister.coffeeMachine.get(),"Coffee Machine");
        add(BlockRegister.coffeePress.get(),"Coffee Press");

        add("itemGroup.kawaiidishes.foods", "Kawaii's Foods");
        add("itemGroup.kawaiidishes.blocks", "Kawaii's Blocks");
        add("itemGroup.kawaiidishes.cosmetics", "Kawaii's Cosmetics");
        add("itemGroup.kawaiidishes.decoration", "Kawaii's Decorations");

        add("gui.kawaiidishes.coffee_machine", "Coffee Machine");

        add(EffectRegister.kawaiiEffect.get(),"Kawaii");
        add(EffectRegister.nekoEffect.get(),"Neko-chan");
    }

    public String setFirstLetterUpperCase(String string){
        var builtString = "";
        for (String word:string.split(" ")) {
            builtString += word.substring(0,1).toUpperCase() + word.substring(1)+ " ";
        }
        return builtString.substring(0,builtString.length()-1);
    }
}