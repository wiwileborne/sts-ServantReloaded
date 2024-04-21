 package servantreloaded.cards;


 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ThrowKnivesAction;
 import servantreloaded.powers.BladesPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;

 public class KidneyShot extends AbstractEasyCard {
    public static final String ID = "KidneyShot";

   private static final int COST = 1;
   private static final int ATTACK_DMG = 4;
   private static final int THROW = 2;
   
   public KidneyShot() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 4;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     for (int i = 0; i < this.magicNumber; i++)
       atb(new ThrowKnivesAction(p, m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), "Weakened"));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new KidneyShot();
   }
   
   public void applyPowers() {
     this.baseDamage = 4;
     if (AbstractDungeon.player.hasPower(makeID(BladesPower.ID)))
       this.baseDamage += (AbstractDungeon.player.getPower(makeID(BladesPower.ID))).amount;
     super.applyPowers();
     if (AbstractDungeon.player.hasPower(makeID(BladesPower.ID)))
       this.isDamageModified = true; 
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


