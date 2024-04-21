 package servantreloaded.cards;


 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
 import com.megacrit.cardcrawl.actions.utility.UpdateCardDescriptionAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
 import servantreloaded.relics.KneeBrace;

 import static servantreloaded.util.Wiz.atb;

 public class Potential extends AbstractEasyCard {
    public static final String ID = "Potential";

   private static final int COST = 0;
   private static final int ATTACK_DMG = 5;
   private static final int UPGRADE_PLUS_DMG = 2;
   private static final int ATTACK_DMG_GROW = 5;
   
   public Potential() {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 5;
     this.magicNumber = this.baseMagicNumber = 5;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (this.damage >= 0 && this.damage < 14) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
     }
     else if (this.damage >= 14 && this.damage < 28) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
     }
     else if (this.damage >= 28 && this.damage < 42) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     } else {
       
       if (m != null) {
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.0F, 1.0F, 1.0F, 1.0F))));
       }
       atb(new WaitAction(0.8F));
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     } 
   }
 
   
   public void triggerOnManualDiscard() {
     this.baseDamage += this.magicNumber;
     superFlash();
     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new DiscardToHandAction((AbstractCard)this));
     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(0.3F));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new UpdateCardDescriptionAction((AbstractCard)this));
       if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
           KneeBrace.Block(3);
       }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Potential();
   }
   
@Override
     public void upp() {
         upgradeDamage(2);
         upgradeMagicNumber(2);

     }
 }


