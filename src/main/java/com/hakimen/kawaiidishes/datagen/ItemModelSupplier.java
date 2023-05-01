package com.hakimen.kawaiidishes.datagen;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.registry.BlockRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelSupplier extends ItemModelProvider {
    public ItemModelSupplier(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, KawaiiDishes.modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var block:BlockRegister.BLOCKS.getEntries().stream().toList()) {
            String path = Registry.BLOCK.getKey(block.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if(path.contains("_stool")){
                stool(block.get());
            }else if(path.endsWith("milkshake")){
                milkshake(block.get());
            }else if(path.endsWith("coffee")){
                coffee(block.get());
            }else if(path.endsWith("ice_cream")){
                iceCream(block.get());
            }else if(path.endsWith("cake")){
                cake(block.get());
            }
            else if(path.contains("coffee_bush") || path.contains("mortar_and_pestle")){
                continue;
            }else
                block(block.get());
        }
        for(var item : ItemRegister.ITEMS.getEntries().stream().toList()){
            String path = Registry.ITEM.getKey(item.get()).toString().replaceAll(KawaiiDishes.modId+":","");
            if(path.contains("_headband")){
                if(path.endsWith("_cat_ears_black")){
                    catBandItem(item.get(),"cat_ears_black");
                }
                else if(path.endsWith("_cat_ears_white")){
                    catBandItem(item.get(),"cat_ears_white");
                }
                else if(path.endsWith("_cat_ears_caramel")) {
                    catBandItem(item.get(),"cat_ears_caramel");
                }
                else if(path.endsWith("_fox_ears_black")){
                    foxBandItem(item.get(),"cat_ears_black","fox_ears_black");
                }
                else if(path.endsWith("_fox_ears_white")){
                    foxBandItem(item.get(),"cat_ears_white","fox_ears_white");
                }
                else if(path.endsWith("_fox_ears_red")) {
                    foxBandItem(item.get(),"fox_ears_red","fox_ears_red");
                }
                else if(path.endsWith("_fox_ears_brown")) {
                    foxBandItem(item.get(),"fox_ears_brown","fox_ears_brown");
                }
                else if(path.endsWith("_bunny_ears_black")){
                    bunnyBandItem(item.get(),"bunny_ears_black","bunny_ears_black");
                }
                else if(path.endsWith("_bunny_ears_white")){
                    bunnyBandItem(item.get(),"bunny_ears_white","bunny_ears_white");
                }
                else if(path.endsWith("_bunny_ears_caramel")) {
                    bunnyBandItem(item.get(),"bunny_ears_caramel","bunny_ears_caramel");
                }
                else if(path.endsWith("_horns_white")){
                    bigHornBandItem(item.get(),"white_horn","horns_white");
                }
                else if(path.endsWith("_horns_gray")){
                    bigHornBandItem(item.get(),"gray_horn","horns_gray");
                }
                else if(path.endsWith("_horns_light_gray")){
                    bigHornBandItem(item.get(),"light_gray_horn","horns_light_gray");
                }
                else if(path.endsWith("_horns_red")){
                    hornBandItem(item.get(),"red_horns","horns_red");
                }
                else if(path.endsWith("_horns_purple")){
                    hornBandItem(item.get(),"purple_horns","horns_purple");
                }
                else if(path.endsWith("_horns_black")){
                    hornBandItem(item.get(),"black_horns","horns_black");
                }
                else{
                    headBandItem(item.get());
                }
            }else if(path.contains("_maid_dress")) {
                maidDressItem(item.get());
            }else if(path.contains("thigh_highs")){
                thighHighItem(item.get());
            }
        }


        cookieItem(ItemRegister.sweetBerryCookie.get());
        cookieItem(ItemRegister.chocolateCookie.get());
        cookieItem(ItemRegister.honeyCookie.get());
        cookieItem(ItemRegister.goldenCookie.get());
        cookieItem(ItemRegister.unbindingCookie.get());

        simpleItem(ItemRegister.cakePiece.get());
        simpleItem(ItemRegister.cheeseCakePiece.get());
        simpleItem(ItemRegister.chocolateCheeseCakePiece.get());
        simpleItem(ItemRegister.honeyCheeseCakePiece.get());

        simpleItem(ItemRegister.whiteChocolateBar.get());
        simpleItem(ItemRegister.darkChocolateBar.get());
        simpleItem(ItemRegister.milkChocolateBar.get());

        simpleItem(ItemRegister.condensedMilk.get());
        simpleItem(ItemRegister.brigadeiroMix.get());
        simpleItem(ItemRegister.creamCheese.get());

        simpleItem(ItemRegister.driedCocoaBeans.get());
        simpleItem(ItemRegister.roastedCocoaBeans.get());
        simpleItem(ItemRegister.cocoaPowder.get());

        simpleItem(ItemRegister.coffeeFruit.get());
        simpleItem(ItemRegister.driedCoffeeBeans.get());
        simpleItem(ItemRegister.roastedCoffeeBeans.get());
        simpleItem(ItemRegister.coffeePowder.get());

        simpleItem(ItemRegister.blackThighHighsShoes.get());
        simpleItem(ItemRegister.whiteThighHighsShoes.get());

        simpleItem(ItemRegister.bunnySuitBlackTail.get());
        simpleItem(ItemRegister.bunnySuitWhiteTail.get());
        simpleItem(ItemRegister.bunnySuitCaramelTail.get());
        simpleItem(ItemRegister.bunnySuitSocks.get());
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder maidDressItem(Item item) {
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/maid_dresses/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder thighHighItem(Item item) {
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/thigh_highs/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder cookieItem(Item item) {
        return withExistingParent(Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(KawaiiDishes.modId,"item/cookie/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }



    private ItemModelBuilder catBandItem(Item item,String type) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/cat_headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+type,"")));

    }
    private ItemModelBuilder foxBandItem(Item item,String type,String name) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/fox_headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+name,"")));

    }
    private ItemModelBuilder bunnyBandItem(Item item,String type,String name) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/bunny_headband"))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/" + type))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+name,"")));
    }

    private ItemModelBuilder bigHornBandItem(Item item,String type,String name) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/big_horn_headband"))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/horns/" + type))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+name,"")));
    }
    private ItemModelBuilder hornBandItem(Item item,String type,String name) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/horns_headband"))
                .texture("1", new ResourceLocation(KawaiiDishes.modId,"item/horns/" + type))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","").replaceAll("_"+name,"")));
    }

    private ItemModelBuilder headBandItem(Item item) {
        return withExistingParent(
                Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":",""),
                new ResourceLocation(KawaiiDishes.modId,"item/headband"))
                .texture("0", new ResourceLocation(KawaiiDishes.modId,"item/headbands/" + Registry.ITEM.getKey(item).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder block(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }

    private ItemModelBuilder cake(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/cake/cakes/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }



    private ItemModelBuilder milkshake(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/milk_shakes/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder coffee(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/coffees/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
    private ItemModelBuilder iceCream(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/ice_creams/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }

    private ItemModelBuilder stool(Block block){
        return withExistingParent(Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":",""),new ResourceLocation(KawaiiDishes.modId,"block/stools/"+Registry.BLOCK.getKey(block).toString().replaceAll(KawaiiDishes.modId+":","")));
    }
}
