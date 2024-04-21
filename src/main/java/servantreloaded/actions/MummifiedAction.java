 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardQueueItem;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.MummifiedHand;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import servantreloaded.powers.TheWorldPower;

 import java.util.ArrayList;
 import java.util.Iterator;
 
 
 public class MummifiedAction
   extends AbstractGameAction
 {
   private static final Logger logger = LogManager.getLogger(MummifiedHand.class.getName());
   
   private AbstractCard card;
   private TheWorldPower source;
   
   public MummifiedAction(AbstractCard card, TheWorldPower source) {
     this.duration = Settings.ACTION_DUR_XFAST;
     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
     this.card = card;
     this.source = source;
   }
 
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_XFAST && 
       this.card.type == AbstractCard.CardType.POWER) {
       ArrayList<AbstractCard> groupCopy = new ArrayList<>();
       for (AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
         if (abstractCard.cost > 0 && abstractCard.costForTurn > 0) {
           groupCopy.add(abstractCard); continue;
         } 
         logger.info("COST IS 0: " + abstractCard.name);
       } 
 
       
       for (Iterator<CardQueueItem> it = AbstractDungeon.actionManager.cardQueue.iterator(); it.hasNext(); ) {
         CardQueueItem i = it.next();
         if (i.card != null) {
           logger.info("INVALID: " + i.card.name);
           groupCopy.remove(i.card);
         } 
       } 
       AbstractCard c = null;
       if (!groupCopy.isEmpty()) {
         logger.info("VALID CARDS: ");
         for (AbstractCard cc : groupCopy) {
           logger.info(cc.name);
         }
         c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
       } else {
         logger.info("NO VALID CARDS");
       } 
       if (c != null) {
         logger.info("Mummified hand: " + c.name);
         c.setCostForTurn(0);
         this.source.zero_cost.add(c);
       } else {
         logger.info("ERROR: MUMMIFIED HAND NOT WORKING");
       } 
     } 
     
     tickDuration();
   }
 }


