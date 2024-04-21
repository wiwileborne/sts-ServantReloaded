 package servantreloaded.cards;


 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
 import servantreloaded.actions.DoubleProtectionAction;
 import servantreloaded.powers.ProtectionPower;
 import servantreloaded.relics.KneeBrace;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class DualDimension extends AbstractEasyCard {
    public static final String ID = "DualDimension";

   private static final int COST = 2;
   private static final int COST_UPGRADED = 1;
   private static final int PROTECTION = 6;
   
   public DualDimension() {
       super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 6;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalAuraEffect(new Color(0.0F, 1.0F, 1.0F, 0.0F), p.hb.cX, p.hb.cY)));
     atb(new DoubleProtectionAction(p));
   }
   
   public void triggerOnManualDiscard() {
     applyPowers();
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new ProtectionPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));

       if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
           KneeBrace.Block(3);
       }
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 6;
     if (AbstractDungeon.player.hasPower(makeID("ElegancePower"))
) {
       upgradeMagicNumber((AbstractDungeon.player.getPower(makeID("ElegancePower"))
).amount);
       this.isMagicNumberModified = true;
     } 
     super.applyPowers();
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DualDimension();
   }
   
@Override
   public void upp() {
  upgradeBaseCost(0);
   }
 }


