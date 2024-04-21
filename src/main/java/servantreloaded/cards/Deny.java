 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.DenyAction;
 import static servantreloaded.util.Wiz.atb;
 public class Deny extends AbstractEasyCard {
   public static final String ID = "Deny";
   private static final int COST = 1;
   private static final int DENIED = 3;

   public Deny() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);

     this.magicNumber = this.baseMagicNumber = DENIED;
     this.exhaust = true;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DenyAction(this.magicNumber, true));
   }

   public AbstractCard makeCopy() {
     return (AbstractCard)new Deny();
   }

   @Override
   public void upp() {
  this.exhaust = false;
   }
 }


