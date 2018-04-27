package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        String mainName;
        List<String> alsoKnownAs;
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients;


        try {
            //convert json string into a JSON object
            JSONObject sandwichJson = new JSONObject(json);

            //Name fields
            JSONObject name = sandwichJson.getJSONObject("name");
            mainName = name.getString("mainName");
            JSONArray alsoKnownAsJAY = name.getJSONArray("alsoKnownAs");
            placeOfOrigin = sandwichJson.getString("placeOfOrigin");
            description = sandwichJson.getString("description");
            image = sandwichJson.getString("image");
            JSONArray ingredientsJAY = sandwichJson.getJSONArray("ingredients");

            //convert the two JSONArrays to java ArrayList
            //used method from https://stackoverflow.com/questions/17037340/converting-jsonarray-to-arraylist?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            alsoKnownAs = new ArrayList<>();
            if (alsoKnownAsJAY != null){
                for (int i = 0; i < alsoKnownAsJAY.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsJAY.getString(i));
                }
            }

            ingredients = new ArrayList<>();
            if (ingredientsJAY != null){
                for (int i = 0; i < ingredientsJAY.length(); i++) {
                    ingredients.add(ingredientsJAY.getString(i));
                }
            }
        }catch (JSONException e){
            throw new RuntimeException(e);
        }

        //Build a sandwich object with the data retrieved
        //the sandwich object for the listview row position clicked on.
        Sandwich thisSandwich = new Sandwich();

        thisSandwich.setMainName(mainName);
        thisSandwich.setAlsoKnownAs(alsoKnownAs);
        thisSandwich.setPlaceOfOrigin(placeOfOrigin);
        thisSandwich.setDescription(description);
        thisSandwich.setImage(image);
        thisSandwich.setIngredients(ingredients);

        return thisSandwich;
    }
}


