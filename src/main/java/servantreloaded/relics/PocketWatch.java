 package servantreloaded.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.Wiz.att;


 public class PocketWatch extends AbstractEasyRelic {
   private static final String ID = makeID("PocketWatch");

   public PocketWatch() {
         super(ID, RelicTier.RARE, LandingSound.MAGICAL, ServantCharacter.Enums.SILVER);
       }


   public void onExhaust(AbstractCard card) {
     flash();
     att(new RelicAboveCreatureAction(AbstractDungeon.player, this));
     atb(new DrawCardAction(AbstractDungeon.player, 1));
   }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new PocketWatch();
   }
 }


