 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class MatrixPower extends AbstractEasyPower {
     public static final String ID = makeID("MatrixPower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public MatrixPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
   
   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
     return calculateDamageTakenAmount(damage, type);
   }
   
   private float calculateDamageTakenAmount(float damage, DamageInfo.DamageType type) {
     if (type != DamageInfo.DamageType.HP_LOSS && type != DamageInfo.DamageType.THORNS) {
       return damage * 0.5F;
     }
     return damage;
   }
   
   public int onAttacked(DamageInfo info, int damageAmount) {
     if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
       flash();
       atb(new ReducePowerAction(this.owner, this.owner, "MatrixPower", 1));
     } 
     return damageAmount;
   }
 
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 }


