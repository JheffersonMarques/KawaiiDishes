package com.hakimen.kawaiidishes.datagen.recipebuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CoffeeMachineRecipeBuilder implements RecipeBuilder {
    private final ItemStack result;
    private final ItemStack neededOnOutput;
    private final boolean needWater,needMilk;
    private final int ticks;
    private final Ingredient ingredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public CoffeeMachineRecipeBuilder(ItemLike ingredient, ItemLike ingredient1, ItemLike ingredient2, ItemStack result,int ticks, boolean needWater, boolean needMilk, ItemStack neededOnOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredient = Ingredient.of(ingredient,ingredient1,ingredient2);
        this.ticks = ticks;
        this.neededOnOutput = neededOnOutput;
        this.needMilk = needMilk;
        this.needWater = needWater;
        var stack = result.getItem().getDefaultInstance();

        var mainEffectTag = new CompoundTag();
        var secondaryEffectTag = new CompoundTag();
        if(mainEffect != null){
            mainEffect.save(mainEffectTag);
        }
        if(secondaryEffect != null){
            secondaryEffect.save(secondaryEffectTag);
        }



        if(!mainEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("mainEffect",mainEffectTag);
        }
        if(!secondaryEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("secondaryEffect",secondaryEffectTag);
        }

        this.result = stack;
        this.count = 1;
    }
    public CoffeeMachineRecipeBuilder(ItemLike ingredient, ItemLike ingredient1, ItemStack result,int ticks,boolean needWater, boolean needMilk, ItemStack neededOnOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredient = Ingredient.of(ingredient,ingredient1);
        this.neededOnOutput = neededOnOutput;
        this.ticks = ticks;
        this.needMilk = needMilk;
        this.needWater = needWater;
        var stack = result.getItem().getDefaultInstance();

        var mainEffectTag = new CompoundTag();
        var secondaryEffectTag = new CompoundTag();
        if(mainEffect != null){
            mainEffect.save(mainEffectTag);
        }
        if(secondaryEffect != null){
            secondaryEffect.save(secondaryEffectTag);
        }



        if(!mainEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("mainEffect",mainEffectTag);
        }
        if(!secondaryEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("secondaryEffect",secondaryEffectTag);
        }

        this.result = stack;
        this.count = 1;
    }
    public CoffeeMachineRecipeBuilder(ItemLike ingredient, ItemStack result,int ticks, boolean needWater, boolean needMilk, ItemStack neededOnOutput, MobEffectInstance mainEffect, MobEffectInstance secondaryEffect) {
        this.ingredient = Ingredient.of(ingredient);
        this.ticks = ticks;
        this.neededOnOutput = neededOnOutput;
        this.needMilk = needMilk;
        this.needWater = needWater;
        var stack = result.getItem().getDefaultInstance();

        var mainEffectTag = new CompoundTag();
        var secondaryEffectTag = new CompoundTag();
        if(mainEffect != null){
            mainEffect.save(mainEffectTag);
        }
        if(secondaryEffect != null){
            secondaryEffect.save(secondaryEffectTag);
        }

        if(!mainEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("mainEffect",mainEffectTag);
        }
        if(!secondaryEffectTag.equals(new CompoundTag())){
            stack.getOrCreateTag().put("secondaryEffect",secondaryEffectTag);
        }

        this.result = stack;
        this.count = 1;
    }


    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new CoffeeMachineRecipeBuilder.Result(pRecipeId, this.result, this.count, this.ingredient,
                ticks,needWater,needMilk,neededOnOutput,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/"+ pRecipeId.getPath()+"_from_coffee_machining")));

    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final ItemStack result;
        private final Ingredient ingredient;
        private final ItemStack neededOnOutput;
        private final int ticks;
        private final boolean needWater,needMilk;
        private final int count;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, ItemStack pResult, int pCount, Ingredient ingredient, int ticks,boolean needWater,boolean needMilk,ItemStack neededOnOutput, Advancement.Builder pAdvancement,
                      ResourceLocation pAdvancementId) {
            this.id = pId;
            this.neededOnOutput = neededOnOutput;
            this.ticks = ticks;
            this.needMilk = needMilk;
            this.needWater = needWater;
            this.result = pResult;
            this.count = pCount;
            this.ingredient = ingredient;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {

            pJson.addProperty("milk", needMilk);
            pJson.addProperty("water", needWater);
            pJson.addProperty("ticks",ticks);


            JsonObject onOutput = new JsonObject();
            onOutput.addProperty("item", BuiltInRegistries.ITEM.getKey(this.neededOnOutput.getItem()).toString());

            pJson.add("itemOnOutput",onOutput);
            JsonArray jsonarray = new JsonArray();
            jsonarray.add(ingredient.toJson());

            pJson.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", BuiltInRegistries.ITEM.getKey(this.result.getItem()).toString());
            jsonobject.addProperty("nbt", this.result.getOrCreateTag().toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }


            pJson.add("output", jsonobject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(KawaiiDishes.modId,
                    BuiltInRegistries.ITEM.getKey(this.result.getItem()).toString().replaceAll(KawaiiDishes.modId+":","")+"_from_coffee_machining");
        }

        @Override
        public RecipeSerializer<?> getType() {
            return CoffeeMachineRecipe.Serializer.INSTANCE;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
