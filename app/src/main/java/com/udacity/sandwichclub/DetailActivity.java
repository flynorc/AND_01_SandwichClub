package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import com.udacity.sandwichclub.utils.ListUtils;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        TextView akaTV = (TextView) findViewById(R.id.also_known_tv);
        akaTV.setText(ListUtils.implodeList(", ", sandwich.getAlsoKnownAs()));

        TextView ingredientsTV = (TextView) findViewById(R.id.ingredients_tv);
        ingredientsTV.setText(ListUtils.implodeList(", ", sandwich.getIngredients()));

        TextView originTV = (TextView) findViewById(R.id.origin_tv);
        originTV.setText(replaceEmptyStringWithPlaceholder(sandwich.getPlaceOfOrigin()));

        TextView descriptionTV = (TextView) findViewById(R.id.description_tv);
        descriptionTV.setText(replaceEmptyStringWithPlaceholder(sandwich.getDescription()));
    }
    
    private String replaceEmptyStringWithPlaceholder(String string) {
        if(string.isEmpty()) {
            return getString(R.string.detail_empty_string_placeholder);
        }

        return string;
    }

}
