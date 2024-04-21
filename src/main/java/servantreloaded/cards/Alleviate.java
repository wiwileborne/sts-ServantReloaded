 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.AlleviateAction;
 import static servantreloaded.util.Wiz.atb;
 public class Alleviate extends AbstractEasyCard {
   public static final String ID = "Alleviate";
   

   private static final int COST = 2;
   private static final int BLOCK = 5;
   private static final int UPGRADE_PLUS_BLOCK = 2;
   
   public Alleviate() {
     super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 5;
     this.baseBlock = 5;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new AlleviateAction(p, 3, this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Alleviate();
   }


   @Override
   public void upp() {
     upgradeBlock(2);
   }
 }


