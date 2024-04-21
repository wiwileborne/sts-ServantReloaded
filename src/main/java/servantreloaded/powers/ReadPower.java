 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.applyToSelf;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.*;

 public class ReadPower extends AbstractEasyPower {
     public static String ID = makeID("ReadPower");
     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final PowerType PWT = PowerType.BUFF;
     public static AbstractMonster target;
     private final boolean prediction;
     private int w;

     public ReadPower(AbstractCreature owner, AbstractCreature source, int amount,int amount2, boolean prediction ) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
         this.w = amount2;
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
         atb(new ApplyPowerAction(player, player, new ProtectionPower(player, this.amount), this.amount));
         atb(new ApplyPowerAction(target, player, new WeakPower(target, this.w, false), this.w));
     }

 
   
   public void updateDescription() {
     if (this.prediction) { this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.w + DESCRIPTIONS[2]; }
     else { this.description = DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4] + this.w + DESCRIPTIONS[5]; }
   
   }
 }


