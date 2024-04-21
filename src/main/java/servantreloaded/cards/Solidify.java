 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.SolidifyAction;
 import static servantreloaded.util.Wiz.atb;
 public class Solidify extends AbstractEasyCard {
    public static final String ID = "Solidify";
    private static final int COST = 0;
   
   public Solidify() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
 
     
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new SolidifyAction(p));
     if (!canUpgrade()) atb(new SolidifyAction(p)); 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Solidify();
   }
   
   @Override
   public void upp() {
   }
 }


