 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import servantreloaded.actions.ReturningBladeAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.*;

 public class ReturningBladePower extends AbstractEasyPower {
     public static String ID = makeID("ReturningBladePower");
     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final PowerType PWT = PowerType.BUFF;
     public static AbstractMonster target;
     private final boolean prediction;
     private AbstractCard itself;

     public ReturningBladePower(AbstractCreature owner, AbstractCreature source, int amount, boolean prediction, AbstractCard c) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
         this.itself = c;
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
         atb(new ReturningBladeAction(target, this.amount, this.itself));
     }
   
   public void updateDescription() {
     if (this.prediction) { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
     else { this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]; }
   
   }
 }


