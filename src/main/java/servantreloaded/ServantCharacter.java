package servantreloaded;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import servantreloaded.cards.Exchange;
import servantreloaded.cards.KidneyShot;
import servantreloaded.relics.Uniform;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import servantreloaded.cards.Defend;
import servantreloaded.cards.Strike;
import servantreloaded.screens.VisionScreen;

import java.util.ArrayList;

import static servantreloaded.ServantCharacter.Enums.SILVER;
import static servantreloaded.ServantCharacter.Enums.Servant;
import static servantreloaded.ServantReloadedMod.*;

public class ServantCharacter extends CustomPlayer {

    static final String ID = makeID("Servant");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;
    public static final float[] layerSpeeds = new float[] { 80.0F, 40.0F, -40.0F, 20.0F, 0.0F, 16.0F, 8.0F, -8.0F, 5.0F, 0.0F };

    public ServantCharacter(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, makeCharacterPath("mainChar/orb/vfx.png"), layerSpeeds), new SpriterAnimation(
                makeCharacterPath("mainChar/static.scml")));
        initializeClass(MAIN,
                SHOULDER2,
                SHOULDER1,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
        if (ServantReloadedMod.vs == null) {
            ServantReloadedMod.vs = new VisionScreen();
      }
        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                65, 65, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(KidneyShot.ID);
        retVal.add(Exchange.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Uniform.ID);
        UnlockTracker.markRelicAsSeen(Uniform.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playV("ATTACK_DAGGER_6", 1.75F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    private static final String[] orbTextures = {
            makeCharacterPath("mainChar/orb/layer1.png"),
            makeCharacterPath("mainChar/orb/layer2.png"),
            makeCharacterPath("mainChar/orb/layer3.png"),
            makeCharacterPath("mainChar/orb/layer4.png"),
            makeCharacterPath("mainChar/orb/layer4.png"),
            makeCharacterPath("mainChar/orb/layer6.png"),
            makeCharacterPath("mainChar/orb/layer1d.png"),
            makeCharacterPath("mainChar/orb/layer2d.png"),
            makeCharacterPath("mainChar/orb/layer3d.png"),
            makeCharacterPath("mainChar/orb/layer4d.png"),
            makeCharacterPath("mainChar/orb/layer5d.png"),
    };

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return SILVER;
    }

    @Override
      public Color getCardTrailColor() {
          return CardHelper.getColor(131.0F, 156.0F, 165.0F);
      }
    @Override
   public String getCustomModeCharacterButtonSoundKey() {
         return "ATTACK_DAGGER_6";
      }

     public BitmapFont getEnergyNumFont() {
         return FontHelper.energyNumFontBlue;
       }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new KidneyShot();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new ServantCharacter(this.name, Servant);
    }

    @Override
    public Color getCardRenderColor() {return CardHelper.getColor(131.0F, 156.0F, 165.0F);}

    @Override
    public Color getSlashAttackColor() {return CardHelper.getColor(131.0F, 156.0F, 165.0F);}

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL };
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[10];
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass Servant;
        @SpireEnum(name = "SERVANT")
        public static AbstractCard.CardColor SILVER;
        @SpireEnum(name = "SERVANT")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}
