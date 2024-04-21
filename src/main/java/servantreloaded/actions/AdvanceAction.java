package servantreloaded.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static servantreloaded.util.Wiz.att;
import java.util.ArrayList;

public class AdvanceAction extends AbstractGameAction {
    ArrayList<AbstractCard> zero_cost = new ArrayList<>(); private AbstractPlayer p;

    public AdvanceAction(int numCards) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, numCards);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.amount <= 0) {
            this.isDone = true;
            return;
        }
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            this.isDone = true;
            return;
        }
        if (AbstractDungeon.player.hand.size() == 10) {
            AbstractDungeon.player.createHandIsFullDialog();
            this.isDone = true;
            return;
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.costForTurn == 0)
                this.zero_cost.add(c);
        }
        if (this.zero_cost.size() != 0) {
            AbstractCard c = this.zero_cost.get(0);
            this.p.hand.addToHand(c);
            c.lighten(false);
            this.p.drawPile.removeCard(c);
            this.p.hand.refreshHandLayout();
        }
        this.amount--;
        if (this.amount != 0)
            att(new AdvanceAction(this.amount));
        this.isDone = true;
    }
}
