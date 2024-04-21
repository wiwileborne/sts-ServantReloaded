 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ReduceDebuffsAction;
 import static servantreloaded.util.Wiz.atb;
 public class Reset extends AbstractEasyCard {
    public static final String ID = "Reset";

   private static final int COST = 0;
   private static final int PROTECTION = 1;
   
   public Reset() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
 
     
     this.exhaust = true;
     this.magicNumber = this.baseMagicNumber = 1;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new ReduceDebuffsAction(p, this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Reset();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


