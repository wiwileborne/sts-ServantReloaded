 package servantreloaded.relics;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;
 import servantreloaded.cards.TemporalEssence;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;


 public class RomanBracelet
   extends AbstractEasyRelic {
   private static final String ID = makeID("RomanBracelet");
   
   public RomanBracelet() {
         super(ID, RelicTier.RARE, LandingSound.MAGICAL, ServantCharacter.Enums.SILVER);
       }
 
   
   public void atBattleStart() {
     AbstractCard c = (new TemporalEssence()).makeCopy();
     atb(new MakeTempCardInDrawPileAction(c, 2, true, false));
   }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new RomanBracelet();
   }
 }


