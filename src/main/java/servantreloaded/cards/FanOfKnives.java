 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import servantreloaded.actions.ThrowKnivesAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class FanOfKnives extends AbstractEasyCard {
    public static final String ID = "FanOfKnives";
    private static final int COST = -1;
   private static final int ATTACK_DMG = 7;
   private static final int UPGRADE_PLUS_DMG = 3;
   
   public FanOfKnives() {
       super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
 
     
     this.baseDamage = 7;
     this.isMultiDamage = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (this.energyOnUse < EnergyPanel.totalCount) {
       this.energyOnUse = EnergyPanel.totalCount;
     }
     if (p.hasRelic("Chemical X")) p.getRelic("Chemical X").flash(); 
     if (AbstractDungeon.player.hasRelic("Chemical X")) this.energyOnUse += 2; 
     for (int i = 0; i < this.energyOnUse; i++) {
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ThrowKnivesAction(p, mo, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), null));
     }  if (AbstractDungeon.player.hasRelic("Chemical X")) this.energyOnUse -= 2; 
     atb(new LoseEnergyAction(this.energyOnUse));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FanOfKnives();
   }
   
   public void applyPowers() {
     this.baseDamage = 7;
     if (!canUpgrade()) this.baseDamage += 3; 
     if (AbstractDungeon.player.hasPower(makeID("BladesPower"))
)
       this.baseDamage += (AbstractDungeon.player.getPower(makeID("BladesPower"))
).amount;
     super.applyPowers();
     if (AbstractDungeon.player.hasPower(makeID("BladesPower"))
)
       this.isDamageModified = true; 
   }
   
@Override
   public void upp() {
       upgradeDamage(3);
   }

 }


