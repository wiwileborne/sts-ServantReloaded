 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import servantreloaded.cards.TemporalDefense;
 import servantreloaded.cards.TemporalEssence;
 import servantreloaded.cards.TemporalMisd;
 import servantreloaded.cards.TemporalSlicing;

 import static servantreloaded.util.Wiz.atb;
 public class MoondialAction extends AbstractGameAction {
   public MoondialAction(int amount) {
     this.amount = amount;
     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
     this.target = AbstractDungeon.player;
     this.duration = Settings.ACTION_DUR_XFAST;
   }
   
   public void update() {
     if (this.target != null)
     {
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
          atb((AbstractGameAction)new MakeTempCardInHandAction(c, false));
       } 
     }
     this.isDone = true;
   }
 }


