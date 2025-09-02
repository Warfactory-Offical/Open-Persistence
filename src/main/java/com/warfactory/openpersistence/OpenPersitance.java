package com.warfactory.openpersistence;

import com.warfactory.openpersistence.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = OpenPersitance.MODID, version = OpenPersitance.VERSION, acceptedMinecraftVersions = OpenPersitance.MC_VERSION)
public class OpenPersitance {

    public static final String MODID = Tags.MODID;
    public static final String VERSION = Tags.VERSION;
    public static final String MC_VERSION = "[1.12.2]";

    @Mod.Instance
    private static OpenPersitance INSTANCE;

    @SidedProxy(clientSide = "com.warfactory.openpersistence.proxy.ClientProxy", serverSide = "com.warfactory.openpersistence.proxy.CommonProxy")
    public static CommonProxy proxy;

    public OpenPersitance() {
        INSTANCE = this;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    public static OpenPersitance getInstance() {
        return INSTANCE;
    }

}