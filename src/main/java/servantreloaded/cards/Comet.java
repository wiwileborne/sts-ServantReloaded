 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 
 public class Comet extends AbstractEasyCard {
   public static final String ID = "Comet";
   

   private static final int COST = 0;
   private static final int ATTACK_DMG = 3;
   private static final int DRAW = 2;
   
   public Comet() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 3;
     this.magicNumber = this.baseMagicNumber = 2;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
     
     if (this.magicNumber - AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1 > 0)
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DrawCardAction(p, this.magicNumber - AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1)); 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Comet();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }

 }


