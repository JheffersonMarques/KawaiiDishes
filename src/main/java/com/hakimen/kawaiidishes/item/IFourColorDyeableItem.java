package com.hakimen.kawaiidishes.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IFourColorDyeableItem {

    String colors = "PrimaryColors";
    String base = "Base";
    String overlay = "Overlay";

    String secondaryColors = "SecondaryColors";

    int defaultColor = 0xFFFFFF;
    default int getPrimaryBaseColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(base, Tag.TAG_ANY_NUMERIC) ? colorData.getInt(base) : defaultColor;
    }

    default int getPrimaryOverlayColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(overlay, Tag.TAG_ANY_NUMERIC) ? colorData.getInt(overlay) : defaultColor;
    }

    default boolean hasPrimaryBaseColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(base, Tag.TAG_ANY_NUMERIC);
    }

    default boolean hasPrimaryOverlayColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(colors);
        return colorData != null && colorData.contains(overlay, Tag.TAG_ANY_NUMERIC);
    }

    default void clearPrimaryBaseColor(ItemStack stack) {
        CompoundTag colorData = stack.getTagElement(colors);
        if (colorData != null && colorData.contains(base)) {
            colorData.remove(base);
        }
    }

    default void clearPrimaryOverlayColor(ItemStack stack) {
        CompoundTag colorData = stack.getTagElement(colors);
        if (colorData != null && colorData.contains(overlay)) {
            colorData.remove(overlay);
        }
    }

    boolean hasPrimaryOverlay(ItemStack stack);

    default void setPrimaryBaseColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(colors).putInt(base, color);
    }
    default void setPrimaryOverlayColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(colors).putInt(overlay, color);
    }

    static ItemStack dyePrimaryBase(ItemStack stack, List<DyeItem> dyes){
        ItemStack itemstack = ItemStack.EMPTY;

        IFourColorDyeableItem dyeableItem = null;

        int[] colorResult = {0,0,0};

        int baseColorCount = 0;

        Item item = stack.getItem();
        if(item instanceof IFourColorDyeableItem){
            dyeableItem = (IFourColorDyeableItem) item;
            itemstack = stack.copyWithCount(1);
            if (dyeableItem.hasPrimaryBaseColor(stack)) {
                int k = dyeableItem.getPrimaryBaseColor(itemstack);
                float r = (float)(k >> 16 & 255) / 255.0F;
                float g = (float)(k >> 8 & 255) / 255.0F;
                float b = (float)(k & 255) / 255.0F;
                colorResult[0] += (int)(r * 255.0F);
                colorResult[1] += (int)(g * 255.0F);
                colorResult[2] += (int)(b * 255.0F);
                ++baseColorCount;
            }
        }

        for(DyeItem dyeitem : dyes) {
            float[] dyeColors = dyeitem.getDyeColor().getTextureDiffuseColors();
            int r = (int)(dyeColors[0] * 255.0F);
            int g = (int)(dyeColors[1] * 255.0F);
            int b = (int)(dyeColors[2] * 255.0F);
            colorResult[0] += r;
            colorResult[1] += g;
            colorResult[2] += b;
            ++baseColorCount;
        }

        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        } else {
            int j1 = colorResult[0] / baseColorCount;
            int k1 = colorResult[1] / baseColorCount;
            int l1 = colorResult[2] / baseColorCount;
            int rgb = (j1 << 8) + k1;
            rgb = (rgb << 8) + l1;
            dyeableItem.setPrimaryBaseColor(itemstack, rgb);
            return itemstack;
        }
    }

    static ItemStack dyePrimaryOverlay(ItemStack stack, List<DyeItem> dyes){
        ItemStack itemstack = ItemStack.EMPTY;

        IFourColorDyeableItem dyeableItem = null;

        int[] colorResult = {0,0,0};

        int baseColorCount = 0;

        Item item = stack.getItem();
        if(item instanceof IFourColorDyeableItem){
            dyeableItem = (IFourColorDyeableItem) item;
            itemstack = stack.copyWithCount(1);
            if (dyeableItem.hasPrimaryOverlayColor(stack)) {
                int k = dyeableItem.getPrimaryOverlayColor(itemstack);
                float r = (float)(k >> 16 & 255) / 255.0F;
                float g = (float)(k >> 8 & 255) / 255.0F;
                float b = (float)(k & 255) / 255.0F;
                colorResult[0] += (int)(r * 255.0F);
                colorResult[1] += (int)(g * 255.0F);
                colorResult[2] += (int)(b * 255.0F);
                ++baseColorCount;
            }
        }

        for(DyeItem dyeitem : dyes) {
            float[] dyeColors = dyeitem.getDyeColor().getTextureDiffuseColors();
            int r = (int)(dyeColors[0] * 255.0F);
            int g = (int)(dyeColors[1] * 255.0F);
            int b = (int)(dyeColors[2] * 255.0F);
            colorResult[0] += r;
            colorResult[1] += g;
            colorResult[2] += b;
            ++baseColorCount;
        }

        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        } else {
            int r = colorResult[0] / baseColorCount;
            int g = colorResult[1] / baseColorCount;
            int b = colorResult[2] / baseColorCount;
            int rgb = (r << 8) + g;
            rgb = (rgb << 8) + b;
            dyeableItem.setPrimaryOverlayColor(itemstack, rgb);
            return itemstack;
        }
    }

    default int getSecondaryBaseColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(secondaryColors);
        return colorData != null && colorData.contains(base, Tag.TAG_ANY_NUMERIC) ? colorData.getInt(base) : defaultColor;
    }

    default int getSecondaryOverlayColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(secondaryColors);
        return colorData != null && colorData.contains(overlay, Tag.TAG_ANY_NUMERIC) ? colorData.getInt(overlay) : defaultColor;
    }

    default boolean hasSecondaryBaseColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(secondaryColors);
        return colorData != null && colorData.contains(base, Tag.TAG_ANY_NUMERIC);
    }

    default boolean hasSecondaryOverlayColor(ItemStack stack){
        CompoundTag colorData = stack.getTagElement(secondaryColors);
        return colorData != null && colorData.contains(overlay, Tag.TAG_ANY_NUMERIC);
    }

    default void clearSecondaryBaseColor(ItemStack stack) {
        CompoundTag colorData = stack.getTagElement(secondaryColors);
        if (colorData != null && colorData.contains(base)) {
            colorData.remove(base);
        }
    }

    default void clearSecondaryOverlayColor(ItemStack stack) {
        CompoundTag colorData = stack.getTagElement(secondaryColors);
        if (colorData != null && colorData.contains(overlay)) {
            colorData.remove(overlay);
        }
    }

    boolean hasSecondaryOverlay(ItemStack stack);

    default void setSecondaryBaseColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(secondaryColors).putInt(base, color);
    }
    default void setSecondaryOverlayColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(secondaryColors).putInt(overlay, color);
    }

    static ItemStack dyeSecondaryBase(ItemStack stack, List<DyeItem> dyes){
        ItemStack itemstack = ItemStack.EMPTY;

        IFourColorDyeableItem dyeableItem = null;

        int[] colorResult = {0,0,0};

        int baseColorCount = 0;

        Item item = stack.getItem();
        if(item instanceof IFourColorDyeableItem){
            dyeableItem = (IFourColorDyeableItem) item;
            itemstack = stack.copyWithCount(1);
            if (dyeableItem.hasPrimaryBaseColor(stack)) {
                int k = dyeableItem.getPrimaryBaseColor(itemstack);
                float r = (float)(k >> 16 & 255) / 255.0F;
                float g = (float)(k >> 8 & 255) / 255.0F;
                float b = (float)(k & 255) / 255.0F;
                colorResult[0] += (int)(r * 255.0F);
                colorResult[1] += (int)(g * 255.0F);
                colorResult[2] += (int)(b * 255.0F);
                ++baseColorCount;
            }
        }

        for(DyeItem dyeitem : dyes) {
            float[] dyeColors = dyeitem.getDyeColor().getTextureDiffuseColors();
            int r = (int)(dyeColors[0] * 255.0F);
            int g = (int)(dyeColors[1] * 255.0F);
            int b = (int)(dyeColors[2] * 255.0F);
            colorResult[0] += r;
            colorResult[1] += g;
            colorResult[2] += b;
            ++baseColorCount;
        }

        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        } else {
            int j1 = colorResult[0] / baseColorCount;
            int k1 = colorResult[1] / baseColorCount;
            int l1 = colorResult[2] / baseColorCount;
            int rgb = (j1 << 8) + k1;
            rgb = (rgb << 8) + l1;
            dyeableItem.setSecondaryBaseColor(itemstack, rgb);
            return itemstack;
        }
    }

    static ItemStack dyeSecondaryOverlay(ItemStack stack, List<DyeItem> dyes){
        ItemStack itemstack = ItemStack.EMPTY;

        IFourColorDyeableItem dyeableItem = null;

        int[] colorResult = {0,0,0};

        int baseColorCount = 0;

        Item item = stack.getItem();
        if(item instanceof IFourColorDyeableItem){
            dyeableItem = (IFourColorDyeableItem) item;
            itemstack = stack.copyWithCount(1);
            if (dyeableItem.hasPrimaryOverlayColor(stack)) {
                int k = dyeableItem.getPrimaryOverlayColor(itemstack);
                float r = (float)(k >> 16 & 255) / 255.0F;
                float g = (float)(k >> 8 & 255) / 255.0F;
                float b = (float)(k & 255) / 255.0F;
                colorResult[0] += (int)(r * 255.0F);
                colorResult[1] += (int)(g * 255.0F);
                colorResult[2] += (int)(b * 255.0F);
                ++baseColorCount;
            }
        }

        for(DyeItem dyeitem : dyes) {
            float[] dyeColors = dyeitem.getDyeColor().getTextureDiffuseColors();
            int r = (int)(dyeColors[0] * 255.0F);
            int g = (int)(dyeColors[1] * 255.0F);
            int b = (int)(dyeColors[2] * 255.0F);
            colorResult[0] += r;
            colorResult[1] += g;
            colorResult[2] += b;
            ++baseColorCount;
        }

        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        } else {
            int r = colorResult[0] / baseColorCount;
            int g = colorResult[1] / baseColorCount;
            int b = colorResult[2] / baseColorCount;
            int rgb = (r << 8) + g;
            rgb = (rgb << 8) + b;
            dyeableItem.setSecondaryOverlayColor(itemstack, rgb);
            return itemstack;
        }
    }
}
