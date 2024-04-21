 package servantreloaded.cards;


 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.DisposalAction;
 import static servantreloaded.util.Wiz.atb;
 import static servantreloaded.ServantReloadedMod.makeID;

 public class ForgedInTime extends AbstractEasyCard {
   public static final String ID = "ForgedInTime";
   

   private static final int COST = 2;
   private static final int PROTECTION = 5;
   
   public ForgedInTime() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
 
     
     this.exhaust = true;
     this.magicNumber = this.baseMagicNumber = 5;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DisposalAction(p, this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ForgedInTime();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 5;
     if (!canUpgrade()) upgradeMagicNumber(2); 
     if (AbstractDungeon.player.hasPower(makeID("ElegancePower"))
) {
       upgradeMagicNumber((AbstractDungeon.player.getPower(makeID("ElegancePower"))
).amount);
       this.isMagicNumberModified = true;
     } 
     super.applyPowers();
   }
 
   
   @Override
   public void upp() {
       upgradeMagicNumber(2);
   }
 }


