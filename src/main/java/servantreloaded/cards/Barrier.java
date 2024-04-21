 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.ServantReloadedMod.makeID;
 import static servantreloaded.util.Wiz.atb;

 public class Barrier extends AbstractEasyCard {
    public static final String ID = "Barrier";

   private static final int COST = 2;
   private static final int BLOCK_AMT = 7;
   private static final int UPGRADE_PLUS_BLOCK = 3;
   private static final int PROTECTION_AMT = 7;
   private static final int UPGRADE_PROTECTION_BLOCK = 3;
   
   public Barrier() {
     super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
 
     
     this.baseBlock = 7;
     this.magicNumber = this.baseMagicNumber = 7;
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new GainBlockAction(p, p, this.block));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ProtectionPower(p, this.magicNumber), this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Barrier();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 7;
     if (!canUpgrade()) upgradeMagicNumber(3); 
     if (AbstractDungeon.player.hasPower(makeID("ElegancePower"))
) {
       upgradeMagicNumber((AbstractDungeon.player.getPower(makeID("ElegancePower"))
).amount);
       this.isMagicNumberModified = true;
     } 
     super.applyPowers();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeMagicNumber(3);
     } 
   }


   @Override
   public void upp() {
   }
 }


