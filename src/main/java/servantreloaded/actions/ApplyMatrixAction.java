 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.MatrixPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 
 
 public class ApplyMatrixAction
   extends AbstractGameAction
 {
   public void update() {
     if (this.target != null && this.target.hasPower(makeID("SatellitePower")))
       atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new MatrixPower(this.target,
               (this.target.getPower(makeID("SatellitePower"))).amount), (this.target.getPower(makeID("SatellitePower"))).amount));
     this.isDone = true;
   }
 }


