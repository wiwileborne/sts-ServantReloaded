 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 import servantreloaded.actions.BounceAction;
 import servantreloaded.powers.KnivesPower;
 import servantreloaded.vfx.ServantDaggerEffect;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;

 public class Ricochet extends AbstractEasyCard {
    public static final String ID = "Ricochet";

   private static final int COST = 1;
   private static final int ATTACK_DMG = 9;
   private static final int BOUNCE = 2;
   
   public Ricochet() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 9;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (p.hasPower(KnivesPower.ID)
 &&
       (p.getPower(KnivesPower.ID)
).amount > 0) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new ServantDaggerEffect(m.hb.cX, m.hb.cY)));
       atb(new BounceAction(p, m, this.baseDamage, this.magicNumber + 1));
       if (p.hasPower(makeID("SurpressingFirePower"))
) {
         AbstractDungeon.effectList.add(new FlashAtkImgEffect(p.hb.cX, p.hb.cY, AbstractGameAction.AttackEffect.SHIELD));
         p.addBlock((p.getPower(makeID("SurpressingFirePower"))
).amount);
       } 
       atb(new ReducePowerAction(p, p, KnivesPower.ID, 1));
     } 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Ricochet();
   }
   
   public void applyPowers() {
     this.baseDamage = 9;
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
   }
 }


