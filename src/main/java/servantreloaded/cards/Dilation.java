 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.ServantReloadedMod.makeID;

 public class Dilation extends AbstractEasyCard {
   public static final String ID = "Dilation";
   private static final int COST = 0;
   private static final int PROTECTION_AMT = 3;
   private static final int UPGRADE_PROTECTION_BLOCK = 1;
   public int used;
   
   public Dilation() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 3;
     this.used = 0;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ProtectionPower(p, this.magicNumber), this.magicNumber));
     this.used++;
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Dilation();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 3 + this.used;
     if (!canUpgrade()) upgradeMagicNumber(1); 
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
       upgradeMagicNumber(1);
   }

 }


