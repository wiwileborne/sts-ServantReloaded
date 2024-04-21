 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import static servantreloaded.util.Wiz.atb;
 public class FastForward extends AbstractEasyCard {
    public static final String ID = "FastForward";

   private static final int COST = 1;
   private static final int BLOCK = 9;
   private static final int UPGRADE_PLUS_BLOCK = 3;
   private static final int DRAW = 2;
   private boolean ATTACK = false;
   
   public FastForward() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
 
     
     this.baseBlock = 9;
     this.magicNumber = this.baseMagicNumber = 2;
     this.ATTACK = false;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new GainBlockAction(p, p, this.block));
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       if (c.type == AbstractCard.CardType.ATTACK)
         this.ATTACK = true; 
     } 
     if (!this.ATTACK)
       atb(new DrawCardAction(p, this.magicNumber)); 
     this.ATTACK = false;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FastForward();
   }
 
   
@Override
   public void upp() {
     upgradeBlock(3);
   }
 }


