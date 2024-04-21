 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.att;

 public class DoubleProtectionAction
   extends AbstractGameAction {
   public DoubleProtectionAction(AbstractCreature target) {
     this.duration = 0.3F;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
     this.target = target;
     this.source = target;
   }
 
   
   public void update() {
     if (this.duration == 0.3F && this.target != null && this.target.hasPower(makeID("ProtectionPower"))
) {
       att((AbstractGameAction)new ApplyPowerAction(this.target, this.source, (AbstractPower)new ProtectionPower(this.source,
               (this.target.getPower(makeID("ProtectionPower"))
).amount), (this.target.getPower(makeID("ProtectionPower"))
).amount));
     }
     
     tickDuration();
   }
 }


