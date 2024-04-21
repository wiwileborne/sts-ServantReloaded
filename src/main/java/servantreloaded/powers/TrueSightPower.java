 package servantreloaded.powers;
 

 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class TrueSightPower  extends AbstractEasyPower {
     public static final String ID = makeID("TrueSightPower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 4;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public TrueSightPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }


