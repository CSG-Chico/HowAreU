package com.example.howareu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.howareu.R;
import com.example.howareu.constant.Arrays;
import com.example.howareu.constant.Strings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InspirationalQuotesActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 3000; // 3 seconds
    private SharedPreferences mPrefs;
    public TextView quoteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspirational_quotes);
        mPrefs= getSharedPreferences(Strings.START_PREF_NAME, Context.MODE_PRIVATE);
        quoteText = findViewById(R.id.quoteText);
        //Getting Hard coded quotes  and savedQuote from pref
        ArrayList<String> quotes = Arrays.quotes();
        Set<String> savedQuote = mPrefs.getStringSet(Strings.SAVED_QUOTE, null);
        if(savedQuote==null){
            savedQuote= new HashSet<>();
        }
        //Random logic
        Random random = new Random();
        int ran= random.nextInt(quotes.size());
        if(!(savedQuote.isEmpty())){
            while(savedQuote.contains(quotes.get(ran))){
                ran= random.nextInt(quotes.size());
            }
        }
        //Quote output
        String selectedQuote = quotes.get(ran);
        savedQuote.add(selectedQuote);
        quoteText.setText(selectedQuote);
        //Clear savedQuote pref when 7thday or just save to pref
        if(savedQuote.size()==quotes.size()){
            mPrefs.edit().putStringSet(Strings.SAVED_QUOTE,null).apply();
        }
        else{
            HashSet<String> hashSet = new HashSet<String>(savedQuote);
            mPrefs.edit().putStringSet(Strings.SAVED_QUOTE,hashSet).apply();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(InspirationalQuotesActivity.this, MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}