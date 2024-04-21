 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 import servantreloaded.powers.SatellitePower;
 import static servantreloaded.util.Wiz.att;

 public class SatelliteAction
   extends AbstractGameAction {
     public SatelliteAction(AbstractPlayer p, AbstractCreature target, DamageInfo info) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.source = (AbstractCreature)p;
        this.target = target;
        this.info = info;
     }
   private final DamageInfo info;
   
   public void update() {
     if (this.source.hasPower(SatellitePower.ID)) {
       if (this.source.getPower(SatellitePower.ID).amount > 0 && this.target != null && !this.target.isDying && !this.target.halfDead && this.target.currentHealth > 0) {
 
         
         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
         
         this.info.applyPowers(this.info.owner, this.target);
         this.target.damage(this.info);
         att(new WaitAction(0.06F));
         if (this.target != null && this.target.hb != null) {
           att(new VFXAction(new servantreloaded.vfx.SatelliteDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
         }
         this.source.getPower(SatellitePower.ID).reducePower(1);
         this.source.getPower(SatellitePower.ID).updateDescription();
       } 
       
       if (this.source.getPower(SatellitePower.ID).amount == 0)
         att(new RemoveSpecificPowerAction(this.source, this.source, SatellitePower.ID));
     } 
     this.isDone = true;
   }

 }


