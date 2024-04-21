package servantreloaded.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends AbstractEasyCard {
    public final static String ID = "Defend";
    
    private static final int COST = 1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3;
    public Defend() {
        super(ID, COST, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = BLOCK_AMT;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_PLUS_BLOCK);
    }
}