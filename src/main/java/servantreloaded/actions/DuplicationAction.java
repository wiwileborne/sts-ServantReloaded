 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.TalkAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.colorless.Madness;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import servantreloaded.ServantReloadedMod;
 import servantreloaded.screens.VisionScreen;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;

 public class DuplicationAction
   extends AbstractGameAction
 {
   private float startingDuration;
   private AbstractPlayer p;
   public static int numExhausted;
   
   public DuplicationAction(AbstractPlayer p, int numCards) {
     this.amount = numCards;
     this.p = p;
     this.actionType = AbstractGameAction.ActionType.EXHAUST;
     this.startingDuration = Settings.ACTION_DUR_FAST;
     this.duration = this.startingDuration;
   }
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
       if (this.p.hand.isEmpty()) {
         this.isDone = true;
         
         return;
       } 
       numExhausted = this.amount;
         String[] TEXT = CardCrawlGame.languagePack.getUIString(makeID("HandCardSelectScreen")).TEXT;
         AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true);
         tickDuration();
         return;
     }

       if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
           atb(new MakeTempCardInHandAction(c, false));
         AbstractCard d = c.makeStatEquivalentCopy();
         if (d instanceof servantreloaded.cards.Duplication) {
             String[] TEXT = CardCrawlGame.languagePack.getUIString(makeID("TalkAction")).TEXT;
             atb(new TalkAction(true, TEXT[0], 1.0F, 2.0F));
           d = (new Madness()).makeCopy();
         }
           atb(new MakeTempCardInHandAction(d, false));
         d.setCostForTurn(-9);
       } 
       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
     } 
     tickDuration();
   }
 }


