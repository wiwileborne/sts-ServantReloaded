 package servantreloaded.relics;

 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;


 public class KneeBrace
   extends AbstractEasyRelic
 {
   public static final String ID = makeID("KneeBrace");

   public KneeBrace() {
         super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, ServantCharacter.Enums.SILVER);
       }

       public static void Block(int amount){
           atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, amount));
       }
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new KneeBrace();
   }
 }


