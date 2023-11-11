package com.example.combosdinamicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner sdeptos, sprods;
    Button botoncito;
    EditText cantidad;

    ArrayAdapter<String> aadeptos;
    ArrayAdapter<Basurita> aaprods;

    ArrayList<Basurita> Arecibe = new ArrayList<>();

    int numero, total, seleccionado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botoncito = findViewById(R.id.button1);
        botoncito.setOnClickListener(this);

        sdeptos = findViewById(R.id.spinner1);
        sdeptos.setOnItemSelectedListener(this);

        sprods = findViewById(R.id.spinner2);
        sprods.setOnItemSelectedListener(this);

        cantidad = findViewById(R.id.edit1);

        String deptos[] = {"Selecciona","Papeleria","Computacion"};
        aadeptos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,deptos);
        sdeptos.setAdapter(aadeptos);

    }

    @Override
    public void onClick(View view) {

        numero = Integer.parseInt(cantidad.getText().toString());
        total = Arecibe.get(sprods.getSelectedItemPosition()).getCosto() * numero;

        Toast.makeText(this,
                    sdeptos.getSelectedItem().toString() + "\n" +
                        sprods.getSelectedItem().toString() + "\n" +
                        "Precio c/u: " + Arecibe.get(sprods.getSelectedItemPosition()).getCosto() + "\n" +
                        "Cantidad: " + numero + "\n" +
                        "Total: " + total
                ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.spinner1) {
            seleccionado = sdeptos.getSelectedItemPosition();

            Alistito aregreso = new Alistito();

            if(seleccionado == 1) {
                aregreso.agregar(1);
                Arecibe = aregreso.regresar();
                aaprods = new ArrayAdapter<Basurita>(this, android.R.layout.simple_spinner_item,Arecibe);
                sprods.setAdapter(aaprods);
            }
            else {
                if(seleccionado == 2) {
                    aregreso.agregar(2);
                    Arecibe = aregreso.regresar();
                    aaprods = new ArrayAdapter<Basurita>(this, android.R.layout.simple_spinner_item,Arecibe);
                    sprods.setAdapter(aaprods);
                }
                else
                    if(seleccionado == 0)
                        sprods.setAdapter(null);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}