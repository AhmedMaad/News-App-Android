package com.maad.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup rd = findViewById(R.id.rg_countries);
        rd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                switch (id) {
                    case R.id.rb_australia:
                        savePreference("au");
                        break;

                    case R.id.rb_china:
                        savePreference("cn");
                        break;

                    case R.id.rb_egypt:
                        savePreference("eg");
                        break;

                    case R.id.rb_france:
                        savePreference("fr");
                        break;

                    case R.id.rb_germany:
                        savePreference("de");
                        break;

                    case R.id.rb_japan:
                        savePreference("jp");
                        break;

                    case R.id.rb_Portugal:
                        savePreference("pt");
                        break;

                    case R.id.rb_saudi_arabia:
                        savePreference("sa");
                        break;

                    case R.id.rb_turkey:
                        savePreference("tr");
                        break;

                    case R.id.rb_united_states:
                        savePreference("us");
                        break;
                }
            }
        });

    }


    private void savePreference(String countryCode) {
        SharedPreferences.Editor editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putString("country", countryCode);
        editor.apply();
    }

}
