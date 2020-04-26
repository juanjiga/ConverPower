package com.juanjiga.converpower.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.juanjiga.converpower.R;

import java.text.DecimalFormat;

public class dBm_Aw extends Fragment {

    public dBm_Aw() {
        // Required empty public constructor
    }

    String datoEntrada="";
    String unidad = " W";
    String signo = "";
    Double numeroEntrada, numeroSalida;
    DecimalFormat formato = new DecimalFormat("0.00");
    TextView texto, entrada, salida;
    Button[] tecla = new Button[12];
    Button tecla_dBm, tecla_borrar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.prueba, container, false);

        texto = (TextView) view.findViewById(R.id.texto);
        entrada = (TextView) view.findViewById(R.id.textoentrada);
        salida = (TextView) view.findViewById(R.id.textosalida);
        tecla[0] = (Button) view.findViewById(R.id.button0);
        tecla[1] = (Button) view.findViewById(R.id.button1);
        tecla[2] = (Button) view.findViewById(R.id.button2);
        tecla[3] = (Button) view.findViewById(R.id.button3);
        tecla[4] = (Button) view.findViewById(R.id.button4);
        tecla[5] = (Button) view.findViewById(R.id.button5);
        tecla[6] = (Button) view.findViewById(R.id.button6);
        tecla[7] = (Button) view.findViewById(R.id.button7);
        tecla[8] = (Button) view.findViewById(R.id.button8);
        tecla[9] = (Button) view.findViewById(R.id.button9);
        tecla[10] = (Button) view.findViewById(R.id.buttonSigno);
        tecla[11] = (Button) view.findViewById(R.id.buttonPunto);

        tecla_dBm=(Button) view.findViewById(R.id.buttondBm);
        tecla_dBm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculo_dBmaW();
            }
        });

        tecla_borrar=(Button) view.findViewById(R.id.buttonBorrar);
        tecla_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borraEntrada();
            }
        });

        for (int i=0; i<12; i++){tecla[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    if (v == tecla[i]) {
                        datoEntrada = datoEntrada + i;
                        numeroEntrada = Double.parseDouble(datoEntrada);
                        imprimEntrada();
                    }
                }
                if (v == tecla[10]){
                    /*numeroEntrada = Double.parseDouble(datoEntrada);

                    if (numeroEntrada > 0) signo = "-";
                    if (numeroEntrada < 0) signo = "+";

                    numeroEntrada = numeroEntrada * (-1);

                    imprimEntrada();*/
                    datoEntrada = "-" + datoEntrada;
                    imprimEntrada();
                }
                if (v == tecla[11]) {
                    datoEntrada = datoEntrada + ".";
                    imprimEntrada();
                }
            }
        });}

        return view;
    }

    void imprimEntrada(){
        entrada.setText(signo + datoEntrada);
        texto.setText("   " + numeroEntrada);
    }

    void borraEntrada(){
        datoEntrada ="";
        signo = "";
        imprimEntrada();
    }

    public void calculo_dBmaW() {
        try {
            //numeroEntrada = Double.parseDouble(datoEntrada);
        } catch (Exception e) {
            numeroEntrada = 0.0;
            Activity activity;
            activity = getActivity();
            Toast toast = Toast.makeText(activity,"eeehhhh!!! debes indicar un número de dBm", Toast.LENGTH_LONG);
            toast.show();
        }
        entrada.setText(""); // borra el número introducido
        numeroSalida = Math.pow(10, (numeroEntrada - 30) / 10); //formula
        cambiaUnidad();
        salida.setText(numeroEntrada + " dBm  " + formato.format(numeroSalida) + unidad);
        borraEntrada();
    }

    public void cambiaUnidad() {
        unidad = " W";
        if (numeroSalida < 1) {
            numeroSalida = numeroSalida * 1000;
            unidad = " mW";
            if (numeroSalida < 1) {
                numeroSalida = numeroSalida * 1000;
                unidad = " uW";
            }
            if (numeroSalida < 1) {
                numeroSalida = numeroSalida * 1000;
                unidad = " nW";
            }
            if (numeroSalida < 1) {
                numeroSalida = numeroSalida * 1000;
                unidad = " pW";
            }

        }
        if (numeroSalida >= 1000){
            numeroSalida = numeroSalida / 1000;
            unidad = " Kw";
            if (numeroSalida >= 1000){
                numeroSalida = numeroSalida / 1000;
                unidad = " Mw";
            }
        }
    }



}
