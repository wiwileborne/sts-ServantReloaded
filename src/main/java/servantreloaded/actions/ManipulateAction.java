 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;

 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import servantreloaded.powers.ManipulatePower;

 import static servantreloaded.util.Wiz.atb;
 public class ManipulateAction extends AbstractGameAction {
   private int energyOnUse = -1; private boolean freeToPlayOnce = false;
   private AbstractPlayer p;
   private boolean canUpgrade = false;
   
   public ManipulateAction(AbstractPlayer p, boolean freeToPlayOnce, int energyOnUse, boolean canUpgrade) {
     this.energyOnUse = energyOnUse;
     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
     this.p = p;
     this.freeToPlayOnce = freeToPlayOnce;
     this.canUpgrade = canUpgrade;
     this.duration = Settings.ACTION_DUR_XFAST;
   }
   
   public void update() {
     int effect = EnergyPanel.totalCount;
     if (this.energyOnUse != -1) {
       effect = this.energyOnUse + 1;
     }
     if (this.p.hasRelic("Chemical X")) {
       effect += 2;
       this.p.getRelic("Chemical X").flash();
     } 
     if (!this.canUpgrade) effect++; 
     if (effect > 0) {
       atb((AbstractGameAction)new ApplyPowerAction(this.p, this.p, (AbstractPower)new ManipulatePower(this.p, effect), effect));
       if (!this.freeToPlayOnce) {
         this.p.energy.use(EnergyPanel.totalCount);
       }
     } 
     this.isDone = true;
   }
 }


