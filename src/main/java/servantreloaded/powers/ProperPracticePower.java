 package servantreloaded.powers;
 

 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.actions.ProperPracticeAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class ProperPracticePower extends AbstractEasyPower {
     public static final String ID = makeID("ProperPracticePower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 21;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public ProperPracticePower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
 
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
   
   public void atStartOfTurnPostDraw() {
     flash();
     atb(new ProperPracticeAction(AbstractDungeon.player, this.amount));
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 }


