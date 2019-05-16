package com.example.sophie.astrodomus.utils;

import java.util.Random;

/**
 * Created by Sophie on 6/05/2019.
 */

public class CodeRandom {

   private String cadena[]={"1", "a", "b", "2", "C", "d", "F", "G","h","i", "J", "K", "3", "4",
    "l", "M", "n", "O", "P", "5", "6", "q", "R", "s", "7", "8", "T", "u", "V", "X", "Y", "W", "Z", "9", "0"};

    public String getCode(){
        String code ="";
        Random ram = new Random();
        for (int i =0; i<4; i++){
            int num = ram.nextInt(cadena.length);
            code += ""+ cadena[num];
        }

        return code;
    }
}
