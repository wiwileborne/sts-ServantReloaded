 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.AdvanceAction;

 import static servantreloaded.util.Wiz.atb;

 public class Advance extends AbstractEasyCard {
   public static final String ID = "Advance";
   private static final int COST = 0;
   private static final int ZERO = 2;
   
   public Advance() {
     super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = ZERO;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new AdvanceAction(this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Advance();
   }
 


   @Override
   public void upp() {
     this.isInnate = true;
   }
 }


