package com.hakimen.kawaiidishes;

import com.hakimen.kawaiidishes.client.block_entity_renderers.CoffeeMachineRenderer;
import com.hakimen.kawaiidishes.client.block_entity_renderers.CoffeePressRenderer;
import com.hakimen.kawaiidishes.client.block_entity_renderers.IceCreamMachineRenderer;
import com.hakimen.kawaiidishes.client.entity.FoxTailArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.SeatRenderer;
import com.hakimen.kawaiidishes.client.entity.maid_dresses.CatMaidDressArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.CatTailArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.maid_dresses.FoxMaidDressArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.maid_dresses.MaidDressArmorRenderer;
import com.hakimen.kawaiidishes.client.entity.ThighHighsArmorRenderer;
import com.hakimen.kawaiidishes.items.armor.*;
import com.hakimen.kawaiidishes.registry.BlockEntityRegister;
import com.hakimen.kawaiidishes.registry.EntityRegister;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = KawaiiDishes.modId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KawaiiDishesClient {
    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(CatTailArmorItem.class, CatTailArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(MaidDressArmorItem.class, MaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(CatMaidArmorItem.class, CatMaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(ThighHighsArmorItem.class, ThighHighsArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(FoxMaidArmorItem.class, FoxMaidDressArmorRenderer::new);
        GeoArmorRenderer.registerArmorRenderer(FoxTailArmorItem.class, FoxTailArmorRenderer::new);

    }
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(BlockEntityRegister.coffeePress.get(), CoffeePressRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.coffeeMachine.get(), CoffeeMachineRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegister.iceCreamMachine.get(), IceCreamMachineRenderer::new);

        event.registerEntityRenderer(EntityRegister.SEAT.get(), SeatRenderer::new);
    }
}
