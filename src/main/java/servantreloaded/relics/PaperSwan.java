 package servantreloaded.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;

 import static servantreloaded.ServantReloadedMod.makeID;


 public class PaperSwan
   extends AbstractEasyRelic
 {
   private static final String ID = makeID("PaperSwan");

   public PaperSwan() {
          super(ID, RelicTier.BOSS, LandingSound.FLAT, ServantCharacter.Enums.SILVER);
       }
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new PaperSwan();
   }
 }


