 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 
 public class SoulSculpture extends AbstractEasyCard {
    public static final String ID = "SoulSculpture";

   private static final int COST = 2;
   private static final int ATTACK_DMG = 3;
   private static final int UPGRADE_PLUS_DMG = 1;
   private static final int STRENGTH_LOSS = 3;
   
   public SoulSculpture() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 3;
     this.magicNumber = this.baseMagicNumber = 3;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new StrengthPower(m, -this.magicNumber), -this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SoulSculpture();
   }


     @Override
     public void upp() {
         upgradeDamage(1);
         upgradeMagicNumber(1);

     }
 }


