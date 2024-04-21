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
 import static servantreloaded.util.Wiz.makeInHand;

 public class Initiator extends AbstractEasyCard {
   public static final String ID = "Initiator";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 5;
   private static final int UPGRADE_PLUS_DMG = 2;
   
   public Initiator() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
     cardsToPreview = new FollowUp(); // Preview a FollowUp when hovering over this card.
     this.baseDamage = 5;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
     
     AbstractCard c = (new FollowUp()).makeCopy();
     if (!canUpgrade()) c.upgrade(); 
     atb(new MakeTempCardInHandAction(c, false));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Initiator();
   }
   
   @Override
   public void upp() {
       upgradeDamage(2);
   }
 }


