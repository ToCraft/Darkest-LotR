package dev.tocraft.darkestlotr.common;

import java.util.function.Supplier;

import dev.tocraft.darkestlotr.DarkestLotR;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
    private static final DeferredRegister<Item> ITEMS;

    public static final RegistryObject<Item> EXAMPLESWORD;

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DarkestLotR.modid);
        // Register Malee-Weapons
        EXAMPLESWORD = regItem("examplesword", () -> new SwordItem(ItemTier.IRON, 3, 2.4F, new SwordItem.Properties().tab(ItemGroup.TAB_COMBAT)));
    }

    public static void registerItems() {
        // Register all Items
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DarkestLotR.LOGGER.info("Registered Items");
    }

    private static RegistryObject<Item> regItem(String name, Supplier<Item> sup) {
		RegistryObject<Item> item = ITEMS.register(name, sup);
		return item;
	}
}
