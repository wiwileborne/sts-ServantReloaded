 package servantreloaded.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.att;


 public class StoneMask extends AbstractEasyRelic {
   private static final String ID = makeID("StoneMask");
   private static final int MAX_HP_AMT = 1;
   private static final int HEAL_AMT = 2;
   
   public StoneMask() {super(ID, RelicTier.BOSS, LandingSound.MAGICAL, ServantCharacter.Enums.SILVER);}

   
   public void onMonsterDeath(AbstractMonster m) {
     if (!m.halfDead && !m.hasPower("Minion")) {
       AbstractDungeon.player.heal(2);
       AbstractDungeon.player.increaseMaxHp(1, false);
       flash();
       att(new RelicAboveCreatureAction(AbstractDungeon.player, this));
     } 
   }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new StoneMask();
   }
 }

