package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
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

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText etClave, etTitulo, etAutor;
    Button bAltas, bRegresa;

    Spinner ctipos, cgen;
    ArrayAdapter<String> aatipos;
    ArrayAdapter<Basurita> aagen;
    ArrayList<Basurita> Arecibe = new ArrayList<>();
    int seleccionado;

    MediaPlayer mp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etClave = findViewById(R.id.editClave);
        etTitulo = findViewById(R.id.editTitulo);
        etAutor = findViewById(R.id.editAutor);

        bAltas = findViewById(R.id.buttonInicia);
        bRegresa = findViewById(R.id.buttonRegresa);

        bAltas.setOnClickListener(this);
        bRegresa.setOnClickListener(this);

        mp = MediaPlayer.create(this, R.raw.mario);

        ctipos = findViewById(R.id.newcombito);
        ctipos.setOnItemSelectedListener(this);

        cgen = findViewById(R.id.newcombito2);
        cgen.setOnItemSelectedListener(this);

        String gen [] = {"Selecciona", "Tradicional", "Moderna"};
        aatipos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gen);
        ctipos.setAdapter(aatipos);

        deshabilita();
    }

    @Override
    public void onClick(View view) {
        String cadenita = ((Button)view).getText().toString();

        if(cadenita.equals("Nueva")){

            limpieza();

        } else if (cadenita.equals("Regresar")) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (cadenita.equals("Altas")) {

            Base admin = new Base(this,"admini", null, 1);
            SQLiteDatabase baselite = admin.getWritableDatabase();

            String sClave = etClave.getText().toString();
            String sTitulo = etTitulo.getText().toString();
            String sAutor = etAutor.getText().toString();
            String sTipo = String.valueOf(ctipos.getSelectedItemPosition());
            String sGen = cgen.getSelectedItem().toString();

            if(!sClave.equals("") && !sTitulo.equals("") && !sAutor.equals("") && !sTipo.equals("") && !sGen.equals("")) {

            ContentValues registro = new ContentValues();
            registro.put("clave",sClave);
            registro.put("titulo",sTitulo);
            registro.put("autor",sAutor);
            registro.put("tipo",sTipo);
            registro.put("gen",sGen);

            baselite.insert("mus", null, registro);
            baselite.close();

            mp.start();
            Toast.makeText(this,"Cancion agregada",Toast.LENGTH_LONG).show();

            resetea();

            } else
                Toast.makeText(this,"Falta un campo",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.newcombito){
            seleccionado = ctipos.getSelectedItemPosition();
            Alistito aregreso = new Alistito();
            if (seleccionado == 1){
                aregreso.agregar(1);
                Arecibe = aregreso.regresar();
                aagen = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arecibe);
                cgen.setAdapter(aagen);
            } else if (seleccionado == 2){
                aregreso.agregar(2);
                Arecibe = aregreso.regresar();
                aagen = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arecibe);
                cgen.setAdapter(aagen);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    private void limpieza() {

        bAltas.setText("Altas");
        bRegresa.setEnabled(false);

        etClave.setEnabled(true);
        etTitulo.setEnabled(true);
        etAutor.setEnabled(true);

        etClave.requestFocus();

        habilita();

        etClave.requestFocus();
    }

    private void resetea() {

        bAltas.setText("Nueva");
        bRegresa.setEnabled(true);

        etClave.setEnabled(false);
        etTitulo.setEnabled(false);
        etAutor.setEnabled(false);

        ctipos.setSelection(0);
        aagen.clear();
        aagen.notifyDataSetChanged();

        deshabilita();

        etClave.setText("");
        etTitulo.setText("");
        etAutor.setText("");

    }

    private void habilita() {

        ctipos.setEnabled(true);
        cgen.setEnabled(true);

    }

    private void deshabilita() {

        ctipos.setEnabled(false);
        cgen.setEnabled(false);

    }

}