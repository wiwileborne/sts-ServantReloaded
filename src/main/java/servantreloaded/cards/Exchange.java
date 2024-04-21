 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DiscardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.KnivesPower;
 import static servantreloaded.util.Wiz.atb;
 public class Exchange extends AbstractEasyCard {
    public static final String ID = "Exchange";
    private static final int COST = 0;
   private static final int DISCARD = 2;
   
   public Exchange() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 2;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DiscardAction(p, p, this.magicNumber, false));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new KnivesPower(p, this.magicNumber), this.magicNumber));
   }
   
   public void triggerOnEndOfTurnForPlayingCard() {
     if (!canUpgrade()) {
       this.retain = true;
     }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Exchange();
   }
 
   
@Override
   public void upp() {
  this.retain = true;
   }
 }


