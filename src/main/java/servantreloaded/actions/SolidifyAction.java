 package servantreloaded.actions;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import servantreloaded.ServantReloadedMod;
 import servantreloaded.cards.TemporalDefense;
 import servantreloaded.cards.TemporalEssence;
 import servantreloaded.cards.TemporalMisd;
 import servantreloaded.cards.TemporalSlicing;
 import servantreloaded.screens.VisionScreen;

 import static servantreloaded.util.Wiz.atb;
 import java.util.ArrayList;
 
 
 public class SolidifyAction
   extends AbstractGameAction
 {
   private ArrayList<AbstractCard> list = new ArrayList<>();
   
   public SolidifyAction(AbstractCreature p) {
     this.actionType = AbstractGameAction.ActionType.SPECIAL;
     this.duration = Settings.ACTION_DUR_FAST;
     this.source = p;
     
     AbstractCard c = (new TemporalSlicing()).makeCopy();
     this.list.add(c);
     c = (new TemporalMisd()).makeCopy();
     this.list.add(c);
     c = (new TemporalDefense()).makeCopy();
     this.list.add(c);
     c = (new TemporalEssence()).makeCopy();
     this.list.add(c);
   }
 
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
         ServantReloadedMod.vs.open(this.list, null, VisionScreen.TEXT[2]);
         tickDuration();
         return;
     } 
     if (ServantReloadedMod.vs.prediction != null) {
       AbstractCard card = ServantReloadedMod.vs.prediction.makeStatEquivalentCopy();
       atb((AbstractGameAction)new MakeTempCardInDrawPileAction(card, 1, false, false));
       this.isDone = true;
     } 
     tickDuration();
   }
 }


