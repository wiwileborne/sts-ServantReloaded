 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.actions.TimeEmbeddedAction;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.ServantReloadedMod.makeID; import static servantreloaded.util.Wiz.atb;

 public class Contraction extends AbstractEasyCard {
   public static final String ID = "Contraction";
   private static final int COST = 1;
   private static final int PROTECTION_AMT = 5;
   private static final int UPGRADE_PROTECTION_BLOCK = 3;
   
   public Contraction() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ProtectionPower(p, this.magicNumber), this.magicNumber));
     atb(new TimeEmbeddedAction(p));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Contraction();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = PROTECTION_AMT;
     if (!canUpgrade()) upgradeMagicNumber(UPGRADE_PROTECTION_BLOCK);
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
       upgradeMagicNumber(UPGRADE_PROTECTION_BLOCK);
   }

 }


