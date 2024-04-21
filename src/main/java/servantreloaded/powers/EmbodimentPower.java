 package servantreloaded.powers;

 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import servantreloaded.cards.TemporalDefense;
 import servantreloaded.cards.TemporalEssence;
 import servantreloaded.cards.TemporalMisd;
 import servantreloaded.cards.TemporalSlicing;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.powerHelper.getDescByID;
 import static servantreloaded.util.powerHelper.getNameByID;
 import static servantreloaded.util.Wiz.atb;
 public class EmbodimentPower extends AbstractEasyPower {
     public static final String ID = makeID("EmbodimentPower");

     public static final String[] DESCRIPTIONS = getDescByID(ID);
     public static final String NAME = getNameByID(ID);
     public static final int PRIORITY = 5;
     public static final PowerType PWT = PowerType.BUFF;
     public EmbodimentPower(AbstractCreature owner, int amount) {
         super(ID, NAME,PRIORITY ,PWT, false, owner, amount);
     }
 
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
   }
 
   
   public void atEndOfTurn(boolean isPlayer) {
     flash();
     
     for (int i = 0; i < this.amount; i++) {
       AbstractCard c; int randomNum = AbstractDungeon.miscRng.random(9);
       if (randomNum == 0) { c = (new TemporalSlicing()).makeCopy(); }
       else if (randomNum == 1) { c = (new TemporalMisd()).makeCopy(); }
       else if (randomNum == 2) { c = (new TemporalDefense()).makeCopy(); }
       else if (randomNum == 3) { c = (new TemporalSlicing()).makeCopy(); }
       else if (randomNum == 4) { c = (new TemporalMisd()).makeCopy(); }
       else if (randomNum == 5) { c = (new TemporalDefense()).makeCopy(); }
       else if (randomNum == 6) { c = (new TemporalSlicing()).makeCopy(); }
       else if (randomNum == 7) { c = (new TemporalMisd()).makeCopy(); }
       else if (randomNum == 8) { c = (new TemporalDefense()).makeCopy(); }
       else { c = (new TemporalEssence()).makeCopy(); }
        atb(new MakeTempCardInDrawPileAction(c, 1, true, false));
     } 
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 }


