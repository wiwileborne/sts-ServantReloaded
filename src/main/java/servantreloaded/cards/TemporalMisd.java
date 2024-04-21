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
 import com.megacrit.cardcrawl.powers.WeakPower;
 import servantreloaded.powers.AmplifyDamagePower;

 public class TemporalMisd extends AbstractEasyCard {
    public static final String ID = "TemporalMisd";

   private static final int COST = 0;
   private static final int MISD = 1;
   
   public TemporalMisd() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
     
     this.isEthereal = true;
     this.exhaust = true;
     this.magicNumber = this.baseMagicNumber = 1;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(mo, AbstractDungeon.player, (AbstractPower)new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
       
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(mo, AbstractDungeon.player, (AbstractPower)new WeakPower(mo, this.magicNumber, false), this.magicNumber));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TemporalMisd();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


