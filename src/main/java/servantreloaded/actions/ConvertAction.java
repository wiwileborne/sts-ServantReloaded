 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 import servantreloaded.powers.KnivesPower;
 import servantreloaded.powers.SatellitePower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.att;

 public class ConvertAction extends AbstractGameAction {
   public ConvertAction(int amount) {
     this.amount = amount;
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
     this.target = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_XFAST;
   }
   private int stack;
   public void update() {
     if (this.target != null && 
       this.target.hasPower(KnivesPower.ID)
) {
       this.stack = Math.min(this.amount, (this.target.getPower(KnivesPower.ID)
).amount);
       att((AbstractGameAction)new ReducePowerAction(this.target, this.target, KnivesPower.ID, this.stack));
       att((AbstractGameAction)new ApplyPowerAction(this.target, this.target, (AbstractPower)new SatellitePower(this.target, this.stack), this.stack));
       if (this.target.hasPower(makeID("SurpressingFirePower"))
) {
         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
         this.target.addBlock((this.target.getPower(makeID("SurpressingFirePower"))
).amount * this.stack);
       } 
     } 
     
     this.isDone = true;
   }
 }


