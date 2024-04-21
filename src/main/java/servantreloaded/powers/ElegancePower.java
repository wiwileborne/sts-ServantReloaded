 package servantreloaded.powers;
 

 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class ElegancePower extends AbstractEasyPower {
     public static final String ID = makeID("ElegancePower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public ElegancePower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
 
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
   
   public float modifyBlock(float blockAmount) {
     return blockAmount + this.amount;
   }
 }


