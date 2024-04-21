 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import static servantreloaded.util.Wiz.atb;
 public class RealityMarbleAction
   extends AbstractGameAction {
   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
   public static final String[] TEXT = uiStrings.TEXT;
   
   public RealityMarbleAction(AbstractCreature source, int amount) {
     setValues(AbstractDungeon.player, source, amount);
     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
   }
 
 
   
   public void update() {
     if (this.duration == 0.5F) {
       AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
       atb((AbstractGameAction)new WaitAction(0.25F));
       tickDuration();
       
       return;
     } 
     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
       
       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
         c.retain = true;
         c.isEthereal = false;
         AbstractDungeon.player.hand.addToTop(c);
       } 
       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
     } 
     
     tickDuration();
   }
 }


