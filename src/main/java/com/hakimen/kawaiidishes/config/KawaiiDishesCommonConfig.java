package com.hakimen.kawaiidishes.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class KawaiiDishesCommonConfig {
    public static final ForgeConfigSpec.Builder commonConfigBuilder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec commonSpec;

    public static final ForgeConfigSpec.BooleanValue shouldMobSpawnWithDress;
    public static final ForgeConfigSpec.DoubleValue chanceToSpawnWithDress;

    public static final ForgeConfigSpec.DoubleValue changeToDropArmorSet;

    static {
        commonConfigBuilder.push("Common Configs for Kawaii Dishes");

        shouldMobSpawnWithDress = commonConfigBuilder.comment("Should the mobs spawn with the maid dresses")
                        .define("shouldSpawn", true);

        chanceToSpawnWithDress = commonConfigBuilder.comment("Sets the chance for mobs to spawn with maid dresses")
                .defineInRange("chanceToSpawn", 0.075,0,1);
        changeToDropArmorSet = commonConfigBuilder.comment("Sets the chance for mobs that spawn with maid dresses to drop them")
                .defineInRange("chanceToDrop", 0.25,0,1);



        commonConfigBuilder.pop();
        commonSpec = commonConfigBuilder.build();
    }


}
