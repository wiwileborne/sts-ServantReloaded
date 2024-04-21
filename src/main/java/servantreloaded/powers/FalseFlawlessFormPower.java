 package servantreloaded.powers;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class FalseFlawlessFormPower extends AbstractEasyPower {
     public static final String ID = makeID("FalseFlawlessFormPower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     public FalseFlawlessFormPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, true, owner, amount);
     }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 
   
   public float atDamageGive(float damage, DamageInfo.DamageType type) {
     if (this.owner.hasPower(makeID("FlawlessFormPower"))
) return damage;
     if (type == DamageInfo.DamageType.NORMAL && 
       this.owner.hasPower("Weakened")) return damage / 0.75F;
     
     return damage;
   }
 
   
   public float modifyBlock(float blockAmount) {
     if (this.owner.hasPower(makeID("FlawlessFormPower"))
) return blockAmount;
     if (this.owner.hasPower("Frail")) return blockAmount / 0.75F; 
     return blockAmount;
   }
 
   
   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
     if (this.owner.hasPower(makeID("FlawlessFormPower"))
) return damage;
     if (type == DamageInfo.DamageType.NORMAL)
     {
       if (this.owner.hasPower("Vulnerable")) {
         if (AbstractDungeon.player.hasRelic("Odd Mushroom")) {
           return damage / 1.25F;
         }
         return damage / 1.5F;
       } 
     }
     return damage;
   }
 
   
   public void atEndOfRound() {
     if (this.amount == 0) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, makeID("FalseFlawlessFormPower")));
     } else {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, makeID("FalseFlawlessFormPower"), 1));
     } 
   }
 }


