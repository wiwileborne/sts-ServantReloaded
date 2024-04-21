 package servantreloaded.relics;


 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;
 import servantreloaded.actions.OldScarfAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.att;


 public class OldScarf
   extends AbstractEasyRelic {
   private static final String ID = makeID("OldScarf");

   public OldScarf() {
         super(ID, RelicTier.RARE, LandingSound.FLAT, ServantCharacter.Enums.SILVER);
       }

   public void atBattleStart() {
     att(new OldScarfAction());
   }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new OldScarf();
   }
 }


