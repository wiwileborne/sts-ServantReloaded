 package servantreloaded.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;

 public class Broom extends AbstractEasyRelic {
   private static final String ID = makeID("Broom");
   private static final int COUNT = 10;

   public Broom() {
         super(ID, RelicTier.COMMON, LandingSound.FLAT, ServantCharacter.Enums.SILVER);
           this.counter = 0;
   }


   public void onManualDiscard() {
     this.counter++;
     if (this.counter >= COUNT) {
       flash();
       atb(new RelicAboveCreatureAction(AbstractDungeon.player, this));
       this.counter -= COUNT;
       atb(new GainEnergyAction(1));
     }
       if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
           KneeBrace.Block(3);
       }
   }


   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Broom();
   }
 }


