 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.VisionAction;

 public class TimeTheft extends AbstractEasyCard {
    public static final String ID = "TimeTheft";
   private static final int COST = 1;
   private static final int ENERGY = 2;
   
   public TimeTheft() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.magicNumber = this.baseMagicNumber = 2;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VisionAction(p, m, this.magicNumber, 0, (AbstractCard)this));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TimeTheft();
   }
   

     @Override
     public void upp() {
         upgradeMagicNumber(1);

     }
 }


