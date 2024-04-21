 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import servantreloaded.actions.GougeAction;

 public class Gouge extends AbstractEasyCard {
   public static final String ID = "Gouge";
   private static final int COST = -1;
   private static final int ATTACK_DMG = 6;
   
   public Gouge() {
       super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 6;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (this.energyOnUse < EnergyPanel.totalCount) {
       this.energyOnUse = EnergyPanel.totalCount;
     }
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GougeAction(p, m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, canUpgrade()));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Gouge();
   }
   
   @Override
   public void upp() {
   }
 }


