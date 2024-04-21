 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import servantreloaded.powers.AmplifyDamagePower;
 import static servantreloaded.util.Wiz.atb;
 public class Misdirection extends AbstractEasyCard {
    public static final String ID = "Misdirection";

   private static final int COST = 1;
   private static final int BLOCK_AMT = 6;
   private static final int UPGRADE_PLUS_BLOCK = 3;
   private static final int DEBUFF = 1;
   
   public Misdirection() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
     
     this.baseBlock = 6;
     this.magicNumber = this.baseMagicNumber = 1;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new GainBlockAction(p, p, this.block));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new AmplifyDamagePower(m, this.magicNumber), this.magicNumber));
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(m, p, (AbstractPower)new WeakPower(m, this.magicNumber, false), this.magicNumber));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Misdirection();
   }
   
@Override
   public void upp() {
     upgradeBlock(3);
   }
 }


