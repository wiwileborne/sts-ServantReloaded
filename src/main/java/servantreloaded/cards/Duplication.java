package servantreloaded.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import servantreloaded.actions.BacklashAction;
import servantreloaded.actions.DuplicationAction;

import static servantreloaded.util.Wiz.atb;

public class Duplication extends AbstractEasyCard {
   public static final String ID = "Duplication";
   private static final int COST = 2;
   private static final int DUP = 1;
   
   public Duplication() {
     super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
     
     this.magicNumber = this.baseMagicNumber = DUP;
     this.exhaust = true;
   }
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     atb(new DuplicationAction(p, this.magicNumber));
     atb(new BacklashAction(1));
   }
   
   public AbstractCard makeCopy() {
     return new Duplication();
   }


  @Override
  public void upp() {
    upgradeMagicNumber(1);
  }
}


