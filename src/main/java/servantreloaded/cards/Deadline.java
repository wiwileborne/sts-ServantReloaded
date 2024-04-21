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

 public class Deadline extends AbstractEasyCard {
   public static final String ID = "Deadline";
   private static final int COST = 1;
   private static final int AD = 2;
   
   public Deadline() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.magicNumber = this.baseMagicNumber = AD;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom(new VisionAction(p, m, this.magicNumber, 0, this));
   }
   
   public AbstractCard makeCopy() {
     return new Deadline();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }

 }


