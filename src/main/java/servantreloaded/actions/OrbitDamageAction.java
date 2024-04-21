 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class OrbitDamageAction extends AbstractGameAction {
   public OrbitDamageAction(int[] damage) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.attackEffect = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
     this.damage = damage;
   }
 
   
   public void update() {
     if (AbstractDungeon.player.hasPower(makeID("SatellitePower"))
) {
       for (int i = 0; i < (AbstractDungeon.player.getPower(makeID("SatellitePower"))
).amount; i++) {
         atb((AbstractGameAction)new DamageAllEnemiesAction(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL, this.attackEffect, true));
       }
     }
     
     this.isDone = true;
   }
   
   public int[] damage;
 }


