package com.example.practk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,  View.OnClickListener {
    EditText editadito;
    Spinner sdeptos, sprods;
    TextView vistita;
    Button botoncito;
    ArrayAdapter<String> aadeptos;
    ArrayAdapter<Basurita> aaprods;
    ArrayList<Basurita> Arecibe = new ArrayList<>();
    int seleccionado = 0;
    int cant, total;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botoncito = findViewById(R.id.boton);
        botoncito.setOnClickListener(this);

        editadito = findViewById(R.id.cant);

        sdeptos = findViewById(R.id.combito);
        sdeptos.setOnItemSelectedListener(this);

        sprods = findViewById(R.id.combito2);
        sprods.setOnItemSelectedListener(this);

        String deptos [] = {"Selecciona", "Papeleria", "Computacion"};
        aadeptos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deptos);
        sdeptos.setAdapter(aadeptos);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.combito){
            seleccionado = sdeptos.getSelectedItemPosition();
            Alistito aregreso = new Alistito();
            if (seleccionado == 1){
                aregreso.agregar(1);
                Arecibe = aregreso.regresar();
                aaprods = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arecibe);
                sprods.setAdapter(aaprods);
            } else if (seleccionado == 2){
                aregreso.agregar(2);
                Arecibe = aregreso.regresar();
                aaprods = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arecibe);
                sprods.setAdapter(aaprods);
            }
            else if (seleccionado == 0){
                sprods.setAdapter(null);
            }
        }

    }


    @Override
    public void onClick(View view) {
        Toast.makeText(this, sdeptos.getSelectedItem().toString()+ " " + sprods.getSelectedItem().toString(), Toast.LENGTH_LONG ).show();
        String cadenita = ((Button)view).getText().toString();

        cant =  Integer.parseInt(editadito.getText().toString());
        total = Arecibe.get(sprods.getSelectedItemPosition()).getCosto() * cant;
        if(cadenita.equals("Pagar")){
            Toast.makeText(this, "Total: "  +  total, Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
