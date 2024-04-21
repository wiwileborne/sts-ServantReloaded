package servantreloaded.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PostfixPatchInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

@SpirePatch(
        clz = GridCardSelectScreen.class,
        method = "update"
)
public class PatchGridCardSelectScreen {
    private static AbstractCard lastHoveredCard;

    public PatchGridCardSelectScreen() {
    }


    public static void Prefix(GridCardSelectScreen __instance, AbstractCard ___hoveredCard) {


        if (___hoveredCard != null && (lastHoveredCard == null || lastHoveredCard != ___hoveredCard)) {
            CardCrawlGame.sound.playV("CARD_OBTAIN", 0.4F);
            lastHoveredCard = ___hoveredCard;
        } else if (___hoveredCard == null) {
            lastHoveredCard = null;
        }
    }


}
