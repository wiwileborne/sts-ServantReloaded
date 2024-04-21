 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.util.LocalizationHelper;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.ServantReloadedMod.modID;
 import static servantreloaded.util.Wiz.atb;
 public class Collapse extends AbstractEasyCard {
   public static final String ID = "Collapse";
   private static final int COST = 2;
   private static final int COST_UPGRADED = 1;
   private static final CardStrings Card = LocalizationHelper.GetCard(modID + ":"+ID);
   private static final String DESCRIPTION = Card.DESCRIPTION;
   private static final String[] EXTENDED_DESCRIPTION = Card.EXTENDED_DESCRIPTION;

   public Collapse() {
     super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
     this.isMultiDamage = true;
     this.baseDamage = 0;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (p.hasPower(makeID("ProtectionPower"))
)
     { this.baseDamage = (p.getPower(makeID("ProtectionPower"))
).amount; }
     else { this.baseDamage = 0; }
      calculateCardDamage(m);
     atb(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));


     this.rawDescription = DESCRIPTION;
     initializeDescription();
   }
 
   
   public void applyPowers() {
     if (AbstractDungeon.player.hasPower(makeID("ProtectionPower"))
)
     { this.baseDamage = (AbstractDungeon.player.getPower(makeID("ProtectionPower"))
).amount; }
     else { this.baseDamage = 0; }
      super.applyPowers();
     
     this.rawDescription = DESCRIPTION;
     this.rawDescription += EXTENDED_DESCRIPTION[0];
     initializeDescription();
   }
 
   
   public void onMoveToDiscard() {
     this.rawDescription = DESCRIPTION;
     initializeDescription();
   }
 
   
   public void calculateCardDamage(AbstractMonster mo) {
     super.calculateCardDamage(mo);
     this.rawDescription = DESCRIPTION;
     this.rawDescription += EXTENDED_DESCRIPTION[0];
     initializeDescription();
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Collapse();
   }
 
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }

 }


