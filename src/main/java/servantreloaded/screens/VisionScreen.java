 package servantreloaded.screens;
 
 
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
 import com.megacrit.cardcrawl.helpers.input.InputHelper;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import com.megacrit.cardcrawl.rewards.RewardItem;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
 import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;
 import java.util.ArrayList;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import servantreloaded.ServantReloadedMod;

 import static servantreloaded.ServantReloadedMod.makeID;


 public class VisionScreen
   extends CardRewardScreen
 {
   public AbstractCard codexCard = null;
   public boolean onCardSelect = true;
   public boolean hasTakenAll = false;
   public boolean cardOnly = false;
   public RewardItem rItem = null;
   private boolean codex = false;
   private boolean draft = false;
   private String header = "";
   private SkipCardButton skipButton = new SkipCardButton();
   private SingingBowlButton bowlButton = new SingingBowlButton();
   private int draftCount = 0;
 
   
   public void update() {
     this.skipButton.update();
     this.bowlButton.update();
     updateControllerInput();
     cardSelectUpdate();
   }
   
   private void updateControllerInput() {
     if (!Settings.isControllerMode || AbstractDungeon.topPanel.selectPotionMode || !AbstractDungeon.topPanel.potionUi.isHidden) {
       return;
     }
     int index = 0;
     boolean anyHovered = false;
     for (AbstractCard c : this.rewardGroup) {
       if (c.hb.hovered) {
         anyHovered = true;
         break;
       } 
       index++;
     } 
     if (!anyHovered) {
       index = 0;
       Gdx.input.setCursorPosition((int)((AbstractCard)this.rewardGroup.get(index)).hb.cX, Settings.HEIGHT - (int)((AbstractCard)this.rewardGroup.get(index)).hb.cY);
     }
     else if (CInputActionSet.left.isJustPressed() || CInputActionSet.altLeft.isJustPressed()) {
       if (--index < 0) {
         index = this.rewardGroup.size() - 1;
       }
       Gdx.input.setCursorPosition((int)((AbstractCard)this.rewardGroup.get(index)).hb.cX, Settings.HEIGHT - (int)((AbstractCard)this.rewardGroup.get(index)).hb.cY);
     }
     else if (CInputActionSet.right.isJustPressed() || CInputActionSet.altRight.isJustPressed()) {
       if (++index > this.rewardGroup.size() - 1) {
         index = 0;
       }
       Gdx.input.setCursorPosition((int)((AbstractCard)this.rewardGroup.get(index)).hb.cX, Settings.HEIGHT - (int)((AbstractCard)this.rewardGroup.get(index)).hb.cY);
     } 
   }
   
   private void cardSelectUpdate() {
     AbstractCard hoveredCard = null;
     for (AbstractCard c : this.rewardGroup) {
       c.update();
       c.updateHoverLogic();
       if (c.hb.justHovered) {
         CardCrawlGame.sound.playV("CARD_OBTAIN", 0.4F);
       }
       if (c.hb.hovered) {
         hoveredCard = c;
       }
     } 
     if (hoveredCard != null && InputHelper.justClickedLeft) {
       hoveredCard.hb.clickStarted = true;
     }
     if (hoveredCard != null && (InputHelper.justClickedRight || CInputActionSet.proceed.isJustPressed())) {
       CardCrawlGame.cardPopup.open(hoveredCard);
     }
     if (hoveredCard != null && CInputActionSet.select.isJustPressed()) {
       hoveredCard.hb.clicked = true;
     }
     if (hoveredCard != null && hoveredCard.hb.clicked) {
       hoveredCard.hb.clicked = false;
       this.skipButton.hide();
       this.bowlButton.hide();
       if (this.codex) {
         this.codexCard = hoveredCard;
       } else {
         
         acquireCard(hoveredCard);
       } 
       takeReward();
       if (!this.draft || this.draftCount >= 15) {
         AbstractDungeon.closeCurrentScreen();
         this.draftCount = 0;
       } else {
         
         draftOpen();
       } 
     } 
   }
   
   private void acquireCard(AbstractCard hoveredCard) {
     this.prediction = hoveredCard;
   }
   
   private void takeReward() {
     if (this.rItem != null) {
       AbstractDungeon.combatRewardScreen.rewards.remove(this.rItem);
       AbstractDungeon.combatRewardScreen.positionRewards();
       if (AbstractDungeon.combatRewardScreen.rewards.isEmpty()) {
         AbstractDungeon.combatRewardScreen.hasTakenAll = true;
         AbstractDungeon.overlayMenu.proceedButton.show();
       } 
     } 
   }
   
   public void render(SpriteBatch sb) {
     this.skipButton.render(sb);
     this.bowlButton.render(sb);
     renderCardReward(sb);
   }
   
   private void renderCardReward(SpriteBatch sb) {
     for (AbstractCard c : this.rewardGroup) {
       c.render(sb);
     }
     for (AbstractCard c : this.rewardGroup) {
       c.renderCardTip(sb);
     }
   }
   
   public void reopen() {
     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
     if (this.draft) {
       this.skipButton.hide();
       this.bowlButton.hide();
     }
     else if (this.codex) {
       this.skipButton.show();
       this.bowlButton.hide();
     }
     else if (AbstractDungeon.player.hasRelic("Singing Bowl")) {
       this.skipButton.show(true);
       this.bowlButton.show(this.rItem);
     } else {
       
       this.skipButton.show();
       this.bowlButton.hide();
     } 
     AbstractDungeon.topPanel.unhoverHitboxes();
     AbstractDungeon.isScreenUp = true;
     if (!this.codex) {
       AbstractDungeon.dynamicBanner.appear(this.header);
     } else {
       
       AbstractDungeon.dynamicBanner.appear(TEXT[0]);
     }
     AbstractDungeon.overlayMenu.proceedButton.hide();
   }
   
   public void open(ArrayList<AbstractCard> cards, RewardItem rItem, String header) {
     AbstractDungeon.cardRewardScreen = ServantReloadedMod.vs;
     this.codex = false;
     this.draft = false;
     this.rItem = rItem;
     this.skipButton.hide();
     this.bowlButton.hide();
     this.onCardSelect = true;
     AbstractDungeon.topPanel.unhoverHitboxes();
     this.rewardGroup = cards;
     AbstractDungeon.isScreenUp = true;
     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
     this.header = header;
     AbstractDungeon.dynamicBanner.appear(header);
     AbstractDungeon.overlayMenu.proceedButton.hide();
     AbstractDungeon.overlayMenu.showBlackScreen();
     placeCards(Settings.WIDTH / 2.0F, CARD_TARGET_Y);
   }

   private void placeCards(float x, float y) {
     float cardWidth = AbstractCard.IMG_WIDTH;
     float paddingX = PAD_X;
     int numCards = this.rewardGroup.size();
     float startX = x - ((numCards - 1) * (cardWidth + paddingX) / 2.0F);

     for (int i = 0; i < numCards; i++) {
       float targetX = startX + i * (cardWidth + paddingX);
       placeCard((AbstractCard) this.rewardGroup.get(i), targetX, y);
     }
   }

   private void placeCard(AbstractCard card, float targetX, float targetY) {
     card.target_x = targetX;
     card.target_y = targetY;
     card.drawScale = 0.75F;
     card.targetDrawScale = 0.75F;
     card.current_x = card.target_x;
     card.current_y = card.target_y;
   }

   
   public void onClose() {
     AbstractDungeon.cardRewardScreen = new CardRewardScreen();
   }
   
   public void reset() {
     this.draftCount = 0;
     this.codex = false;
     this.draft = false;
   }

   public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(makeID("CardVisionScreen")).TEXT;
   private static final float PAD_X = 40.0F * Settings.scale;
   private static final float CARD_TARGET_Y = Settings.HEIGHT * 0.45F;
   public ArrayList<AbstractCard> rewardGroup;
   public AbstractCard prediction;
 }


