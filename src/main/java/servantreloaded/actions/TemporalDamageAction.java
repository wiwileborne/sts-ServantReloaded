 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;

 public class TemporalDamageAction
   extends AbstractGameAction {
   public TemporalDamageAction(int damage) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.info = new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL);
   }
   private DamageInfo info;
   
   public void update() {
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       if (mo != null && !mo.isDeadOrEscaped()) {
         this.info.applyPowers(this.info.owner, mo);
         mo.damage(this.info);
       } 
     } 
     this.isDone = true;
   }
 }


