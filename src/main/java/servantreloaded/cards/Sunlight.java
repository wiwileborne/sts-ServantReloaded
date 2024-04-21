 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
 import servantreloaded.powers.AmplifyDamagePower;
 import servantreloaded.powers.KnivesPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;

 public class Sunlight extends AbstractEasyCard {
    public static final String ID = "Sunlight";

   private static final int COST = 2;
   private static final int ATTACK_DMG = 16;
   private static final int UPGRADE_PLUS_DMG = 5;
   private static final int BLIGHT = 1;
   
   public Sunlight() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
     
     this.isMultiDamage = true;
     this.baseDamage = 16;
     this.magicNumber = this.baseMagicNumber = 1;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (p.hasPower(KnivesPower.ID)
 &&
       (p.getPower(KnivesPower.ID)
).amount >= 1) {
       atb(new SFXAction("ATTACK_HEAVY"));
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction(p, (AbstractGameEffect)new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
       atb(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
       atb(new ReducePowerAction(p, p, KnivesPower.ID, 1));
       if (p.hasPower(makeID("SurpressingFirePower"))
) {
         AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
         p.addBlock((p.getPower(makeID("SurpressingFirePower"))
).amount);
       } 
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(mo, AbstractDungeon.player, (AbstractPower)new AmplifyDamagePower(mo, this.magicNumber), this.magicNumber));
       }
     } 
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Sunlight();
   }
   
   public void applyPowers() {
     if (canUpgrade()) { this.baseDamage = 16; }
     else { this.baseDamage = 21; }
      if (AbstractDungeon.player.hasPower(makeID("BladesPower"))
)
       this.baseDamage += (AbstractDungeon.player.getPower(makeID("BladesPower"))
).amount;
     super.applyPowers();
     if (AbstractDungeon.player.hasPower(makeID("BladesPower"))
)
       this.isDamageModified = true; 
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
       upgradeDamage(5);
   }

 }


