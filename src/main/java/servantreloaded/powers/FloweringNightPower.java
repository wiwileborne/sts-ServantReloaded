 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class FloweringNightPower extends AbstractEasyPower {
     public static final String ID = makeID("FloweringNightPower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public FloweringNightPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (6 - this.amount2) + DESCRIPTIONS[2];
   }
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
     for (int i = 0; i < this.amount && 
       this.amount2 < 6; i++) {
       atb(new DrawCardAction(this.owner, 1));
       this.amount2++;
       updateDescription();
     } 
   }
   
   public void atEndOfTurn(boolean isPlayer) {
     this.amount2 = 0;
     updateDescription();
   }
 }


