 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.util.Wiz.atb;
 public class ReduceDebuffsAction
   extends AbstractGameAction {
   public ReduceDebuffsAction(AbstractCreature target, int protection) {
     this.target = target;
     this.amount = protection;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
     this.duration = 0.3F;
   }
   
   public void update() {
     if (this.duration == 0.3F && this.target != null) {
       if (this.target.hasPower("Weakened")) {
         atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new ProtectionPower(this.target, 
                 Math.min(999 * this.amount, (this.target.getPower("Weakened")).amount * this.amount)), 
               Math.min(999 * this.amount, (this.target.getPower("Weakened")).amount * this.amount)));
         atb((AbstractGameAction)new RemoveSpecificPowerAction(this.target, this.target, "Weakened"));
       } 
       if (this.target.hasPower("Vulnerable")) {
         atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new ProtectionPower(this.target,
                 Math.min(999 * this.amount, (this.target.getPower("Vulnerable")).amount * this.amount)), 
               Math.min(999 * this.amount, (this.target.getPower("Vulnerable")).amount * this.amount)));
         atb((AbstractGameAction)new RemoveSpecificPowerAction(this.target, this.target, "Vulnerable"));
       } 
       if (this.target.hasPower("Frail")) {
         atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new ProtectionPower(this.target, 
                 Math.min(999 * this.amount, (this.target.getPower("Frail")).amount * this.amount)), 
               Math.min(999 * this.amount, (this.target.getPower("Frail")).amount * this.amount)));
         atb((AbstractGameAction)new RemoveSpecificPowerAction(this.target, this.target, "Frail"));
       } 
     } 
     tickDuration();
   }
 }


