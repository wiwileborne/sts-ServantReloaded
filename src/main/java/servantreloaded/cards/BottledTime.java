 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import static servantreloaded.util.Wiz.atb;
 public class BottledTime extends AbstractEasyCard {
   public static final String ID = "BottledTime";
   private static final int COST = 0;
   private static final int DRAW = 2;
   
   public BottledTime() {
     super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 2;
     this.exhaust = true;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DrawCardAction(p, this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BottledTime();
   }
 
   
@Override
   public void upp() {
       this.isInnate = true;
   }
 }


