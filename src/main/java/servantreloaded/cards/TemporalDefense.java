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

 public class TemporalDefense extends AbstractEasyCard {
    public static final String ID = "TemporalDefense";

   private static final int COST = 0;
   private static final int BLOCK_AMT = 4;
   private static final int UPGRADE_PLUS_BLOCK = 2;
   private static final int PROTECTION_AMT = 4;
   private static final int UPGRADE_PROTECTION_BLOCK = 2;
   
   public TemporalDefense() {
     super(ID, COST, CardType.SKILL,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
     
     this.isEthereal = true;
     this.exhaust = true;
     this.baseBlock = 4;
     this.magicNumber = this.baseMagicNumber = 4;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new ProtectionPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TemporalDefense();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 4;
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
         upgradeBlock(2);
         upgradeMagicNumber(2);

     }
 }


