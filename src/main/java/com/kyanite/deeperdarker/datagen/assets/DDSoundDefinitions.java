package com.kyanite.deeperdarker.datagen.assets;

import com.kyanite.deeperdarker.DeeperDarker;
import com.kyanite.deeperdarker.content.DDSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class DDSoundDefinitions extends SoundDefinitionsProvider {
    public DDSoundDefinitions(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DeeperDarker.MOD_ID, existingFileHelper);
    }

    @Override
    public void registerSounds() {
        add(DDSounds.PORTAL_GROAN, subtitle("ambience.portal.groan").with(sound("ambience/portal/groan1"), sound("ambience/portal/groan2"), sound("ambience/portal/groan3"), sound("ambience/portal/groan4")));
        add(DDSounds.SCULK_STONE_BREAK, subtitle("block.generic.break").with(sound("block/sculk_stone/break1"), sound("block/sculk_stone/break2"), sound("block/sculk_stone/break3"), sound("block/sculk_stone/break4"), sound("block/sculk_stone/break5"), sound("block/sculk_stone/break6"), sound("block/sculk_stone/break7")));
        add(DDSounds.SCULK_STONE_FALL, definition().with(sound("block/sculk_stone/step1"), sound("block/sculk_stone/step2"), sound("block/sculk_stone/step3"), sound("block/sculk_stone/step4"), sound("block/sculk_stone/step5"), sound("block/sculk_stone/step6")));
        add(DDSounds.SCULK_STONE_HIT, subtitle("block.generic.hit").with(sound("block/sculk_stone/step1"), sound("block/sculk_stone/step2"), sound("block/sculk_stone/step3"), sound("block/sculk_stone/step4"), sound("block/sculk_stone/step5"), sound("block/sculk_stone/step6")));
        add(DDSounds.SCULK_STONE_PLACE, subtitle("block.generic.place").with(sound("block/sculk_stone/place1"), sound("block/sculk_stone/place2"), sound("block/sculk_stone/place3"), sound("block/sculk_stone/place4"), sound("block/sculk_stone/place5")));
        add(DDSounds.SCULK_STONE_STEP, subtitle("block.generic.footsteps").with(sound("block/sculk_stone/step1"), sound("block/sculk_stone/step2"), sound("block/sculk_stone/step3"), sound("block/sculk_stone/step4"), sound("block/sculk_stone/step5"), sound("block/sculk_stone/step6")));
        add(DDSounds.VASE_BREAK, subtitle("block.generic.break").with(sound("block/vase/break1"), sound("block/vase/break2"), sound("block/vase/break3"), sound("block/vase/break4"), sound("block/vase/break5")));
        add(DDSounds.VASE_FALL, definition().with(sound("block/vase/step1"), sound("block/vase/step2"), sound("block/vase/step3")));
        add(DDSounds.VASE_HIT, subtitle("block.generic.hit").with(sound("block/vase/step1"), sound("block/vase/step2"), sound("block/vase/step3")));
        add(DDSounds.VASE_PLACE, subtitle("block.generic.place").with(sound("block/vase/place1"), sound("block/vase/place2"), sound("block/vase/place3"), sound("block/vase/place4")));
        add(DDSounds.VASE_STEP, subtitle("block.generic.footsteps").with(sound("block/vase/step1"), sound("block/vase/step2"), sound("block/vase/step3")));
        add(DDSounds.SNAPPER_AMBIENT, subtitle("entity.snapper.ambient").with(sound("entity/sculk_snapper/ambient")));
        add(DDSounds.SNAPPER_BITE, subtitle("entity.snapper.bite").with(sound("entity/sculk_snapper/bite")));
        add(DDSounds.SNAPPER_HURT, subtitle("entity.snapper.hurt").with(sound("entity/sculk_snapper/hurt")));
        add(DDSounds.SNAPPER_SNIFF, subtitle("entity.snapper.sniff").with(sound("entity/sculk_snapper/sniff")));
        add(DDSounds.TRANSMITTER_ERROR, subtitle("item.transmitter.error").with(sound("item/transmitter/error")));
        add(DDSounds.TRANSMITTER_LINK, subtitle("item.transmitter.link").with(sound("item/transmitter/link")));
        add(DDSounds.TRANSMITTER_OPEN, subtitle("item.transmitter.open").with(sound("item/transmitter/open")));
        add(DDSounds.TRANSMITTER_UNLINK, subtitle("item.transmitter.unlink").with(sound("item/transmitter/unlink")));
    }

    private SoundDefinition subtitle(String subtitle) {
        return definition().subtitle("subtitles." + subtitle);
    }

    protected static SoundDefinition.Sound sound(String location) {
        return sound(new ResourceLocation(DeeperDarker.MOD_ID, location));
    }
}
