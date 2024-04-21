 package servantreloaded.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import servantreloaded.actions.VisionAction2;

 public class _DummyNotAttack extends AbstractEasyCard {
   public static final String ID = "_DummyNotAttack";
   

   private static final int COST = -2;
   
   public _DummyNotAttack() {
     super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {}

   @Override
   public void onChoseThisOption(){
     VisionAction2.prediction = false;
     System.out.println("ServantReloaded" + " : "+ "PAS D'ATTAQUE J'EN SUIS SUR !");
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new _DummyNotAttack();
   }
   
   public void upp() {}
 }


