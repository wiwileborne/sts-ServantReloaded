 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.ThrowKnivesAction;
 import servantreloaded.powers.KnivesPower;

 import static servantreloaded.ServantReloadedMod.makeID;

 public class KillingDoll extends AbstractEasyCard {
    public static final String ID = "KillingDoll";

   private static final int COST = 2;
   private static final int ATTACK_DMG = 2;
   private static final int UPGRADE_PLUS_DMG = 1;
   private int KNIVES = 0;
   
   public KillingDoll() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);
     
     this.baseDamage = 2;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (p.hasPower(KnivesPower.ID)
) {
       this.KNIVES = (p.getPower(KnivesPower.ID)
).amount; int i;
       for (i = 0; i < this.KNIVES; i++)
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ThrowKnivesAction(p, m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), "Golden"));
       this.KNIVES = (p.getPower(KnivesPower.ID)
).amount;
       for (i = 0; i < this.KNIVES; i++)
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ThrowKnivesAction(p, m, new DamageInfo(p, this.baseDamage, this.damageTypeForTurn), "Golden")); 
     } 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new KillingDoll();
   }
   
   public void applyPowers() {
     this.baseDamage = 2;
     if (!canUpgrade()) this.baseDamage++; 
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
       upgradeDamage(1);
   }
 }


