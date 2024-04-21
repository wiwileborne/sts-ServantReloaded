 package servantreloaded.powers;

 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class AmplifyDamagePower extends AbstractEasyPower {
   public static final String ID = makeID("AmplifyDamagePower");

   public static final String[] DESCRIPTIONS = getDescByID(ID);
   public static final String NAME = getNameByID(ID);
   public static final int PRIORITY = 10;
   public static final PowerType PWT = PowerType.DEBUFF;
   public AmplifyDamagePower(AbstractCreature owner, int amount) {
     super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     this.amount = amount;
     updateDescription();
   }

   public void onInitialApplication() {
     if (AbstractDungeon.player.hasRelic("PaperSwan") && 
       AbstractDungeon.cardRandomRng.randomBoolean()) {
       AbstractDungeon.player.getRelic("PaperSwan").flash();
       this.amount++;
     } 
   }
 
 
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
     if (AbstractDungeon.player.hasRelic("PaperSwan") && 
       AbstractDungeon.cardRandomRng.randomBoolean()) {
       AbstractDungeon.player.getRelic("PaperSwan").flash();
       this.amount++;
     } 
   }
 
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
     this.type = AbstractPower.PowerType.DEBUFF;
   }
 
   
   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
     if (type == DamageInfo.DamageType.NORMAL && this.owner != null)
     {
       return damage + this.amount;
     }
     return damage;
   }
 }


