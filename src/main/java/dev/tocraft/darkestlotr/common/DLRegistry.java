package dev.tocraft.darkestlotr.common;

import dev.tocraft.darkestlotr.DarkestLotR;
import dev.tocraft.darkestlotr.common.material.DLMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
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
        EXAMPLESWORD = regSwordItem("example_sword", DLMaterial.EXAMPLE.asTool(), 3, 2.4F);
        // Register Armor
        EXAMPLE_HEAD = regArmorItem("example_helmet", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.HEAD);
        EXAMPLE_CHEST = regArmorItem("example_chestplate", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.CHEST);
        EXAMPLE_LEGS = regArmorItem("example_leggings", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.LEGS);
        EXAMPLE_FEET = regArmorItem("example_boots", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.FEET);
    }

    public static void registerItems() {
        // Register all Items
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DarkestLotR.LOGGER.info("Registered Items");
    }

    private static RegistryObject<SwordItem> regSwordItem(String name, IItemTier tier, int atkdamage, float atkspeed) {
        return regSwordItem(name, tier, atkdamage, atkspeed, new SwordItem.Properties().tab(ItemGroup.TAB_COMBAT));
    }

    private static RegistryObject<SwordItem> regSwordItem(String name, IItemTier tier, int atkdamage, float atkspeed, Item.Properties properties) {
        return ITEMS.register(name, () -> new SwordItem(tier, atkdamage, atkspeed, properties));
    }

    private static RegistryObject<ArmorItem> regArmorItem(String name, IArmorMaterial material, EquipmentSlotType slot) {
        return regArmorItem(name, material,  slot, new ArmorItem.Properties().tab(ItemGroup.TAB_COMBAT));
    }

    private static RegistryObject<ArmorItem> regArmorItem(String name, IArmorMaterial material,  EquipmentSlotType slot, Item.Properties properties) {
        return ITEMS.register(name, () -> new ArmorItem(material, slot, properties));
    }
}
