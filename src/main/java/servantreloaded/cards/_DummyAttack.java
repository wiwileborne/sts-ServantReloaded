 package servantreloaded.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import servantreloaded.actions.VisionAction2;

 public class _DummyAttack extends AbstractEasyCard {
   public static final String ID = "_DummyAttack";
   

   private static final int COST = -2;
   
   public _DummyAttack() {
     super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {}

   @Override
   public void onChoseThisOption(){
     VisionAction2.prediction = true;
     System.out.println("ServantReloaded" + " : "+ "IL ATTAQQUEEEEEE !");
   }
   
   public AbstractCard makeCopy() {
     return new _DummyAttack();
   }


   @Override
   public void upp() {
   }

 }


