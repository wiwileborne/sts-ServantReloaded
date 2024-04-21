 package servantreloaded.actions;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

 
 public class OldScarfAction
   extends AbstractGameAction
 {
   private AbstractPlayer p = AbstractDungeon.player;
   
   private float startingDuration = Settings.ACTION_DUR_FAST;
 
 
   
   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST)
       for (AbstractCard c : this.p.drawPile.group) {
         if (c.canUpgrade() && isVision(c)) {
           c.upgrade();
           c.superFlash();
         } 
       }  
     tickDuration();
   }
   
   public boolean isVision(AbstractCard c) {
     if (c instanceof servantreloaded.cards.Read) return true;
     if (c instanceof servantreloaded.cards.Snipe) return true;
     if (c instanceof servantreloaded.cards.TimeTheft) return true;
     if (c instanceof servantreloaded.cards.Deadline) return true;
     if (c instanceof servantreloaded.cards.ReturningBlade) return true;
     return false;
   }
 }


