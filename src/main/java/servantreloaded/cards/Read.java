 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.VisionAction;

 public class Read extends AbstractEasyCard {
    public static final String ID = "Read";

   private static final int COST = 2;
   private static final int BLOCK = 15;
   private static final int UPGRADE_PLUS_BLOCK = 5;
   private static final int WEAK = 2;

   public Read() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);


     this.baseBlock = 15;
     this.magicNumber = this.baseMagicNumber = 2;
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VisionAction(p, m, this.block, this.magicNumber, (AbstractCard)this));
   }

   public AbstractCard makeCopy() {
     return (AbstractCard)new Read();
   }

@Override
   public void upp() {
       upgradeMagicNumber(1);
       upgradeBlock(5);
   }

 }


