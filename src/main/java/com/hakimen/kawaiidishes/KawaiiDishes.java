package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.screens.BlenderScreen;
import com.hakimen.kawaiidishes.client.screens.CoffeeMachineScreen;
import com.hakimen.kawaiidishes.client.screens.IceCreamScreen;
import com.hakimen.kawaiidishes.config.KawaiiDishesClientConfig;
import com.hakimen.kawaiidishes.config.KawaiiDishesCommonConfig;
import com.hakimen.kawaiidishes.registry.ContainerRegister;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.Registration;
import com.hakimen.kawaiidishes.utils.MaidMobEventHandler;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Random;



@Mod("kawaiidishes")
public class KawaiiDishes {

    // Directly reference a slf4j logger
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String modId = "kawaiidishes";
    public KawaiiDishes() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, KawaiiDishesClientConfig.clientSpec, "kawaii-dishes-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, KawaiiDishesCommonConfig.commonSpec, "kawaii-dishes-common.toml");

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registration.init();

        bus.addListener(this::enqueueIMC);
        bus.addListener(this::clientStartup);
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.addListener(this::onLivingSpecialSpawn);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onLivingSpecialSpawn(MobSpawnEvent event) {
        Entity entity = event.getEntity();
        if (!entity.isAddedToWorld() && entity instanceof Monster monster && !entity.serializeNBT().getBoolean("isBaby") && RANDOM.nextFloat(0,1) < KawaiiDishesCommonConfig.chanceToSpawnWithDress.get()) {
            if((monster instanceof Skeleton
                    || monster instanceof WitherSkeleton
                    || monster instanceof Stray
                    || monster instanceof Zombie
                    || monster instanceof Piglin
                    || monster instanceof PiglinBrute) && KawaiiDishesCommonConfig.shouldMobSpawnWithDress.get()){
                ItemStack[] stacks = MaidMobEventHandler.armorBuild(RANDOM);

                monster.setItemSlot(EquipmentSlot.HEAD, stacks[0]);
                monster.setItemSlot(EquipmentSlot.CHEST, stacks[1]);
                monster.setItemSlot(EquipmentSlot.LEGS, stacks[2]);
                monster.setItemSlot(EquipmentSlot.FEET, stacks[3]);

                monster.setDropChance(EquipmentSlot.HEAD,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.CHEST,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.LEGS,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
                monster.setDropChance(EquipmentSlot.FEET,KawaiiDishesCommonConfig.chanceToDropArmorSet.get().floatValue());
            }
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(ItemRegister.coffeeFruit.get(),0.25f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCoffeeBeans.get(),0.50f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCoffeeBeans.get(),0.75f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.driedCocoaBeans.get(),0.50f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.roastedCocoaBeans.get(),0.75f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.cakePiece.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.honeyCheeseCakePiece.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.chocolateCheeseCakePiece.get(),0.65f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.cheeseCakePiece.get(),0.65f);

        ComposterBlock.COMPOSTABLES.put(ItemRegister.honeyCheeseCake.get(),1f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.chocolateCheeseCake.get(),1f);
        ComposterBlock.COMPOSTABLES.put(ItemRegister.cheeseCake.get(),1f);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    private void clientStartup(final FMLClientSetupEvent event){
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerRegister.coffeeMachine.get(), CoffeeMachineScreen::new);
            MenuScreens.register(ContainerRegister.iceCreamMachine.get(), IceCreamScreen::new);
            MenuScreens.register(ContainerRegister.blenderContainer.get(), BlenderScreen::new);
        });
    }
}
