 package servantreloaded.relics;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import servantreloaded.ServantCharacter;
 import servantreloaded.actions.BacklashAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.util.Wiz.att;


 public class MysterySword extends AbstractEasyRelic {
   private static final String ID = makeID("MysterySword");
   private boolean activated = true;
   
   public MysterySword() {
         super(ID, RelicTier.BOSS, LandingSound.CLINK, ServantCharacter.Enums.SILVER);
       }
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card.type == AbstractCard.CardType.SKILL && this.activated) {
       this.activated = false;
       flash();
       att(new RelicAboveCreatureAction(AbstractDungeon.player, this));
       stopPulse();
       atb(new BacklashAction(1));
     } 
   }
   
   public void onEquip() {
     AbstractDungeon.player.energy.energyMaster++;
   }
 
   
   public void onUnequip() {
     AbstractDungeon.player.energy.energyMaster--;
   }
 
   
   public void atTurnStart() {
     beginPulse();
     this.pulse = true;
     this.activated = true;
   }
 
   
   public boolean checkTrigger() {
     return this.activated;
   }
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new MysterySword();
   }
 }


