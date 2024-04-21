 package servantreloaded.powers;
 

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.actions.SatelliteAction;

 import java.util.Arrays;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;

 public class SatellitePower extends AbstractEasyPower {
     public static final String ID = makeID("SatellitePower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final AbstractPower.PowerType PWT = PowerType.BUFF;
     private int damage;
     private final int ATTACK = 4;


     public SatellitePower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }


   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
     updateDescription();
   }

     public int onAttacked(DamageInfo info, int damageAmount) {
         this.damage = ATTACK;
         if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {

             flash();
             if (this.owner.hasPower(BladesPower.ID)) {
                 this.damage += (this.owner.getPower(BladesPower.ID)).amount;
             }
             atb(new SatelliteAction(AbstractDungeon.player, info.owner, new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.NORMAL)));
         }
         return damageAmount;
     }

     public void onUseCard(AbstractCard card, UseCardAction action) {
         this.damage = ATTACK;
         if (card.type == AbstractCard.CardType.ATTACK) {
             flash();
             if (this.owner.hasPower(BladesPower.ID)) {
                 this.damage += this.owner.getPower(BladesPower.ID).amount;
             }
             if (card.target != AbstractCard.CardTarget.ALL_ENEMY) {
                 atb(new WaitAction(0.1F));
                 atb(new WaitAction(0.1F));
                 atb(new SatelliteAction(AbstractDungeon.player, action.target, new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.NORMAL)));
             }
             else {
                 atb(new SatelliteAction(AbstractDungeon.player, AbstractDungeon.getMonsters().getRandomMonster(true), new DamageInfo(this.owner, this.damage, DamageInfo.DamageType.NORMAL)));
             }
         }
     }
   
   public void onAfterUseCard(AbstractCard card, UseCardAction action) {
     updateDescription();
   }
 
   
   public void updateDescription() {
     this.damage = ATTACK;
     if (this.owner.hasPower(BladesPower.ID)){
         this.damage += (this.owner.getPower(BladesPower.ID)).amount;
     }
     this.description = DESCRIPTIONS[0] + this.damage + DESCRIPTIONS[1];
   }
 }


