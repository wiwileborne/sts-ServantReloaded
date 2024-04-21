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
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import servantreloaded.actions.BacklashAction;
 import static servantreloaded.util.Wiz.atb;
 public class DoubleEdge extends AbstractEasyCard {
   public static final String ID = "DoubleEdge";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 15;
   private static final int UPGRADE_PLUS_DMG = 6;
   private static final int VULNERABLE = 2;
   
   public DoubleEdge() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.magicNumber = this.baseMagicNumber = 2;
     this.baseDamage = 15;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
     atb(new BacklashAction(1));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DoubleEdge();
   }
   
   @Override
   public void upp() {
  upgradeDamage(6);
       upgradeMagicNumber(1);
   }
 }


