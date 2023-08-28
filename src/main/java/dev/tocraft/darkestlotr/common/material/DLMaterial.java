package dev.tocraft.darkestlotr.common.material;

import java.util.function.Supplier;

import dev.tocraft.darkestlotr.DarkestLotR;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum DLMaterial {
    EXAMPLE("example", 2, 230, 5.0F, 1.5F, 10, 0.5F, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, () -> Ingredient.of(Items.IRON_INGOT));

	private static final int[] ARMOR_DURABILITY_ARRAY = {13, 15, 16, 11};
	private String materialName;
	private DLMaterial.AsTool asTool;
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final LazyValue toolRepairMaterial;
	private DLMaterial.AsArmor asArmor;
	private final int armorDurabilityFactor;
	private final int[] armorProtectionArray;
	private final SoundEvent armorSoundEvent;
	private final float toughness;
	private final LazyValue armorRepairMaterial;

	DLMaterial(String name, int lvl, int uses, float eff, float atk, int ench, float pr, SoundEvent sound, float tough, Supplier repair) {
		this(name, lvl, uses, eff, atk, ench, pr, sound, tough, repair, repair);
	}

	DLMaterial(String name, int lvl, int uses, float eff, float atk, int ench, float pr, SoundEvent sound, float tough, Supplier repair, Supplier armorRepair) {
		materialName = DarkestLotR.modid + ":" + name;
		harvestLevel = lvl;
		maxUses = uses;
		efficiency = eff;
		attackDamage = atk;
		enchantability = ench;
		toolRepairMaterial = new LazyValue(repair);
		armorDurabilityFactor = Math.round(maxUses * 0.06F);
		armorProtectionArray = DLMaterial.ArmorHelper.getArmorProtectionArray(pr);
		armorSoundEvent = sound;
		toughness = tough;
		armorRepairMaterial = new LazyValue(armorRepair);
	}

	public DLMaterial.AsArmor asArmor() {
		if (asArmor == null) {
			asArmor = new DLMaterial.AsArmor(this);
		}

		return asArmor;
	}

	public DLMaterial.AsTool asTool() {
		if (asTool == null) {
			asTool = new DLMaterial.AsTool(this);
		}

		return asTool;
	}

	private static class ArmorHelper {
		private static final float[] ARMOR_PART_WEIGHTING = {0.14F, 0.32F, 0.4F, 0.14F};

		public static int[] getArmorProtectionArray(float protection) {
			int[] armorArray = new int[ARMOR_PART_WEIGHTING.length];

			for (int i = 0; i < armorArray.length; ++i) {
				armorArray[i] = Math.round(ARMOR_PART_WEIGHTING[i] * protection * 25.0F);
			}

			return armorArray;
		}
	}
    
    public static class AsArmor implements IArmorMaterial {
		private final DLMaterial materialReference;

		public AsArmor(DLMaterial m) {
			materialReference = m;
		}

		@Override
		public int getDefenseForSlot(EquipmentSlotType slot) {
			return materialReference.armorProtectionArray[slot.getIndex()];
		}

		@Override
		public int getDurabilityForSlot(EquipmentSlotType slot) {
			return DLMaterial.ARMOR_DURABILITY_ARRAY[slot.getIndex()] * materialReference.armorDurabilityFactor;
		}

		@Override
		public int getEnchantmentValue() {
			return materialReference.enchantability;
		}

		@Override
		public SoundEvent getEquipSound() {
			return materialReference.armorSoundEvent;
		}

		@Override
		public float getKnockbackResistance() {
			return 0.0F;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public String getName() {
			return materialReference.materialName;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return (Ingredient) materialReference.armorRepairMaterial.get();
		}

		@Override
		public float getToughness() {
			return materialReference.toughness;
		}
	}

	public static class AsTool implements IItemTier {
		private final DLMaterial materialReference;

		public AsTool(DLMaterial m) {
			materialReference = m;
		}

		@Override
		public float getAttackDamageBonus() {
			return materialReference.attackDamage;
		}

		@Override
		public int getEnchantmentValue() {
			return materialReference.enchantability;
		}

		@Override
		public int getLevel() {
			return materialReference.harvestLevel;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return (Ingredient) materialReference.toolRepairMaterial.get();
		}

		@Override
		public float getSpeed() {
			return materialReference.efficiency;
		}

		@Override
		public int getUses() {
			return materialReference.maxUses;
		}
	}
}
