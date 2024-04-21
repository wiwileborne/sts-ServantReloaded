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
 import com.megacrit.cardcrawl.powers.GainStrengthPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 
 public class Sabotage extends AbstractEasyCard {
    public static final String ID = "Sabotage";

   private static final int COST = 1;
   private static final int ATTACK_DMG = 7;
   private static final int UPGRADE_PLUS_DMG = 3;
   
   public Sabotage() {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 7;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new StrengthPower(m, -this.damage), -this.damage));
     if (m != null && !m.hasPower("Artifact")) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new GainStrengthPower(m, this.damage), this.damage));
     }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Sabotage();
   }
   

   @Override
   public void upp() {
     upgradeDamage(3);
     upgradeMagicNumber(3);
   }

 }


