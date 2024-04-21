 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
 import servantreloaded.actions.BacklashAction;
 import static servantreloaded.util.Wiz.atb;
 public class Spin extends AbstractEasyCard {
    public static final String ID = "Spin";

   private static final int COST = 1;
   private static final int ATTACK_DMG = 13;
   private static final int UPGRADE_PLUS_DMG = 5;
   private static final int WEAK = 1;
   
   public Spin() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ALL_ENEMY);
 
     
     this.baseDamage = 13;
     this.magicNumber = this.baseMagicNumber = 1;
     this.isMultiDamage = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new SFXAction("ATTACK_HEAVY"));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction(p, (AbstractGameEffect)new CleaveEffect(), 0.0F));
     atb(new DamageAllEnemiesAction(p, this.multiDamage, this.damageType, AbstractGameAction.AttackEffect.NONE, true));
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(mo, p, (AbstractPower)new WeakPower(mo, this.magicNumber, false), this.magicNumber)); 
     atb(new BacklashAction(1));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Spin();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
       upgradeDamage(5);
   }

 }


