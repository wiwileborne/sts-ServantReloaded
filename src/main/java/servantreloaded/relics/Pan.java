 package servantreloaded.relics;

 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;
 import servantreloaded.powers.AmplifyDamagePower;
 import servantreloaded.powers.KnivesPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.*;


 public class Pan extends AbstractEasyRelic {
   private static final String ID = makeID("Pan");

   public Pan() {
         super(ID, RelicTier.SHOP, LandingSound.SOLID, ServantCharacter.Enums.SILVER);
       }
   private static final int BLIGHT = 2;

   public void atBattleStart() {
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         applyToEnemy(mo, new AmplifyDamagePower(mo, 2));
     }
   }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Pan();
   }
 }


