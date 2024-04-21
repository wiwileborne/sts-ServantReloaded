 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import servantreloaded.actions.ManipulateAction;

 public class Manipulate extends AbstractEasyCard {
   public static final String ID = "Manipulate";
   private static final int COST = -1;
   
   public Manipulate() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
   }
 
 
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (this.energyOnUse < EnergyPanel.totalCount) {
       this.energyOnUse = EnergyPanel.totalCount;
     }
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ManipulateAction(p, this.freeToPlayOnce, this.energyOnUse, canUpgrade()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Manipulate();
   }
 
   
   @Override
   public void upp() {
   }
 }


