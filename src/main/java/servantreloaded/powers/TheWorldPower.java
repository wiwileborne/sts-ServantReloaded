 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.actions.MummifiedAction;

 import java.util.ArrayList;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class TheWorldPower  extends AbstractEasyPower {
     public static final String ID = makeID("TheWorldPower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public ArrayList<AbstractCard> zero_cost = new ArrayList<>();
     private boolean doNothing;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public TheWorldPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }

   public void onInitialApplication() {
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c.costForTurn == 0) { this.zero_cost.add(c); continue; }
        c.setCostForTurn(-9);
     } 
   }
   
   public void onDrawOrDiscard() {
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       c.setCostForTurn(-9);
     }
   }
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
     flash();
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, makeID("TheWorldPower")));
     if (AbstractDungeon.player.hasRelic("Mummified Hand"))
       atb(new MummifiedAction(card, this));
     if (card instanceof servantreloaded.cards.TheWorld) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new TheWorldPower(AbstractDungeon.player, -1), -1));
     }
   }
   
   public void onRemove() {
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       for (AbstractCard c2 : this.zero_cost) {
         if (c == c2)
           this.doNothing = true; 
       }  if (!this.doNothing) {
         c.costForTurn = c.cost;
         c.isCostModifiedForTurn = false;
       } 
       this.doNothing = false;
     } 
   }
   
   public void atEndOfTurn(boolean isPlayer) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, makeID("TheWorldPower")));
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }


