 package servantreloaded.relics;


 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import servantreloaded.powers.ElegancePower;
 import servantreloaded.powers.KnivesPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;
 import servantreloaded.powers.SatellitePower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.*;


 public class SplendidAttire extends AbstractEasyRelic {
   private static final String ID = makeID("SplendidAttire");
   
   public SplendidAttire() {
     super(ID, RelicTier.BOSS, LandingSound.MAGICAL, ServantCharacter.Enums.SILVER);
   }
   private static final int KNIVES = 12;


     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Uniform.ID)) {
             for (int i=0; i<AbstractDungeon.player.relics.size(); ++i) {
                 if (AbstractDungeon.player.relics.get(i).relicId.equals(Uniform.ID)) {
                     instantObtain(AbstractDungeon.player, i, true);
                     break;
                 }
             }
         } else {
             super.obtain();
         }
     }

   public void atBattleStart() {
       applyToSelfTop(new KnivesPower(AbstractDungeon.player, KNIVES));
       applyToSelf(new ElegancePower(AbstractDungeon.player, 1));
       applyToSelfTop(new SatellitePower(AbstractDungeon.player, 3));
   }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new SplendidAttire();
   }
 }


