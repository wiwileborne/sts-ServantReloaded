package servantreloaded.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import servantreloaded.ServantReloadedMod;
import servantreloaded.cards._DummyAttack;
import servantreloaded.cards._DummyNotAttack;
import servantreloaded.powers.*;
import servantreloaded.screens.VisionScreen;

import java.util.ArrayList;
import java.util.Objects;

import static servantreloaded.util.Wiz.applyToSelf;
import static servantreloaded.util.Wiz.atb;





public class VisionAction
        extends AbstractGameAction
{
    private boolean prediction;
    private ArrayList<AbstractCard> list = new ArrayList<>();
    private int amount2;
    private AbstractCard card;

    public VisionAction(AbstractCreature p, AbstractCreature m, int amount, int amount2, AbstractCard card) {
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
        this.source = p;
        this.target = m;
        this.amount = amount;
        this.amount2 = amount2;
        this.card = card;
        _DummyAttack _DummyAttack = new _DummyAttack();
        this.list.add(_DummyAttack);
        _DummyNotAttack _DummyNotAttack = new _DummyNotAttack();
        this.list.add(_DummyNotAttack);
    }


    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {
            ServantReloadedMod.vs.open(this.list, null, VisionScreen.TEXT[1]);
            tickDuration();
            return;
        }
        if (Objects.equals(ServantReloadedMod.vs.prediction.cardID, _DummyAttack.ID)) { this.prediction = true; }
        else if (Objects.equals(ServantReloadedMod.vs.prediction.cardID, _DummyNotAttack.ID)) { this.prediction = false; }

        if (this.card instanceof servantreloaded.cards.Read) {
            applyToSelf(new ReadPower(this.source, this.target, this.amount, this.amount2, this.prediction));
            this.isDone = true;
        }
        if (this.card instanceof servantreloaded.cards.Snipe) {
            applyToSelf(new SnipePower(this.source, this.target, this.amount, this.prediction));
            this.isDone = true;
        }
        if (this.card instanceof servantreloaded.cards.TimeTheft) {
            applyToSelf(new TimeTheftPower(this.source, this.target, this.amount, this.prediction));
            this.isDone = true;
        }
        if (this.card instanceof servantreloaded.cards.Deadline) {
            applyToSelf(new DeadlinePower(this.source, this.target, this.amount, this.prediction));
            this.isDone = true;
        }
        if (this.card instanceof servantreloaded.cards.ReturningBlade) {
            applyToSelf(new ReturningBladePower(this.source, this.target, this.amount, this.prediction, this.card));
            this.isDone = true;
        }

        tickDuration();
    }
}


