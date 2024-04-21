 package servantreloaded.cards;


 import basemod.helpers.BaseModCardTags;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.FlawlessFormPower;
 import servantreloaded.powers.ProtectionPower;

 import static servantreloaded.ServantReloadedMod.makeID;

 public class FlawlessForm extends AbstractEasyCard {
    public static final String ID = "FlawlessForm";
    private static final int COST = 3;
   private static final int PROTECTION_AMT = 12;
   
   public FlawlessForm() {
     super(ID, COST, CardType.POWER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = 12;
     this.tags.add(BaseModCardTags.FORM);
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new FlawlessFormPower(p, -1), -1));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new ProtectionPower(p, this.magicNumber), this.magicNumber));
   }
   
   public void triggerOnEndOfTurnForPlayingCard() {
     if (!canUpgrade())
       this.retain = true; 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FlawlessForm();
   }
   
   public void applyPowers() {
     this.magicNumber = this.baseMagicNumber = 12;
     if (!canUpgrade()) upgradeMagicNumber(4); 
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
     this.retain = true;
     upgradeMagicNumber(4);
   }
 }


