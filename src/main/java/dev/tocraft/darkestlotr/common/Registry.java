package dev.tocraft.darkestlotr.common;

import java.util.function.Supplier;

import dev.tocraft.darkestlotr.DarkestLotR;
import lotr.common.init.LOTRMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
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

    // Malee-Weapons
    public static final RegistryObject<Item> EXAMPLESWORD;
    // Armor
    public static final RegistryObject<Item> EXAMPLEARMOR_HEAD;
    public static final RegistryObject<Item> EXAMPLEARMOR_CHEST;
    public static final RegistryObject<Item> EXAMPLEARMOR_LEGS;
    public static final RegistryObject<Item> EXAMPLEARMOR_FEET;


    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DarkestLotR.modid);
        // Register Malee-Weapons
        EXAMPLESWORD = regItem("examplesword", () -> new SwordItem(ItemTier.IRON, 3, 2.4F, new SwordItem.Properties().tab(ItemGroup.TAB_COMBAT)));
        // Register Armor
        EXAMPLEARMOR_HEAD = regItem("examplearmor_head", () -> new ArmorItem(LOTRMaterial.BRONZE.asArmor(), EquipmentSlotType.HEAD, new ArmorItem.Properties().tab(ItemGroup.TAB_COMBAT)));
        EXAMPLEARMOR_CHEST = regItem("examplearmor_chest", () -> new ArmorItem(LOTRMaterial.BRONZE.asArmor(), EquipmentSlotType.CHEST, new ArmorItem.Properties().tab(ItemGroup.TAB_COMBAT)));
        EXAMPLEARMOR_LEGS = regItem("examplearmor_legs", () -> new ArmorItem(LOTRMaterial.BRONZE.asArmor(), EquipmentSlotType.LEGS, new ArmorItem.Properties().tab(ItemGroup.TAB_COMBAT)));
        EXAMPLEARMOR_FEET = regItem("examplearmor_feet", () -> new ArmorItem(LOTRMaterial.BRONZE.asArmor(), EquipmentSlotType.FEET, new ArmorItem.Properties().tab(ItemGroup.TAB_COMBAT)));


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
