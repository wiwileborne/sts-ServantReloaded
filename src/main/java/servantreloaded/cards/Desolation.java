 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.AmplifyDamagePower;

 import static servantreloaded.ServantReloadedMod.makeID;

 public class Desolation extends AbstractEasyCard {
   public static final String ID = "Desolation";
   

   private static final int COST = 1;
   private static final int ATTACK_DMG = 12;
   private static final int UPGRADE_PLUS_DMG = 4;
   
   public Desolation() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 12;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     
     if (m.hasPower(makeID("AmplifyDamagePower"))
) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new AmplifyDamagePower(m,
               (m.getPower(makeID("AmplifyDamagePower"))
).amount), (m.getPower(makeID("AmplifyDamagePower"))
).amount));
     }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Desolation();
   }
   
@Override
   public void upp() {
       upgradeDamage(4);
   }

 }


