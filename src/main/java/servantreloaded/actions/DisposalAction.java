 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.KnivesPower;
 import servantreloaded.powers.ProtectionPower;
 import static servantreloaded.util.Wiz.att;

 public class DisposalAction extends AbstractGameAction {
   private float startingDuration;
   
   public DisposalAction(AbstractPlayer p, int magicNumber) {
     this.amount = magicNumber;
     this.p = p;
     this.actionType = AbstractGameAction.ActionType.EXHAUST;
     this.startingDuration = Settings.ACTION_DUR_FAST;
     this.duration = this.startingDuration;
   }
   private AbstractPlayer p;
   public void update() {
     if (this.duration == this.startingDuration) {
       int count = AbstractDungeon.player.hand.size(); int i;
       for (i = 0; i < count; i++) {
         att((AbstractGameAction)new ApplyPowerAction(this.p, this.p, (AbstractPower)new ProtectionPower(this.p, this.amount), this.amount));
         att((AbstractGameAction)new ApplyPowerAction(this.p, this.p, (AbstractPower)new KnivesPower(this.p, 2), 2));
       } 
       for (i = 0; i < count; i++) {
         att((AbstractGameAction)new ExhaustAction(this.p, this.p, 1, true, true));
       }
     } 
     tickDuration();
   }
 }


