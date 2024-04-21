 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;

 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.util.Wiz.atb;
 public class HightailAction extends AbstractGameAction {
   private int energyOnUse = -1; private boolean freeToPlayOnce = false;
   private AbstractPlayer p;
   private int magicNumber;
   
   public HightailAction(AbstractPlayer p, int magicNumber, boolean freeToPlayOnce, int energyOnUse) {
     this.energyOnUse = energyOnUse;
     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
     this.p = p;
     this.magicNumber = magicNumber;
     this.freeToPlayOnce = freeToPlayOnce;
     this.duration = Settings.ACTION_DUR_XFAST;
   }
   
   public void update() {
     int effect = EnergyPanel.totalCount;
     if (this.energyOnUse != -1) {
       effect = this.energyOnUse;
     }
     if (this.p.hasRelic("Chemical X")) {
       effect += 2;
       this.p.getRelic("Chemical X").flash();
     } 
     if (effect > 0) {
       for (int i = 0; i < effect; i++)
         atb((AbstractGameAction)new ApplyPowerAction(this.p, this.p, (AbstractPower)new ProtectionPower(this.p, this.magicNumber), this.magicNumber));
       if (!this.freeToPlayOnce) {
         this.p.energy.use(EnergyPanel.totalCount);
       }
     } 
     atb(new BacklashAction(1));
     this.isDone = true;
   }
 }


