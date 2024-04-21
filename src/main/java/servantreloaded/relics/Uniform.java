package servantreloaded.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;
import servantreloaded.powers.KnivesPower;

import static servantreloaded.ServantReloadedMod.makeID;
import static servantreloaded.util.Wiz.applyToSelfTop;

public class Uniform extends AbstractEasyRelic {
    public static final String ID = makeID("Uniform");

    public Uniform() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL, ServantCharacter.Enums.SILVER);
    }

    private static final int KNIVES = 6;

    public void atBattleStart() {
        applyToSelfTop(new KnivesPower(AbstractDungeon.player, KNIVES));
    }
    public AbstractRelic makeCopy() {
        return (AbstractRelic) new Uniform();
    }
}
