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
 import servantreloaded.powers.TheWorldPower;
 import static servantreloaded.util.Wiz.atb;
 public class TheWorld extends AbstractEasyCard {
    public static final String ID = "TheWorld";

   private static final int COST = 1;
   private static final int COST_UPGRADED = 0;
   private static final int DRAW = 3;
   
   public TheWorld() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 3;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DrawCardAction(p, this.magicNumber));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new TheWorldPower(p, -1), -1));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TheWorld();
   }
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }
 }


