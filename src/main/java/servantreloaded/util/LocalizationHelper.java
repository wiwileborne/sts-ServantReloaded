package servantreloaded.util;

import com.badlogic.gdx.Gdx;
import com.google.gson.*;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.nio.charset.StandardCharsets;

public class LocalizationHelper {
    static JsonObject Cards;

    public static void LoadCards(String Path) {
        // Créez un objet Gson pour mapper le JSON vers des objets Java
        Gson gson = new Gson();
        String JsonCards = Gdx.files.internal(Path).readString(String.valueOf(StandardCharsets.UTF_8));
        // Créez un objet JsonParser pour parser la chaîne JSON
        JsonParser parser = new JsonParser();
        // Utilisez le parser pour obtenir un objet JsonElement à partir de la chaîne JSON
        JsonElement jsonElement = parser.parse(JsonCards);
        // Utilisez Gson pour désérialiser l'objet JSON en un objet Java
        Cards = gson.fromJson(jsonElement, JsonObject.class);
    }

    public static CardStrings GetCard(String ID) {
        // Utilisez la méthode get() pour obtenir la traduction correspondant à la clé
        JsonObject Card = Cards.getAsJsonObject(ID);
        // Créez un objet CardStrings pour stocker les traductions
        CardStrings cardStrings = new CardStrings();
        // Remplissez les propriétés de CardStrings en fonction des valeurs du JsonObject
        if (Card != null) {
            cardStrings.NAME = Card.has("NAME") ? Card.get("NAME").getAsString() : "[MISSING_TITLE]";
            cardStrings.DESCRIPTION = Card.has("DESCRIPTION") ? Card.get("DESCRIPTION").getAsString() : "[MISSING_DESCRIPTION]";
            cardStrings.UPGRADE_DESCRIPTION = Card.has("UPGRADE_DESCRIPTION") ? Card.get("UPGRADE_DESCRIPTION").getAsString() : cardStrings.DESCRIPTION ;

            // Remplissez EXTENDED_DESCRIPTION s'il y a des valeurs disponibles, sinon, utilisez les valeurs par défaut
            if (Card.has("EXTENDED_DESCRIPTION")) {
                JsonArray extendedDescriptionArray = Card.getAsJsonArray("EXTENDED_DESCRIPTION");
                String[] extendedDescription = new String[extendedDescriptionArray.size()];
                for (int i = 0; i < extendedDescription.length; i++) {
                    extendedDescription[i] = extendedDescriptionArray.get(i).getAsString();
                }
                cardStrings.EXTENDED_DESCRIPTION = extendedDescription;
            } else {
                cardStrings.EXTENDED_DESCRIPTION = new String[]{"[MISSING_0]", "[MISSING_1]", "[MISSING_2]"};
            }
        } else {
            System.out.println("La carte n'a pas été trouvée.");
            // En cas d'absence de carte, utilisez les valeurs par défaut
            cardStrings.NAME = "[MISSING_TITLE]";
            cardStrings.DESCRIPTION = "[MISSING_DESCRIPTION]";
            cardStrings.UPGRADE_DESCRIPTION = "[MISSING_DESCRIPTION+]";
            cardStrings.EXTENDED_DESCRIPTION = new String[]{"[MISSING_0]", "[MISSING_1]", "[MISSING_2]"};
        }
        return cardStrings;
    }




}
