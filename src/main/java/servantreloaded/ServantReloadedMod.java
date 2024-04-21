package servantreloaded;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import basemod.helpers.RelicType;
import basemod.ModPanel;
import basemod.ModLabel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import servantreloaded.cards.AbstractEasyCard;
import servantreloaded.cards.cardvars.AbstractEasyDynamicVariable;
import servantreloaded.cards.testCard;
import servantreloaded.relics.AbstractEasyRelic;
import servantreloaded.screens.VisionScreen;
import servantreloaded.util.ProAudio;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static servantreloaded.util.LocalizationHelper.LoadCards;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ServantReloadedMod implements
        PostInitializeSubscriber,
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        AddAudioSubscriber {

    public static final String modID = "servantreloaded";
    public static String makeID(String idText) {
        return modID + ":" + idText;
    }
    public static Map JsonCards;
    public static Color characterColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1); // This should be changed eventually
    public static VisionScreen vs;
    public static final String MAIN = makeCharacterPath("mainChar/main.png");
    public static final String SHOULDER1 = makeCharacterPath("mainChar/shoulder.png");
    public static final String SHOULDER2 = makeCharacterPath("mainChar/shoulder2.png");
    public static final String CORPSE = makeCharacterPath("mainChar/corpse.png");
    private static final String ATTACK_S_ART = makeImagePath("512/attack.png");
    private static final String SKILL_S_ART = makeImagePath("512/skill.png");
    private static final String POWER_S_ART = makeImagePath("512/power.png");
    private static final String CARD_ENERGY_S = makeImagePath("512/energy.png");
    private static final String TEXT_ENERGY = makeImagePath("512/text_energy.png");
    private static final String ATTACK_L_ART = makeImagePath("1024/attack.png");
    private static final String SKILL_L_ART = makeImagePath("1024/skill.png");
    private static final String POWER_L_ART = makeImagePath("1024/power.png");
    private static final String CARD_ENERGY_L = makeImagePath("1024/energy.png");
    private static final String CHARSELECT_BUTTON = makeImagePath("charSelect/charButton.png");
    private static final String CHARSELECT_PORTRAIT = makeImagePath("charSelect/charBG.png");


    public static Settings.GameLanguage[] SupportedLanguages = {
            Settings.GameLanguage.ENG,
            Settings.GameLanguage.FRA,
    };

    private String getLangString() {
        for (Settings.GameLanguage lang : SupportedLanguages) {
            if (lang.equals(Settings.language)) {
                return Settings.language.name().toLowerCase();
            }
        }
        return "eng";
    }

    public ServantReloadedMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(ServantCharacter.Enums.SILVER, characterColor, characterColor, characterColor,
                characterColor, characterColor, characterColor, characterColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(makeUIPath("BRBadge.png"));
        ModPanel settingsPanel = new ModPanel();
        String[] TEXT = CardCrawlGame.languagePack.getUIString(makeID("ConfigMenu")).TEXT;
        settingsPanel.addUIElement(new ModLabel(TEXT[3], 400.0F, 700.0F, settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, TEXT[0], TEXT[1], TEXT[2], settingsPanel);
        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }
    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }
    public static String makeRelicPathOutline(String resourcePath) {
        return modID + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }
    public static String makeUIPath(String resourcePath) {
        return modID + "Resources/images/ui/" + resourcePath;
    }
    public static String makeCharacterPath(String resourcePath)
    {
        return modID + "Resources/images/char/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath,String ext) {
        return modID + "Resources/images/cards/" + resourcePath +ext;
    }

    public static void initialize() {
        ServantReloadedMod servant = new ServantReloadedMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new ServantCharacter(ServantCharacter.characterStrings.NAMES[1], ServantCharacter.Enums.Servant),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, ServantCharacter.Enums.Servant);
    }

    @Override
    public void receiveEditRelics() {
        System.out.println("ServantReloaded" + " : "+ "Auto Relics Add");
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        System.out.println("ServantReloaded" + " : "+ "Auto add Cards");
        new AutoAdd(modID)
                .packageFilter(AbstractEasyDynamicVariable.class)
                .any(DynamicVariable.class, (info, var) ->
                        BaseMod.addDynamicVariable(var));
        new AutoAdd(modID)
                .packageFilter(AbstractEasyCard.class)
                .setDefaultSeen(true)
                .any(AbstractEasyCard.class, (info, card) -> {
                    // Vérifie si la classe de la carte ne commence pas par "servantreloaded:Temporal"
                    if (!info.ignore && !Objects.equals(card.cardID, testCard.ID) && !card.cardID.startsWith("Temporal") && !card.cardID.startsWith("_Dummy") ) {
                        BaseMod.addCard(card);
                        if (info.seen) {
                            UnlockTracker.unlockCard(card.cardID);
                        }
                    }
                });
    }

/*    public void receiveSetUnlocks() {
        BaseMod.addUnlockBundle(new CustomUnlockBundle("Manipulate", "Moondial", "Embodiment"), TheServantEnum.THE_SERVANT, 0);
        UnlockTracker.addCard("Manipulate");
        UnlockTracker.addCard("Moondial");
        UnlockTracker.addCard("Embodiment");
        BaseMod.addUnlockBundle(new CustomUnlockBundle(UnlockType.RELIC, "Broom", "KneeBrace", "Pan"), TheServantEnum.THE_SERVANT, 1);
        UnlockTracker.addRelic("Broom");
        UnlockTracker.addRelic("KneeBrace");
        UnlockTracker.addRelic("Pan");
        BaseMod.addUnlockBundle(new CustomUnlockBundle("Deadline", "TimeTheft", "TrueSight"), TheServantEnum.THE_SERVANT, 2);
        UnlockTracker.addCard("Deadline");
        UnlockTracker.addCard("TimeTheft");
        UnlockTracker.addCard("TrueSight");
        BaseMod.addUnlockBundle(new CustomUnlockBundle(UnlockType.RELIC, "PaperSwan", "RomanBracelet", "OldScarf"), TheServantEnum.THE_SERVANT, 3);
        UnlockTracker.addRelic("PaperSwan");
        UnlockTracker.addRelic("RomanBracelet");
        UnlockTracker.addRelic("OldScarf");
        BaseMod.addUnlockBundle(new CustomUnlockBundle("Orbit", "DancingSilver", "SilverMatrix"), TheServantEnum.THE_SERVANT, 4);
        UnlockTracker.addCard("Orbit");
        UnlockTracker.addCard("DancingSilver");
        UnlockTracker.addCard("SilverMatrix");
    }*/

    @Override
    public void receiveEditStrings() {
        System.out.println("ServantReloaded" + " : "+ "Loading Strings..");
        String pathCards = modID + "Resources/localization/" + getLangString() + "/Cardstrings.json";
        BaseMod.loadCustomStringsFile(CardStrings.class, pathCards);
        LoadCards(pathCards);
        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/" + getLangString() + "/Relicstrings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/" + getLangString() + "/Charstrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/" + getLangString() + "/Powerstrings.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/" + getLangString() + "/UIstrings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class, modID + "Resources/localization/" + getLangString() + "/Orbstrings.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, modID + "Resources/localization/" + getLangString() + "/Stancestrings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class, modID + "Resources/localization/" + getLangString() + "/Potionstrings.json");
    }

    @Override
    public void receiveAddAudio() {
        for (ProAudio a : ProAudio.values())
            BaseMod.addAudio(makeID(a.name()), makePath("audio/" + a.name().toLowerCase() + ".ogg"));
    }

    @Override
    public void receiveEditKeywords() {
        System.out.println("ServantReloaded" + " : "+ "Loading Keywords");
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/" + getLangString() + "/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

}
