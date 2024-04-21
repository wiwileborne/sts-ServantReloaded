 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.DenyAction;
 import static servantreloaded.util.Wiz.atb;
 public class Replace extends AbstractEasyCard {
   public static final String ID = "Replace";
   

   private static final int COST = 1;
   private static final int COST_UPGRADED = 0;
   
   public Replace() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DenyAction(1, false));
     atb(new DiscardPileToTopOfDeckAction(p));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Replace();
   }
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }
 }


