 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;

 import static servantreloaded.util.Wiz.atb;

 public class TemporalEssence extends AbstractEasyCard {
    public static final String ID = "TemporalEssence";
    private static final int COST = 0;
   private static final int DRAW = 2;
   
   public TemporalEssence() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
     
     this.isEthereal = true;
     this.exhaust = true;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new GainEnergyAction(1));
     atb(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
     if (!canUpgrade()) atb(new GainEnergyAction(1)); 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TemporalEssence();
   }
   

     @Override
     public void upp() {
         upgradeMagicNumber(1);

     }
 }


