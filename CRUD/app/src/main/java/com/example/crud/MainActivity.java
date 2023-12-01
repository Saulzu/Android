package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button balt, bbaj, bcamb, bcons;
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

        if(cadenita.equals("Nueva canción")){
            Toast.makeText(this, "Nueva", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        } else if (cadenita.equals("Borrar canción")) {
            Toast.makeText(this, "Quitar", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        } else if (cadenita.equals("Cambiar canción")) {
            Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            startActivity(intent);
        } else if (cadenita.equals("Consultar canción")) {
            Toast.makeText(this, "Ver", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity5.class);
            startActivity(intent);
        }
    }
}