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
 import servantreloaded.powers.RealityMarblePower;

 public class RealityMarble extends AbstractEasyCard {
    public static final String ID = "RealityMarble";

   private static final int COST = 1;
   private static final int RETAIN = 1;
   
   public RealityMarble() {
     super(ID, COST, CardType.POWER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.isInnate = true;
     this.magicNumber = this.baseMagicNumber = 1;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new RealityMarblePower(p, this.magicNumber), this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RealityMarble();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


