package servantreloaded.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;
import servantreloaded.relics.KneeBrace;

import static servantreloaded.util.Wiz.*;

public class TimeWarp extends AbstractEasyCard {
    public static final String ID = "TimeWarp";

    private static final int COST = 0;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int DISCARD_AND_DRAW = 1;

    public TimeWarp() {
        super(ID, COST, CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);


        this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = DISCARD_AND_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p != null && m != null) {
            vfx(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F);
        }
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        discard(this.magicNumber);
    }

    public void triggerOnManualDiscard() {
        atb(new DrawCardAction(this.magicNumber));
        if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
            KneeBrace.Block(3);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new TimeWarp();
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}


