 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class ProtectionPower extends AbstractEasyPower {
   public static final String ID = makeID("ProtectionPower");
     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 2;
     public ProtectionPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PowerType.BUFF, false, owner, amount);
         updateDescription();
     }


   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
   
   public int onLoseHp(int damageAmount) {
     flash();
     int actual_damage = damageAmount - this.amount;
     if (actual_damage < 0) {
       this.amount -= damageAmount;
       updateDescription();
       actual_damage = 0;
     } else {
       
       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, makeID("ProtectionPower")));
     }  return actual_damage;
   }
 }


