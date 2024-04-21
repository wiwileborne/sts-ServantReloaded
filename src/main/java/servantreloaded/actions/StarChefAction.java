 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import static servantreloaded.util.Wiz.atb;
 public class StarChefAction
   extends AbstractGameAction {
   private float startingDuration;
   private AbstractPlayer p;
   public static int numExhausted;
   
   public StarChefAction(AbstractPlayer p, int numCards) {
     this.amount = numCards;
     this.p = p;
     this.actionType = AbstractGameAction.ActionType.EXHAUST;
     this.startingDuration = Settings.ACTION_DUR_FAST;
     this.duration = this.startingDuration;
   }
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
       if (this.p.hand.size() == 0) {
         this.isDone = true;
         
         return;
       } 
       numExhausted = this.amount;
       AbstractDungeon.handCardSelectScreen.open("manger", this.amount, true, true);
       tickDuration();
       
       return;
     } 
     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
         this.p.hand.moveToExhaustPile(c);
         atb((AbstractGameAction)new GainEnergyAction(1));
       } 
       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
     } 
     tickDuration();
   }
 }


