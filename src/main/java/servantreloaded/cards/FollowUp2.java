 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import static servantreloaded.util.Wiz.atb;
 public class FollowUp2 extends AbstractEasyCard {
   public static final String ID = "FollowUp2";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 15;
   private static final int UPGRADE_PLUS_DMG = 6;
   
   public FollowUp2() {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
     cardsToPreview = new FinishingTouch(); // Preview a FinishingTouch when hovering over this card.
     this.baseDamage = 15;
     this.exhaust = true;
     this.isEthereal = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     
     AbstractCard c = (new FinishingTouch()).makeCopy();
     if (!canUpgrade()) c.upgrade(); 
     atb(new MakeTempCardInHandAction(c, false));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FollowUp2();
   }
   
   @Override
   public void upp() {
       upgradeDamage(6);
   }
 }


