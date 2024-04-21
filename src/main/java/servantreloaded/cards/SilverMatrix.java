 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ApplyMatrixAction;
 import servantreloaded.actions.ConvertAction;

 import static servantreloaded.util.Wiz.atb;

 public class SilverMatrix extends AbstractEasyCard {
   public static final String ID = "SilverMatrix";
   

   private static final int COST = 1;
   private static final int SATELLITE = 2;
   
   public SilverMatrix() {
     super(ID, COST, CardType.POWER,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new ConvertAction(this.magicNumber));
     atb(new ApplyMatrixAction());
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SilverMatrix();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


