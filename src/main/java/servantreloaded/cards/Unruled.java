 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.util.LocalizationHelper;

 import static servantreloaded.ServantReloadedMod.modID;

 public class Unruled extends AbstractEasyCard {
   public static final String ID = "Unruled";
     private static final CardStrings Card = LocalizationHelper.GetCard(modID + ":"+ID);
     private static final String DESCRIPTION = Card.DESCRIPTION;

   private static final int COST = 1;
   private static final int ATTACK_DMG = 9;
   private static final int UPGRADE_PLUS_DMG = 2;
   private boolean debuffed;
   
   public Unruled() {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 9;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (p.hasPower("Weakened") || p.hasPower("Vulnerable") || p.hasPower("Weakened")) { this.debuffed = true; }
     else { this.debuffed = false; }
      if (this.debuffed) { AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY)); }
     else
     { AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)); }
   
   }
 
   
   public void applyPowers() {
     this.baseDamage = 9;
     if (AbstractDungeon.player.hasPower("Weakened")) this.baseDamage += (AbstractDungeon.player.getPower("Weakened")).amount * this.magicNumber; 
     if (AbstractDungeon.player.hasPower("Vulnerable")) this.baseDamage += (AbstractDungeon.player.getPower("Vulnerable")).amount * this.magicNumber; 
     if (AbstractDungeon.player.hasPower("Frail")) this.baseDamage += (AbstractDungeon.player.getPower("Frail")).amount * this.magicNumber; 
     super.applyPowers();
     this.rawDescription = DESCRIPTION;
     initializeDescription();
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Unruled();
   }
   

   @Override
   public void upp() {
     upgradeDamage(2);
     upgradeMagicNumber(1);
   }
 }


