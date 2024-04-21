 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.RewindAction;
 import static servantreloaded.util.Wiz.atb;
 public class Rewind extends AbstractEasyCard {
    public static final String ID = "Rewind";

   private static final int COST = 1;
   private static final int BLOCK_AMT = 7;
   private static final int UPGRADE_PLUS_BLOCK = 3;
   
   public Rewind() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.baseBlock = 7;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new RewindAction(1));
     atb(new GainBlockAction(p, p, this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Rewind();
   }
 
   

   @Override
   public void upp() {
     upgradeBlock(3);
   }

 }


