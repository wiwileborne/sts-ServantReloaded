 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import servantreloaded.actions.BacklashAction;
 import static servantreloaded.util.Wiz.atb;
 public class LightFlow extends AbstractEasyCard {
   public static final String ID = "LightFlow";
   

   private static final int COST = 1;
   private static final int ATTACK_DMG = 8;
   private static final int UPGRADE_PLUS_DMG = 4;
   
   public LightFlow() {
     super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
 
     
     this.baseDamage = 8;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     for (int i = 0; i < 3; i++) {
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
     }
     atb(new BacklashAction(1));
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new LightFlow();
   }
   
@Override
   public void upp() {
       upgradeDamage(4);
   }
 }


