 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ThrowKnivesAction;

 import static servantreloaded.ServantReloadedMod.makeID;

 public class Moonlight extends AbstractEasyCard {
   public static final String ID = "Moonlight";
   

   private static final int COST = 1;
   private static final int ATTACK_DMG = 5;
   private static final int THROW = 2;
   
   public Moonlight() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
     
     this.baseDamage = 5;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     for (int i = 0; i < this.magicNumber; i++)
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ThrowKnivesAction(p, m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), "Vulnerable"));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Moonlight();
   }
   
   public void applyPowers() {
     this.baseDamage = 5;
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


