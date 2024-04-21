 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.VulnerablePower;

 import static servantreloaded.util.Wiz.applyToSelf;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.*;

 public class DeadlinePower extends AbstractEasyPower {
     public static String ID = makeID("DeadlinePower");
     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final PowerType PWT = PowerType.BUFF;
     public static AbstractMonster target;
     private final boolean prediction;

   public DeadlinePower(AbstractCreature owner, AbstractCreature source, int amount, boolean prediction) {
       super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     target = (AbstractMonster)source;
     this.prediction = prediction;
   }
     public void atStartOfTurnPostDraw() {
         if (owner.hasPower(TrueSightPower.ID) || isValidTargetAndIntent(target,prediction)) {
             flash();
             applyToSelf(new AmplifyDamagePower(target, amount));
             applyToSelf(new VulnerablePower(target, amount, false));
         }

         atb(new ReducePowerAction(owner, owner, this, 999));
     }
   
   public void updateDescription() {
     if (this.prediction) { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]; }
     else { this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]; }
   
   }
 }


