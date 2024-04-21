 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class DancingSilverAction extends AbstractGameAction {
   public DancingSilverAction(int times) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.BLOCK;
     this.target = AbstractDungeon.player;
     this.source = AbstractDungeon.player;
     this.amount = times;
   }
 
   
   public void update() {
     if (this.target != null && this.target.hasPower(makeID("SatellitePower"))
)
       atb((AbstractGameAction)new GainBlockAction(this.target, this.target, this.amount * 
             (this.target.getPower(makeID("SatellitePower"))
).amount));
     this.isDone = true;
   }
 }


