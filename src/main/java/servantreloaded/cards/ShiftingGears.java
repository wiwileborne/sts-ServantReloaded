 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.ElegancePower;
 import servantreloaded.relics.KneeBrace;

 import static servantreloaded.util.Wiz.atb;
 public class ShiftingGears extends AbstractEasyCard {
    public static final String ID = "ShiftingGears";

   private static final int COST = 1;
   private static final int DRAW = 3;
   
   public ShiftingGears() {
     super(ID, COST, CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
 
     
     this.magicNumber = this.baseMagicNumber = 3;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
   }
   
   public void triggerOnManualDiscard() {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new ElegancePower(AbstractDungeon.player, 1), 1));

       if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
           KneeBrace.Block(3);
       }
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShiftingGears();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


