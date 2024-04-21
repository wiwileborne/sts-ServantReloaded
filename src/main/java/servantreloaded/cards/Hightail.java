 package servantreloaded.cards;


 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import servantreloaded.actions.HightailAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class Hightail extends AbstractEasyCard {
   public static final String ID = "Hightail";
   private static final int COST = -1;
   private static final int PROTECTION = 8;
   
   public Hightail() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 8;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (this.energyOnUse < EnergyPanel.totalCount) {
       this.energyOnUse = EnergyPanel.totalCount;
     }
     atb(new HightailAction(p, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Hightail();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 8;
     if (!canUpgrade()) upgradeMagicNumber(3); 
     if (AbstractDungeon.player.hasPower(makeID("ElegancePower"))
) {
       upgradeMagicNumber((AbstractDungeon.player.getPower(makeID("ElegancePower"))
).amount);
       this.isMagicNumberModified = true;
     } 
     super.applyPowers();
   }
   
   @Override
   public void upp() {
       upgradeMagicNumber(3);
   }

 }


