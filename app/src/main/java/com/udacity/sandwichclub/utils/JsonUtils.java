package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Log.d("JSON", json);

        try {
            //all the data
            JSONObject sandwichJsonObject = new JSONObject(json);

            //sandwich name and aka
            JSONObject sandwichName = sandwichJsonObject.getJSONObject("name");

            //main name
            String mainName = sandwichName.getString("mainName");

            //aka
            List<String> alsoKnownAs = createListFromJsonArray(sandwichName.getJSONArray("alsoKnownAs"));

            //place of origin
            String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");

            //description
            String description = sandwichJsonObject.getString("description");

            //image
            String image = sandwichJsonObject.getString("image");

            //ingredients
            List<String> ingredients = createListFromJsonArray(sandwichJsonObject.getJSONArray("ingredients"));

            //return the sandwich object made from parsed data
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * simple helper method to supplement the String.join method that is not available API < 26
     */
    private static List<String> createListFromJsonArray(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<String>();

        if (jsonArray != null) {
            for (int i=0; i<jsonArray.length(); i++){
                list.add(jsonArray.getString(i));
            }
        }

        return list;
    }
}
