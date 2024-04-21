 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.actions.ConvertAction;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;
 public class InstantArmor extends AbstractEasyCard {
    public static final String ID = "InstantArmor";
    private static final int COST = 0;
   private static final int ARMOR_AMT = 6;
   private static final int SATELLITE = 3;
   
   public InstantArmor() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
 
     
     this.isInnate = true;
     this.exhaust = true;
     this.magicNumber = this.baseMagicNumber = 6;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ProtectionPower(p, this.magicNumber), this.magicNumber));
     atb(new ConvertAction(3));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new InstantArmor();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 6;
     if (!canUpgrade()) upgradeMagicNumber(3); 
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
       upgradeMagicNumber(3);
   }
 }


