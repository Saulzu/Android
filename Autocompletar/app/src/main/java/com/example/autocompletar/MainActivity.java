package com.example.autocompletar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button botoncito;
    AutoCompleteTextView autovista;
    TextView vistita;

    String[] nombrecitos =
            {"Manuel","Micaela","Mario","Morticia","Melani","Mirna","Magda","Maite","Mireya","Monica"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botoncito = findViewById(R.id.button1);
        botoncito.setText("Click");
        botoncito.setOnClickListener(this);

        autovista = findViewById(R.id.auto1);
        vistita = findViewById(R.id.text1);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,nombrecitos);
        autovista.setThreshold(2);
        autovista.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        vistita.setText(autovista.getText());
    }
}