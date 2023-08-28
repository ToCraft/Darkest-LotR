package dev.tocraft.darkestlotr;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.tocraft.darkestlotr.common.DLRegistry;

@Mod("darkestlotr")
public class DarkestLotR
{
    public static final String modid = "darkestlotr";
    public static final Logger LOGGER = LoggerFactory.getLogger(DarkestLotR.class);

    public DarkestLotR() {
        MinecraftForge.EVENT_BUS.register(this);
        DLRegistry.registerItems();
    }
}
