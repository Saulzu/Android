package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String sClave, sTitulo, sAutor;
    EditText etClave, etTitulo, etAutor;
    Button bEditar, bRegresa;

    Spinner stipos, sgen;
    ArrayAdapter<String> aatipos;
    ArrayAdapter<Basurita> aagen;
    ArrayList<Basurita> Arecibe = new ArrayList<>();

    String sTs;

    int seleccionado;
    int index;

    boolean flag;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        etClave = findViewById(R.id.buscClave);
        etTitulo = findViewById(R.id.buscTitulo);
        etAutor = findViewById(R.id.buscAutor);

        bEditar = findViewById(R.id.buttonInicia);
        bRegresa = findViewById(R.id.buttonRegresa);

        bEditar.setOnClickListener(this);
        bRegresa.setOnClickListener(this);

        stipos = findViewById(R.id.buscombito);
        stipos.setOnItemSelectedListener(this);

        sgen = findViewById(R.id.buscombito2);
        sgen.setOnItemSelectedListener(this);

        String tipos [] = {"Selecciona", "Tradicional", "Moderna"};
        aatipos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tipos);
        stipos.setAdapter(aatipos);

        deshabilita();

    }

    @Override
    public void onClick(View view) {

        String cadenita = ((Button)view).getText().toString();

        if(cadenita.equals("Buscar")){

            sClave = etClave.getText().toString();

            if (!sClave.equals("")) {
                Base admin = new Base(this, "admini", null, 1);
                SQLiteDatabase baselite = admin.getWritableDatabase();

                Cursor cursor;

                cursor = baselite.rawQuery("SELECT * FROM mus WHERE clave = '" + sClave + "'", null);

                if (cursor.moveToNext()) {

                    sTitulo = cursor.getString(1);
                    sAutor = cursor.getString(2);
                    stipos.setSelection(Integer.parseInt(cursor.getString(3)));
                    sTs = cursor.getString(4);

                    flag = true;

                    limpieza();

                } else {

                    Toast.makeText(this, "No existe", Toast.LENGTH_LONG).show();
                    resetea();

                }
            }

        } else if (cadenita.equals("Regresar")) {

            resetea();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            if (aagen != null) {

                flag = false;
                deseleccionar();

            }

        } else if (cadenita.equals("Limpiar")) {

            if (aagen != null) {

                flag = false;
                deseleccionar();

            }

            Toast.makeText(this,"Buscar nueva canción",Toast.LENGTH_LONG).show();
            resetea();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.buscombito) {

            seleccionado = stipos.getSelectedItemPosition();
            Alistito aregreso = new Alistito();

            if (seleccionado == 1) {
                aregreso.agregar(1);
                Arecibe = aregreso.regresar();
                aagen = new ArrayAdapter<Basurita>(this, android.R.layout.simple_spinner_item,Arecibe);
                sgen.setAdapter(aagen);
            } else if (seleccionado == 2) {
                aregreso.agregar(2);
                Arecibe = aregreso.regresar();
                aagen = new ArrayAdapter<Basurita>(this, android.R.layout.simple_spinner_item,Arecibe);
                sgen.setAdapter(aagen);
            }

            if (flag == true) {

                index = 0;

                for (int j = 0; j < 5; j++) {

                    sgen.setSelection(j);

                    if (sgen.getSelectedItem().toString().equals(sTs)) {

                        index = sgen.getSelectedItemPosition();

                    }

                }
                sgen.setSelection(index);

            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
    private void limpieza() {

        bEditar.setText("Limpiar");

        etClave.setEnabled(false);

        etTitulo.setText(sTitulo);
        etAutor.setText(sAutor);
    }

    private void resetea() {

        bEditar.setText("Buscar");
        bRegresa.setEnabled(true);

        etClave.setEnabled(true);

        etClave.setText("");
        etTitulo.setText("");
        etAutor.setText("");

    }

    private void deshabilita() {

        stipos.setEnabled(false);
        sgen.setEnabled(false);

    }

    private void deseleccionar() {

        stipos.setSelection(0);
        aagen.clear();
        aagen.notifyDataSetChanged();

    }
}