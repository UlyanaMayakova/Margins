package com.example.margins;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static @StyleRes int theme = R.style.AppTheme;

    private Locale locale = new Locale("ru");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(theme);
        setContentView(R.layout.activity_main);

        final Spinner spinner = findViewById(R.id.spinner);
        final TextView text = findViewById(R.id.text);
        Button ok = findViewById(R.id.button);
        final Spinner spinnerMargins = findViewById(R.id.spinner_margins);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(
                this, R.array.margins, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMargins.setAdapter(adapter1);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String language = spinner.getSelectedItem().toString();
                String chosenTheme = spinnerMargins.getSelectedItem().toString();

                switch (language) {
                    case "Русский":
                        locale = new Locale("ru");
                        break;
                    case "English":
                        locale = new Locale("en");
                        break;
                }

                switch (chosenTheme) {
                    case "Маленький":
                        theme = R.style.SmallMargins;
                        break;
                    case "Средний":
                        theme = R.style.MediumMargins;
                        break;
                    case "Большой":
                        theme = R.style.LargeMargins;
                        break;
                }

                Configuration configuration = new Configuration();
                configuration.setLocale(locale);
                getResources().updateConfiguration(configuration, getBaseContext().
                        getResources().getDisplayMetrics());
                text.setText(R.string.text);
                recreate();
            }
        });
    }
}