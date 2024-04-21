 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.TrueSightPower;

 public class TrueSight extends AbstractEasyCard {
    public static final String ID = "TrueSight";
   private static final int COST = 2;
   
   public TrueSight() {
     super(ID, COST, CardType.POWER,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new TrueSightPower(p, -1), -1));
   }
   
   public void triggerOnEndOfTurnForPlayingCard() {
     if (!canUpgrade())
       this.retain = true; 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TrueSight();
   }


     @Override
     public void upp() {
         this.retain = true;

     }
 }


