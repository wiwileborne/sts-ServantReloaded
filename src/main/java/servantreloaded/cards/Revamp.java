 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.RevampAction;
 import static servantreloaded.util.Wiz.atb;
 public class Revamp extends AbstractEasyCard {
   public static final String ID = "Revamp";
   private static final int COST = 1;
   private static final int DRAW = 1;
   
   public Revamp() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 1;
     this.exhaust = true;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (AbstractDungeon.player.discardPile.size() > 0) {
       atb(new RevampAction(this.magicNumber));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Revamp();
   }
 


     @Override
     public void upp() {
         this.exhaust = false;

     }
 }


