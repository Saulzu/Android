package com.example.practk;

import java.util.ArrayList;

public class Alistito {
    ArrayList<Basurita> adaditos = new ArrayList<>();

    public void agregar(int dato){
        int claves[] = {1,2,3,4,5,6,7,8,9,10};
        String nombres [] = {"lapiz","goma","bicolor","lapicero","pluma","computadora","impresora","mouse","monitor","memoria"};
        int costos[] = {10,5,10,100,10,2000,30,200,4000,100};

        if (dato == 1){
            for(int i =0; i < 5; ++i){
                Basurita objetito = new Basurita();
                objetito.setClave(claves[i]);
                objetito.setNombre(nombres[i]);
                objetito.setCosto(costos[i]);
                adaditos.add(objetito);
            }
        } else if (dato == 2){
            for(int i =5; i < 10; ++i){
                Basurita objetito = new Basurita();
                objetito.setClave(claves[i]);
                objetito.setNombre(nombres[i]);
                objetito.setCosto(costos[i]);
                adaditos.add(objetito);
            }
        }

    }

    public ArrayList<Basurita>  regresar(){
        return adaditos;
    }
}
