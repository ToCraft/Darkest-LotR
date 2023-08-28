package dev.tocraft.darkestlotr.common;

import dev.tocraft.darkestlotr.DarkestLotR;
import dev.tocraft.darkestlotr.common.material.DLMaterial;
import lotr.common.init.LOTRItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Food.Builder;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DLRegistry {
    private static final DeferredRegister<Item> ITEMS;

    // Malee-Weapons
    public static final RegistryObject<Item> EXAMPLE_SWORD;
    // Ranged Weapons
    public static final RegistryObject<Item> EXAMPLE_BOW;
    // Armor
    public static final RegistryObject<Item> EXAMPLE_HEAD;
    public static final RegistryObject<Item> EXAMPLE_CHEST;
    public static final RegistryObject<Item> EXAMPLE_LEGS;
    public static final RegistryObject<Item> EXAMPLE_FEET;
    // Shields
    public static final RegistryObject<Item> EXAMPLE_SHIELD;
    // Food
    public static final RegistryObject<Item> EXAMPLE_FOOD;


    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DarkestLotR.modid);
        // Register Malee-Weapons
        EXAMPLE_SWORD = regSwordItem("example_sword", DLMaterial.EXAMPLE.asTool(), 3, 2.4F);
        // Register Ranged Weapons
        EXAMPLE_BOW = regBowItem("example_bow");
        // Register Armor
        EXAMPLE_HEAD = regArmorItem("example_helmet", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.HEAD);
        EXAMPLE_CHEST = regArmorItem("example_chestplate", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.CHEST);
        EXAMPLE_LEGS = regArmorItem("example_leggings", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.LEGS);
        EXAMPLE_FEET = regArmorItem("example_boots", DLMaterial.EXAMPLE.asArmor(), EquipmentSlotType.FEET);
        // Register Shields
        EXAMPLE_SHIELD = regShieldItem("example_shield");
        // Register Food
        EXAMPLE_FOOD = regFoodItem("example_food", 8, 1.0F);
    }

    public static void registerItems() {
        // Register all Items
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        DarkestLotR.LOGGER.info("Registered Items");
    }

    private static RegistryObject<Item> regSwordItem(String name, IItemTier tier, int atkdamage, float atkspeed) {
        return regSwordItem(name, tier, atkdamage, atkspeed, new Item.Properties().tab(ItemGroup.TAB_COMBAT));
    }

    private static RegistryObject<Item> regSwordItem(String name, IItemTier tier, int atkdamage, float atkspeed, Item.Properties properties) {
        return ITEMS.register(name, () -> new SwordItem(tier, atkdamage, atkspeed, properties));
    }

    private static RegistryObject<Item> regBowItem(String name) {
        return regBowItem(name, new SwordItem.Properties().tab(ItemGroup.TAB_COMBAT));
    }

    private static RegistryObject<Item> regBowItem(String name, Item.Properties properties) {
        return ITEMS.register(name, () -> new BowItem(properties));
    }

    private static RegistryObject<Item> regArmorItem(String name, IArmorMaterial material, EquipmentSlotType slot) {
        return regArmorItem(name, material,  slot, new Item.Properties().tab(ItemGroup.TAB_COMBAT));
    }

    private static RegistryObject<Item> regArmorItem(String name, IArmorMaterial material,  EquipmentSlotType slot, Item.Properties properties) {
        return ITEMS.register(name, () -> new ArmorItem(material, slot, properties));
    }

    private static RegistryObject<Item> regShieldItem(String name) {
        return regShieldItem(name, new Item.Properties().tab(ItemGroup.TAB_COMBAT));
    }

    private static RegistryObject<Item> regShieldItem(String name, Item.Properties properties) {
        return ITEMS.register(name, () -> new ShieldItem(properties));
    }

    private static RegistryObject<Item> regFoodItem(String name, int nutrions, float saturaionMod) {
        return regFoodItem(name, new Item.Properties().tab(ItemGroup.TAB_FOOD).food(new Builder().nutrition(nutrions).saturationMod(saturaionMod).build()));
    }

    private static RegistryObject<Item> regFoodItem(String name, Item.Properties properties) {
        return ITEMS.register(name, () -> new ShieldItem(properties));
    }
}
