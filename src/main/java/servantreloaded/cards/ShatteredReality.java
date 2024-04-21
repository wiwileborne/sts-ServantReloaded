 package servantreloaded.cards;


 import com.badlogic.gdx.math.MathUtils;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
 import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
 import servantreloaded.relics.KneeBrace;

 import static servantreloaded.util.Wiz.atb;
 public class ShatteredReality extends AbstractEasyCard {
    public static final String ID = "ShatteredReality";

   private static final int COST = 0;
   private static final int ATTACK_DMG = 5;
   private static final int UPGRADE_PLUS_DMG = 3;
   
   public ShatteredReality() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 5;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
   }
 
   
   public void triggerOnManualDiscard() {
     atb(new SFXAction("THUNDERCLAP", 0.05F));
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
       if (!mo.isDeadOrEscaped()) {
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(mo.drawX, mo.drawY), 0.05F));
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(mo, new DamageInfo(AbstractDungeon.player, 
                 damageCalculation(AbstractDungeon.player, mo, this.baseDamage) * 2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
       } 
     }


       if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
           KneeBrace.Block(3);
       }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShatteredReality();
   }
   
@Override
   public void upp() {
       upgradeDamage(3);
   }
   
   public int damageCalculation(AbstractPlayer player, AbstractMonster monster, int damage) {
     float tmp = damage;
     for (AbstractPower p : player.powers) tmp = p.atDamageGive(tmp, DamageInfo.DamageType.NORMAL); 
     for (AbstractPower p : monster.powers) tmp = p.atDamageReceive(tmp, DamageInfo.DamageType.NORMAL); 
     for (AbstractPower p : player.powers) tmp = p.atDamageFinalGive(tmp, DamageInfo.DamageType.NORMAL); 
     for (AbstractPower p : monster.powers) tmp = p.atDamageFinalReceive(tmp, DamageInfo.DamageType.NORMAL); 
     int output = MathUtils.floor(tmp);
     if (output < 0) output = 0; 
     return output;
   }
 }


