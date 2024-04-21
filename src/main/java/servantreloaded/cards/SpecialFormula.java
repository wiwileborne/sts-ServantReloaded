 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.BacklashAction;
 import static servantreloaded.util.Wiz.atb;
 public class SpecialFormula extends AbstractEasyCard {
    public static final String ID = "SpecialFormula";

   private static final int COST = 0;
   private static final int HEAL = 5;
   
   public SpecialFormula() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 5;
     this.exhaust = true;
     this.tags.add(AbstractCard.CardTags.HEALING);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new GainEnergyAction(1));
     atb(new HealAction(p, p, this.magicNumber));
     atb(new BacklashAction(1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SpecialFormula();
   }
 
   
   @Override
   public void upp() {
       upgradeMagicNumber(3);
   }
 }


