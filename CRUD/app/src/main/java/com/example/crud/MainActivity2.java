package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    Button baltas, breg;
    EditText etclave, etnameL, etnameA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etclave = findViewById(R.id.clave);
        etnameL = findViewById(R.id.nomL);
        etnameA = findViewById(R.id.nomA);

        baltas = findViewById(R.id.alt);
        breg = findViewById(R.id.reg);

        baltas.setOnClickListener(this);
        breg.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();
        if (cadenita.equals("Agregar")) {
            Base admin = new Base(this, "administracion", null, 1);
            SQLiteDatabase basededatos = admin.getWritableDatabase();
            String clav = etclave.getText().toString();
            String nL = etnameL.getText().toString();
            String nA = etnameA.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("clave", clav);
            registro.put("nombrelibro", nL);
            registro.put("nombreautor", nA);
            basededatos.insert("articulos", null, registro);
            basededatos.close();
            Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();

        } else if (cadenita.equals("Regresar")) {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);
        }
    }

}