 package servantreloaded.actions;


 import com.badlogic.gdx.math.MathUtils;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 import servantreloaded.powers.AmplifyDamagePower;
 import servantreloaded.powers.KnivesPower;
 import servantreloaded.powers.SurpressingFirePower;

 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.Wiz.att;

 public class ThrowKnivesAction extends AbstractGameAction {
   private final DamageInfo info;
     private final String debuff;
   public ThrowKnivesAction(AbstractPlayer p, AbstractCreature target, DamageInfo info, String debuff) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.source = p;
     this.target = target;
     this.info = info;
     this.debuff = debuff;
   }

   
   public void update() {
     if (this.source.hasPower(KnivesPower.ID)) {
       if (this.source.getPower(KnivesPower.ID).amount > 0) {
         if (this.debuff != null && (this.debuff == "Draw" || this.debuff == "Golden"))
           this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
         if (this.target != null && !this.target.isDying && !this.target.halfDead && this.target.currentHealth > 0) {
           if (this.debuff == null || this.debuff != "Golden") {
             AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
           } else {
             CardCrawlGame.sound.play("ATTACK_FAST");
           }
           this.info.applyPowers(this.info.owner, this.target);
           this.target.damage(this.info);
           att((AbstractGameAction)new WaitAction(0.06F));
           if (this.target != null && this.target.hb != null) {
             if (this.debuff != null && this.debuff == "Golden") {
               att((AbstractGameAction)new VFXAction((AbstractGameEffect)new servantreloaded.vfx.KDEffect(
                       MathUtils.random(1200.0F, 2000.0F) * Settings.scale, AbstractDungeon.floorY + 
                       MathUtils.random(-100.0F, 500.0F) * Settings.scale)));
             } else {
               att((AbstractGameAction)new VFXAction((AbstractGameEffect)new servantreloaded.vfx.ServantDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
             } 
           }
           this.source.getPower(KnivesPower.ID)
.reducePower(1);
           this.source.getPower(KnivesPower.ID)
.updateDescription();
           
           if (this.source.hasPower(SurpressingFirePower.ID)) {
             AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.source.hb.cX, this.source.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
             this.source.addBlock((this.source.getPower(SurpressingFirePower.ID)).amount);
           } 
           if (this.debuff != null && this.debuff == "Draw")
             atb((AbstractGameAction)new DrawCardAction(this.source, 1)); 
           if (this.debuff != null && this.debuff == "Weakened")
             atb((AbstractGameAction)new ApplyPowerAction(this.target, this.source, (AbstractPower)new WeakPower(this.target, 1, false), 1)); 
           if (this.debuff != null && this.debuff == "Vulnerable")
             atb((AbstractGameAction)new ApplyPowerAction(this.target, this.source, (AbstractPower)new VulnerablePower(this.target, 1, false), 1)); 
           if (this.debuff != null && this.debuff == "Amplify Damage") {
             atb((AbstractGameAction)new ApplyPowerAction(this.target, this.source, (AbstractPower)new AmplifyDamagePower(this.target, 1), 1));
           }
         } 
       } 
       if ((this.source.getPower(KnivesPower.ID)).amount == 0)
         atb((AbstractGameAction)new RemoveSpecificPowerAction(this.source, this.source, KnivesPower.ID));
     } 
     this.isDone = true;
   }
 }


