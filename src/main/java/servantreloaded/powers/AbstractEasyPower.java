package servantreloaded.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import servantreloaded.ServantReloadedMod;
import servantreloaded.util.TexLoader;

import static servantreloaded.ServantReloadedMod.makeID;

public abstract class AbstractEasyPower extends AbstractPower {
    public boolean prediction;
    public int amount2 = -1;
    public boolean isTwoAmount = false;
    public static Color redColor2 = Color.RED.cpy();
    public static Color greenColor2 = Color.GREEN.cpy();
    public boolean canGoNegative2 = false;

    public AbstractEasyPower(String ID, String NAME,int PRIORITY ,PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.ID = ID;
        this.isTurnBased = isTurnBased;
        this.priority = PRIORITY;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = powerType;

        Texture normalTexture = TexLoader.getTexture(ServantReloadedMod.modID + "Resources/images/powers/" + ID.replaceAll(ServantReloadedMod.modID + ":", "") + "48.png");
        Texture hiDefImage = TexLoader.getTexture(ServantReloadedMod.modID + "Resources/images/powers/" + ID.replaceAll(ServantReloadedMod.modID + ":", "") + "128.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }
    public AbstractEasyPower(String ID, String NAME,int PRIORITY ,PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount, boolean prediction) {
        this.ID = makeID(ID);
        this.isTurnBased = isTurnBased;
        this.priority = PRIORITY;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = powerType;
        this.prediction = prediction;
        Texture normalTexture = TexLoader.getTexture(ServantReloadedMod.modID + "Resources/images/powers/" + ID.replaceAll(ServantReloadedMod.modID + ":", "") + "48.png");
        Texture hiDefImage = TexLoader.getTexture(ServantReloadedMod.modID + "Resources/images/powers/" + ID.replaceAll(ServantReloadedMod.modID + ":", "") + "128.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }


    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateDescription();
    }


    public void reducePower(int reduceAmount) {
        if (this.amount - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount = 0;
        } else {
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
        }
        updateDescription();
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (!isTwoAmount)
            return;
        if (amount2 > 0) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }
}