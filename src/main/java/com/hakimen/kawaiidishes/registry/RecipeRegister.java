package com.hakimen.kawaiidishes.registry;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegister {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, KawaiiDishes.modId);

    public static final RegistryObject<RecipeSerializer<CoffeePressRecipe>> CoffeePressRecipeSerializer =
            SERIALIZERS.register("coffee_pressing", () -> CoffeePressRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<CoffeeMachineRecipe>> CoffeeMachineRecipeSerializer =
            SERIALIZERS.register("coffee_machining", () -> CoffeeMachineRecipe.Serializer.INSTANCE);


    public static final RegistryObject<RecipeSerializer<IceCreamMachineRecipe>> IceCreamMakingRecipe =
            SERIALIZERS.register("ice_cream_making", () -> IceCreamMachineRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<BlenderRecipe>> BlendingRecipe =
            SERIALIZERS.register("blending", () -> BlenderRecipe.Serializer.INSTANCE);


    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
