package servantreloaded.util;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class powerHelper {
    public static String[] getDescByID(String powerID) {
        return CardCrawlGame.languagePack.getPowerStrings(powerID).DESCRIPTIONS;
    }
    public static String getNameByID(String powerID) {
        return CardCrawlGame.languagePack.getPowerStrings(powerID).NAME;
    }


    public static boolean isValidTargetAndIntent(AbstractMonster target,boolean prediction) {
        if (target == null || target.isDeadOrEscaped()) {
            return false;
        }

        if (!prediction && !isAttackIntent(target.intent)) {
            return true;
        }

        return prediction && isAttackIntent(target.intent);
    }

    public static boolean isAttackIntent(AbstractMonster.Intent intent) {
        return intent == AbstractMonster.Intent.ATTACK ||
                intent == AbstractMonster.Intent.ATTACK_BUFF ||
                intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                intent == AbstractMonster.Intent.ATTACK_DEFEND;
    }
}
