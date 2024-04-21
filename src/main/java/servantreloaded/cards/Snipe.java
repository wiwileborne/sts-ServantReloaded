 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.VisionAction;

 import static servantreloaded.ServantReloadedMod.makeID;

 public class Snipe extends AbstractEasyCard {
    public static final String ID = "Snipe";

   private static final int COST = 1;
   private static final int ATTACK_DMG = 15;
   private static final int UPGRADE_PLUS_DMG = 5;
   private static final int VUL = 1;
   
   public Snipe() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 15;
     this.magicNumber = this.baseMagicNumber = 1;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VisionAction(p, m, this.baseDamage, this.magicNumber, (AbstractCard)this));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Snipe();
   }
   
   public void applyPowers() {
     if (canUpgrade()) { this.baseDamage = 15; }
     else { this.baseDamage = 20; }
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
       upgradeDamage(5);
   }
 }


