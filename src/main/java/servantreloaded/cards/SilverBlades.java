 package servantreloaded.cards;


 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.powers.BladesPower;

 import static servantreloaded.util.Wiz.applyToSelf;
 public class SilverBlades extends AbstractEasyCard {
    public static final String ID = "SilverBlades";

   private static final int COST = 1;
   private static final int DAMAGE_UPGRADE = 2;
   
   public SilverBlades() {
     super(ID, COST, CardType.POWER,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     applyToSelf(new BladesPower(p, this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new SilverBlades();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


