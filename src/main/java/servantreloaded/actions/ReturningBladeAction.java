 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
 import static servantreloaded.util.Wiz.atb;

 public class ReturningBladeAction extends AbstractGameAction {
   private AbstractCard itself;
   
   public ReturningBladeAction(AbstractCreature target, int damage, AbstractCard itself) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.target = target;
     this.damage = damage;
     this.itself = itself;
   }
   private int where; private int damage;
   
   public void update() {
     atb((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
     atb((AbstractGameAction)new VFXAction(AbstractDungeon.player, (AbstractGameEffect)new CleaveEffect(), 0.3F));
     atb(new TemporalDamageAction(this.damage));
     for (AbstractCard c : AbstractDungeon.player.discardPile.group) { if (c == this.itself) this.where = 0;  }
      for (AbstractCard c : AbstractDungeon.player.drawPile.group) { if (c == this.itself) this.where = 1;  }
      for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) { if (c == this.itself) this.where = 2;  }
      for (AbstractCard c : AbstractDungeon.player.hand.group) { if (c == this.itself) this.where = 3;  }
      if (this.where == 0) {
       if (AbstractDungeon.player.hand.size() == 10) {
         AbstractDungeon.player.createHandIsFullDialog();
       } else {
         AbstractDungeon.player.discardPile.removeCard(this.itself);
         AbstractDungeon.player.hand.addToTop(this.itself);
       }
     
     } else if (this.where == 1) {
       if (AbstractDungeon.player.hand.size() == 10) {
         AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
         AbstractDungeon.player.createHandIsFullDialog();
       } else {
         AbstractDungeon.player.drawPile.removeCard(this.itself);
         AbstractDungeon.player.hand.addToTop(this.itself);
       }
     
     } else if (this.where == 2) {
       if (AbstractDungeon.player.hand.size() == 10) {
         AbstractDungeon.player.drawPile.moveToDiscardPile(this.itself);
         AbstractDungeon.player.createHandIsFullDialog();
       } else {
         AbstractDungeon.player.exhaustPile.removeCard(this.itself);
         this.itself.unfadeOut();
         AbstractDungeon.player.hand.addToTop(this.itself);
       } 
     } 
     AbstractDungeon.player.hand.refreshHandLayout();
     AbstractDungeon.player.hand.applyPowers();
     AbstractDungeon.player.hand.glowCheck();
     this.isDone = true;
   }
 }


