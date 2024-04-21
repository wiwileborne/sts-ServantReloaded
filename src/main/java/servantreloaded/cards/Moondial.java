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
 import servantreloaded.actions.MoondialAction;
 import servantreloaded.powers.ManipulatePower;
 import static servantreloaded.util.Wiz.atb;
 public class Moondial extends AbstractEasyCard {
    public static final String ID = "Moondial";

   private static final int COST = 2;
   private static final int TEMPORAL = 2;
   
   public Moondial() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.exhaust = true;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new MoondialAction(this.magicNumber));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ManipulatePower(p, this.magicNumber), this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Moondial();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


