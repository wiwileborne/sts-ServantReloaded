 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ReturningBladeAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.*;

 public class TimeTheftPower extends AbstractEasyPower {
     public static String ID = makeID("TimeTheftPower");
     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final PowerType PWT = PowerType.BUFF;
     public static AbstractMonster target;
     private final boolean prediction;

     public TimeTheftPower(AbstractCreature owner, AbstractCreature source, int amount, boolean prediction) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
         target = (AbstractMonster)source;
         this.prediction = prediction;
     }

     public void atStartOfTurnPostDraw() {
         if (owner.hasPower(TrueSightPower.ID) || isValidTargetAndIntent(target,prediction)) {
             flash();
             applyEffects(owner, target);
         }

         atb(new ReducePowerAction(owner, owner, this, 999));
     }


     private void applyEffects(AbstractCreature player, AbstractMonster target) {
         flash();
         atb(new GainEnergyAction(this.amount));
         atb(new DrawCardAction(this.owner, this.amount));
     }
 
   
   public void updateDescription() {
     if (this.prediction) { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]; }
     else { this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.amount + DESCRIPTIONS[5]; }
   
   }
 }


