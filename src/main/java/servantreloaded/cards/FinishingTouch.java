 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import servantreloaded.powers.ElegancePower;
 import servantreloaded.vfx.FinishingImpactEffect;

 public class FinishingTouch extends AbstractEasyCard {
    public static final String ID = "FinishingTouch";

   private static final int COST = 2;
   private static final int ATTACK_DMG = 30;
   private static final int UPGRADE_PLUS_DMG = 12;
   
   public FinishingTouch() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 30;
     this.exhaust = true;
     this.isEthereal = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (m != null) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new FinishingImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
     }
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ElegancePower(p, 1), 1));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FinishingTouch();
   }
   
@Override
   public void upp() {
     upgradeDamage(12);
   }
 }


