package servantreloaded.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import servantreloaded.actions.ChooseAction;
import servantreloaded.actions.VisionAction2;
import servantreloaded.powers.ReadPower;

import java.util.ArrayList;

import static servantreloaded.util.Wiz.*;

public class testCard extends AbstractEasyCard {
    public final static String ID = "testCard";
    // intellij stuff skill, self, uncommon, , , , , ,

    public testCard() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new _DummyAttack());
        easyCardList.add(new _DummyNotAttack());
        atb(new ChooseAction(easyCardList, this));
        applyToSelf(new ReadPower(p, m, this.block, this.magicNumber, VisionAction2.prediction));

    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }

}
