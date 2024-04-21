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
 import servantreloaded.powers.FloweringNightPower;

 public class FloweringNight extends AbstractEasyCard {
    public static final String ID = "FloweringNight";
    private static final int COST = 2;
   private static final int COST_UPGRADED = 1;
   private static final int LIMIT = 6;
   
   public FloweringNight() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 6;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new FloweringNightPower(p, 1), 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FloweringNight();
   }
 
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }
 }


