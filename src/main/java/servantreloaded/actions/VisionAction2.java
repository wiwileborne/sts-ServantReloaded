package servantreloaded.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import servantreloaded.ServantReloadedMod;
import servantreloaded.cards.AbstractEasyCard;
import servantreloaded.cards._DummyAttack;
import servantreloaded.cards._DummyNotAttack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class VisionAction2 extends AbstractGameAction
{
    private BiConsumer<AbstractEasyCard, AbstractCard> callback;
    public static boolean prediction;
    private String text;
    private ArrayList<AbstractCard> selectGroup;
    private AbstractEasyCard ac;
    public VisionAction2(ArrayList<AbstractCard> group, String textForSelect,AbstractEasyCard ac, Predicate<AbstractCard> cardFilter, BiConsumer<AbstractEasyCard, AbstractCard> callback) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.text = textForSelect;
        this.callback = callback;
        this.ac = ac;
        this.selectGroup = group;
    }
    public VisionAction2(ArrayList<AbstractCard> group, String textForSelect, AbstractEasyCard ac, BiConsumer<AbstractEasyCard, AbstractCard> callback) {
        this(group, textForSelect,ac, (c) -> {
            return true;
        }, callback);
    }

    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {
            ServantReloadedMod.vs.open(this.selectGroup, null, text);
            tickDuration();
            return;
        }

        this.callback.accept(this.ac,ServantReloadedMod.vs.prediction);
        this.isDone = true;
        tickDuration();
    }
}


