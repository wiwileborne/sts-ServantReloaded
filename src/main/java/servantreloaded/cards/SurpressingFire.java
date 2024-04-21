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
 import servantreloaded.powers.SurpressingFirePower;

 public class SurpressingFire extends AbstractEasyCard {
    public static final String ID = "SurpressingFire";

   
   private static final int COST = 1;
   private static final int BLOCK = 2;
   
   public SurpressingFire() {
     super(ID, COST, CardType.POWER,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new SurpressingFirePower(p, this.magicNumber), this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SurpressingFire();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


