package com.warfactory.openpersistence.proxy;

import com.warfactory.openpersistence.Config;
import com.warfactory.openpersistence.Logger;
import com.warfactory.openpersistence.OpenPersitance;
import com.warfactory.openpersistence.compat.CompatManager;
import com.warfactory.openpersistence.compat.modular_warfare.MWCompat;
import com.warfactory.openpersistence.compat.modular_warfare.MWNetworkHandler;
import com.warfactory.openpersistence.entity.EntityPersistentPlayer;
import com.warfactory.openpersistence.events.PlayerEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import static com.warfactory.openpersistence.compat.CompatManager.ID_MW;

public class CommonProxy {

    public static PlayerEvents PLAYER_EVENTS = new PlayerEvents();
    public static MWCompat MW_COMPAT = new MWCompat();

    public void preInit(FMLPreInitializationEvent event) {
        Configuration c;
        try {
            c = new Configuration(event.getSuggestedConfigurationFile());
            Config.init(c);
        } catch (Exception e) {
            Logger.warning("Could not create config file: " + e.getMessage());
        }
        Logger.setLogger(event.getModLog());
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(PLAYER_EVENTS);
        if(CompatManager.isLoaded(ID_MW)){
            MinecraftForge.EVENT_BUS.register(MW_COMPAT);
            MWNetworkHandler.registerPackets();
        }
        EntityRegistry.registerModEntity(new ResourceLocation(OpenPersitance.MODID, "player"), EntityPersistentPlayer.class, "player", 133704, OpenPersitance.getInstance(), 128, 1, true);
    }

    public void postInit(FMLPostInitializationEvent event) {
        if(CompatManager.isLoaded(ID_MW) && Config.DEBUG) {
            Logger.warning("Persistent Players: Modular Warfare detected!");
        }
    }
}
