package dev.tocraft.darkestlotr.common;

import dev.tocraft.darkestlotr.DarkestLotR;
import dev.tocraft.darkestlotr.common.item.DLArmorItem;
import dev.tocraft.darkestlotr.common.material.DLMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DLRegistry {
    private static final DeferredRegister<Item> ITEMS;

    // Malee-Weapons
    public static final RegistryObject<SwordItem> EXAMPLESWORD;
    // Armor
    public static final RegistryObject<ArmorItem> EXAMPLE_HEAD;
    public static final RegistryObject<ArmorItem> EXAMPLE_CHEST;
    public static final RegistryObject<ArmorItem> EXAMPLE_LEGS;
    public static final RegistryObject<ArmorItem> EXAMPLE_FEET;


    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DarkestLotR.modid);
        // Register Malee-Weapons
        EXAMPLESWORD = ITEMS.register("example_sword", () -> new SwordItem(DLMaterial.EXAMPLE.asTool(), 3, 2.4F, new SwordItem.Properties().tab(ItemGroup.TAB_COMBAT)));
        // Register Armor
        EXAMPLE_HEAD = ITEMS.register("example_helmet", () -> new DLArmorItem(DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.HEAD));
        EXAMPLE_CHEST = ITEMS.register("example_chestplate", () -> new DLArmorItem(DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.CHEST));
        EXAMPLE_LEGS = ITEMS.register("example_leggings", () -> new DLArmorItem(DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.LEGS));
        EXAMPLE_FEET = ITEMS.register("example_boots", () -> new DLArmorItem(DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.FEET));
    }

    public static void registerItems() {
        // Register all Items
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DarkestLotR.LOGGER.info("Registered Items");
    }
}