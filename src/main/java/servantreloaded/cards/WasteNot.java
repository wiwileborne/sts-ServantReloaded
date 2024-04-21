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
 import servantreloaded.powers.WasteNotPower;

 public class WasteNot extends AbstractEasyCard {
    public static final String ID = "WasteNot";

   private static final int COST = 1;
   private static final int COST_UPGRADED = 0;
   
   public WasteNot() {
     super(ID, COST, CardType.POWER,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new WasteNotPower(p, 1), 1));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WasteNot();
   }
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }
 }


