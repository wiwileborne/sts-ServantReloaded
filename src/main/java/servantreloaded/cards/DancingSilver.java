 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ConvertAction;
 import servantreloaded.actions.DancingSilverAction;
 import static servantreloaded.util.Wiz.atb;
 public class DancingSilver extends AbstractEasyCard {
   public static final String ID = "DancingSilver";
   

   private static final int COST = 2;
   private static final int KNIVES = 4;
   
   public DancingSilver() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 4;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new ConvertAction(this.magicNumber));
     atb(new DancingSilverAction(2));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DancingSilver();
   }
   
   @Override
   public void upp() {
       upgradeMagicNumber(2);
   }

 }


