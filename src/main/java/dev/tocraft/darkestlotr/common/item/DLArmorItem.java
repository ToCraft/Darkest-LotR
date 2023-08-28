package dev.tocraft.darkestlotr.common.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemGroup;

public class DLArmorItem extends ArmorItem {

    public DLArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties properties) {
        super(material, slot, properties);
    }

    public DLArmorItem(IArmorMaterial material, EquipmentSlotType slot) {
        this(material, slot, new Properties().tab(ItemGroup.TAB_COMBAT));
    }
}
