 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ShuttleAction;
 import static servantreloaded.util.Wiz.atb;
 public class Shuttle extends AbstractEasyCard {
   public static final String ID = "Shuttle";
   

   private static final int COST = 1;
   private static final int COST_UPGRADED = 0;
   
   public Shuttle() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new ShuttleAction());
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Shuttle();
   }
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }
 }


