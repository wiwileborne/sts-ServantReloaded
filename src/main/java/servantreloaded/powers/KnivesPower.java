package servantreloaded.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import static servantreloaded.ServantReloadedMod.makeID;
import static servantreloaded.util.powerHelper.*;

public class KnivesPower extends AbstractEasyPower {
    public static final String ID = makeID("KnivesPower");
    public static final String[] DESCRIPTIONS = getDescByID(ID);
    public static final String NAME = getNameByID(ID);
    public KnivesPower(AbstractCreature owner, int amount) {
        super(ID, NAME, 5,PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateDescription();
    }

    @Override
    public void reducePower(int reduceAmount) {
        if (this.amount - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount = 0;
        } else {
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
