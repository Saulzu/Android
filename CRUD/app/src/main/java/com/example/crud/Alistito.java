package com.example.crud;

import java.util.ArrayList;

public class Alistito {
    ArrayList<Basurita> adaditos = new ArrayList<>();
    public void agregar(int dato){
        String nombres [] = {"Boleros","Corridos","Cumbia","Norteña","Salsa","Indie","Pop","Phonk","J-pop","Hip-hop"};

        if (dato == 1){
            for(int i =0; i < 5; ++i){
                Basurita objetito = new Basurita();
                objetito.setNombre(nombres[i]);
                adaditos.add(objetito);
            }
        } else if (dato == 2){
            for(int i =5; i < 10; ++i){
                Basurita objetito = new Basurita();
                objetito.setNombre(nombres[i]);
                adaditos.add(objetito);
            }
        }
    }
    public ArrayList<Basurita>  regresar(){
        return adaditos;
    }
}
