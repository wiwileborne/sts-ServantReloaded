 package servantreloaded.cards;


 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ConvertAction;
 import servantreloaded.actions.OrbitDamageAction;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class Orbit extends AbstractEasyCard {
   public static final String ID = "Orbit";
   

   private static final int COST = 2;
   private static final int ATTACK_DMG = 3;
   private static final int SATELLITE = 3;
   
   public Orbit() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
 
     
     this.baseDamage = 3;
     this.magicNumber = this.baseMagicNumber = 3;
     this.isMultiDamage = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new ConvertAction(this.magicNumber));
     atb(new OrbitDamageAction(this.multiDamage));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Orbit();
   }
   
   public void applyPowers() {
     this.baseDamage = 3;
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


