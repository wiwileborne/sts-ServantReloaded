 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import servantreloaded.powers.EmbodimentPower;
 import servantreloaded.powers.UpgradedEmbodimentPower;

 public class Embodiment extends AbstractEasyCard {
   
   public static final String ID = "Embodiment";
   private static final int COST = 2;
   
   public Embodiment() {
     super(ID, COST, CardType.POWER, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (!this.upgraded) { AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new EmbodimentPower(p, 1), 1)); }
     else { AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(p, p, (AbstractPower)new UpgradedEmbodimentPower(p, 1), 1)); }
   
   }
   public AbstractCard makeCopy() {
     return (AbstractCard)new Embodiment();
   }
   
   @Override
   public void upp() {
   }
 }


