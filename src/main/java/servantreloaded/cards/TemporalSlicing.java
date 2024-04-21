 package servantreloaded.cards;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
 import servantreloaded.actions.TemporalDamageAction;

 import static servantreloaded.util.Wiz.atb;

 public class TemporalSlicing extends AbstractEasyCard {
    public static final String ID = "TemporalSlicing";

   private static final int COST = 0;
   private static final int ATTACK_DMG = 4;
   private static final int ATTACK_TIMES = 2;
   
   public TemporalSlicing() {
     super(ID, COST, CardType.ATTACK,  AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
     
     this.isEthereal = true;
     this.exhaust = true;
     this.baseDamage = 4;
     this.magicNumber = this.baseMagicNumber = 2;
     this.isMultiDamage = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     for (int i = 0; i < this.magicNumber; i++) {
       atb(new SFXAction("ATTACK_HEAVY"));
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction(AbstractDungeon.player, (AbstractGameEffect)new CleaveEffect(), 0.3F));
       atb(new TemporalDamageAction(this.baseDamage));
     } 
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TemporalSlicing();
   }
   
@Override
   public void upp() {
       upgradeMagicNumber(1);
   }
 }


