 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.FalseFlawlessFormPower;

 public class Defy extends AbstractEasyCard {
   public static final String ID = "Defy";
   private static final int COST = 1;
   private static final int BLOCK_AMT = 8;
   private static final int UPGRADE_PLUS_BLOCK = 2;
   
   public Defy() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.baseBlock = BLOCK_AMT;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, (AbstractPower)new FalseFlawlessFormPower(p, 1), 1));
   }
   
   public void triggerOnEndOfTurnForPlayingCard() {
     if (!canUpgrade())
       this.retain = true; 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Defy();
   }
   
   @Override
   public void upp() {
       upgradeBlock(UPGRADE_PLUS_BLOCK);
  this.retain = true;
   }

 }


