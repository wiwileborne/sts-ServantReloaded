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
 public class FollowUp extends AbstractEasyCard {
   public static final String ID = "FollowUp";
   private static final int COST = 1;
   private static final int ATTACK_DMG = 10;
   private static final int UPGRADE_PLUS_DMG = 4;
   
   public FollowUp() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
       cardsToPreview = new FollowUp2(); // Preview a FollowUp2 when hovering over this card.
     
     this.baseDamage = 10;
     this.exhaust = true;
     this.isEthereal = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
     
     AbstractCard c = (new FollowUp2()).makeCopy();
     if (!canUpgrade()) c.upgrade(); 
     atb(new MakeTempCardInHandAction(c, false));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FollowUp();
   }
   
   @Override
   public void upp() {
       upgradeDamage(4);
   }

 }


