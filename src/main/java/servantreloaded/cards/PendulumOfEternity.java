 package servantreloaded.cards;


 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
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
 import com.megacrit.cardcrawl.powers.WeakPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
 import servantreloaded.relics.KneeBrace;

 import static servantreloaded.util.Wiz.atb;
 public class PendulumOfEternity extends AbstractEasyCard {
    public static final String ID = "PendulumOfEternity";

   private static final int COST = 5;
   private static final int COST_REDUCTION_WHEN_UPGRADED = 1;
   private static final int DEBUFFS = 3;
   private static final int ATTACK_DMG = 33;
   
   public PendulumOfEternity() {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
 
     
     this.magicNumber = this.baseMagicNumber = 3;
     this.baseDamage = 33;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     atb(new WaitAction(0.1F));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new WeakPower(m, this.magicNumber, false), this.magicNumber));
   }
   @Override
   public void triggerOnManualDiscard() {
     superFlash();
     if (this.cost > 0) {this.cost--;
     this.isCostModified = true;}
       if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
           KneeBrace.Block(3);
       }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PendulumOfEternity();
   }
   

     @Override
     public void upp() {
         int newCost = this.cost;
         newCost--;
         if (newCost < 0) newCost = 0;
         upgradeBaseCost(newCost);
     }
 }


