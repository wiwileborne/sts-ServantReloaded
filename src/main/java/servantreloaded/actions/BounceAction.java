 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 import static servantreloaded.util.Wiz.atb;
 public class BounceAction
   extends AbstractGameAction {
   private DamageInfo info;
   private int times;
   private int count;
   private int baseDamage;
   
   public BounceAction(AbstractCreature source, AbstractCreature target, int baseDamage, int times) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.source = source;
     this.target = target;
     this.times = times;
     this.baseDamage = baseDamage;
     this.info = new DamageInfo(AbstractDungeon.player, baseDamage, DamageInfo.DamageType.NORMAL);
   }
 
 
   
   public void update() {
     this.count = 0;
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       if (mo != null && !mo.isDeadOrEscaped()) {
         this.count++;
       }
     } 
     if (this.target != null && this.target.hb != null)
     {
       
       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
     }
 
 
     
     this.info.applyPowers(this.info.owner, this.target);
     this.target.damage(this.info);
     this.times--;
 
     
     if (this.count > 1 && this.times > 0) {
       this.source = this.target;
       this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
       while (this.source == this.target)
         this.target = AbstractDungeon.getMonsters().getRandomMonster(true);
       atb((AbstractGameAction)new VFXAction((AbstractGameEffect)new servantreloaded.vfx.BounceEffect(this.source.hb.cX, this.source.hb.cY, this.target.hb.cX, this.target.hb.cY)));
       
       atb((AbstractGameAction)new WaitAction(0.1F));
       atb((AbstractGameAction)new WaitAction(0.1F));
       atb((AbstractGameAction)new WaitAction(0.1F));
       atb(new BounceAction(this.source, this.target, this.baseDamage, this.times));
     } 
     this.isDone = true;
   }
 }


