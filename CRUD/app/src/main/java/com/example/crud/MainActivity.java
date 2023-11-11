package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button balt, bbaj, bcamb, bcons;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balt = findViewById(R.id.altas);
        bbaj = findViewById(R.id.bajas);
        bcamb = findViewById(R.id.cambios);
        bcons = findViewById(R.id.consultas);

        balt.setOnClickListener(this);
        bbaj.setOnClickListener(this);
        bcamb.setOnClickListener(this);
        bcons.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();

        if(cadenita.equals("Altas")){
            Toast.makeText(this, "Altas", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);

        } else if (cadenita.equals("Bajas")) {
            Toast.makeText(this, "Bajas", Toast.LENGTH_SHORT).show();

        } else if (cadenita.equals("Cambios")) {
            Toast.makeText(this, "Cambios", Toast.LENGTH_SHORT).show();

        } else if (cadenita.equals("Consultas")) {
            Toast.makeText(this, "Consultas", Toast.LENGTH_SHORT).show();

        }
    }

}