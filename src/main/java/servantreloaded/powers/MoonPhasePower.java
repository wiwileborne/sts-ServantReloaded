 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class MoonPhasePower extends AbstractEasyPower {
     public static final String ID = makeID("MoonPhasePower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final AbstractPower.PowerType PWT = AbstractPower.PowerType.BUFF;
     private int BLOCK = 0;

     public MoonPhasePower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
 
   
   public void stackPower(int stackAmount) {
     flash();
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
   
   public void atEndOfTurn(boolean isPlayer) {
     this.BLOCK = 0;
     if (this.owner.hasPower("Weakened")) this.BLOCK += (AbstractDungeon.player.getPower("Weakened")).amount * this.amount; 
     if (this.owner.hasPower("Vulnerable")) this.BLOCK += (AbstractDungeon.player.getPower("Vulnerable")).amount * this.amount; 
     if (this.owner.hasPower("Frail")) this.BLOCK += (AbstractDungeon.player.getPower("Frail")).amount * this.amount; 
     if (this.BLOCK != 0) atb(new GainBlockAction(this.owner, this.owner, this.BLOCK));
   
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 }


