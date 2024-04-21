 package servantreloaded.relics;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import servantreloaded.powers.ElegancePower;
 import servantreloaded.powers.KnivesPower;
 import com.megacrit.cardcrawl.powers.AbstractPower;
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
     if (AbstractDungeon.player.hasRelic("Uniform")) { instantObtain(AbstractDungeon.player, 0, false); }
     else { super.obtain(); }
   
   }
   
   public void atBattleStart() {
       applyToSelfTop(new KnivesPower(AbstractDungeon.player, 12));
       applyToSelf(new ElegancePower(AbstractDungeon.player, 1));
       applyToSelfTop(new SatellitePower(AbstractDungeon.player, 3));
   }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new SplendidAttire();
   }
 }


