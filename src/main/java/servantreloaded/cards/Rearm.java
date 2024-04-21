 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
 public class Rearm extends AbstractEasyCard {
    public static final String ID = "Rearm";
    private static final int COST = 1;
   private static final int KNIVES = 5;
   private static final int DRAW = 2;
   
   public Rearm() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 2;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DrawCardAction(p, this.magicNumber));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new KnivesPower(p, 5), 5));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Rearm();
   }
 
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


