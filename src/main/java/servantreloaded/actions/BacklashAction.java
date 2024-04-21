 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.FrailPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import static servantreloaded.util.Wiz.atb;
 public class BacklashAction extends AbstractGameAction {
   public BacklashAction(int amount) {
     this.amount = amount;
     this.actionType = AbstractGameAction.ActionType.DEBUFF;
     this.target = AbstractDungeon.player;
     this.duration = 0.3F;
   }
   
   public void update() {
     if (this.duration == 0.3F && this.target != null) {
       for (int i = 0; i < this.amount; i++) {
         atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new WeakPower(this.target, 1, false), 1));
         atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new VulnerablePower(this.target, 1, false), 1));
         atb((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new FrailPower(this.target, 1, false), 1));
       } 
     }
     tickDuration();
   }
 }
