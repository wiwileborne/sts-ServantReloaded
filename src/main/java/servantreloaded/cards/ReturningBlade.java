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

 public class ReturningBlade extends AbstractEasyCard {
    public static final String ID = "ReturningBlade";

   
   private static final int COST = 1;
   private static final int ATTACK_DMG = 6;
   
   public ReturningBlade() {
     this(0);
   }
   
   public ReturningBlade(int upgrades) {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 6;
     this.timesUpgraded = upgrades;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VisionAction(p, m, this.baseDamage, 0, (AbstractCard)this));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ReturningBlade(this.timesUpgraded);
   }
 

     @Override
     public void upp() {
         upgradeDamage(2 + this.timesUpgraded);

     }
   
   public boolean canUpgrade() {
     return true;
   }
 }


