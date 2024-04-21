package servantreloaded.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import servantreloaded.cards.AbstractEasyCard;
import servantreloaded.screens.VisionScreen;

import java.util.ArrayList;

public class ChooseAction extends VisionAction2 {

    public ChooseAction(ArrayList<AbstractCard> list, String textforSelect, AbstractEasyCard ac) {
        super(list, textforSelect, ac, (card,cardIDchoose) -> {
                card.onChoseThisOption();
                System.out.println("ServantReloaded" + " : "+ " card" + card.cardID);

        });
    }

    public ChooseAction(ArrayList<AbstractCard> list, AbstractEasyCard ac) {
        this(list, VisionScreen.TEXT[1],ac);
    }
}