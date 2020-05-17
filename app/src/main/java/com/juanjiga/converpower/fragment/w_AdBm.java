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

public class w_AdBm extends Fragment {

    public w_AdBm() {
        // Required empty public constructor
    }

    String datoEntrada = "";
    int digito = 0;
    double numeroEntrada = 0;
    double numeroSalida = 0;
    DecimalFormat formato = new DecimalFormat("0.00");
    TextView entrada, salida;
    Button[] boton = new Button[18];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w_adbm, container, false);

        entrada=(TextView) view.findViewById(R.id.entrada_w);
        salida=(TextView) view.findViewById(R.id.salida_dbm);
        boton[0]=(Button) view.findViewById(R.id.button_0);
        boton[1]=(Button) view.findViewById(R.id.button_1);
        boton[2]=(Button) view.findViewById(R.id.button_2);
        boton[3]=(Button) view.findViewById(R.id.button_3);
        boton[4]=(Button) view.findViewById(R.id.button_4);
        boton[5]=(Button) view.findViewById(R.id.button_5);
        boton[6]=(Button) view.findViewById(R.id.button_6);
        boton[7]=(Button) view.findViewById(R.id.button_7);
        boton[8]=(Button) view.findViewById(R.id.button_8);
        boton[9]=(Button) view.findViewById(R.id.button_9);
        boton[10]=(Button) view.findViewById(R.id.button_punto);
        boton[11]=(Button) view.findViewById(R.id.button_borrar);
        boton[12]=(Button) view.findViewById(R.id.button_pico);
        boton[13]=(Button) view.findViewById(R.id.button_nano);
        boton[14]=(Button) view.findViewById(R.id.button_micro);
        boton[15]=(Button) view.findViewById(R.id.button_mili);
        boton[16]=(Button) view.findViewById(R.id.button_watio);
        boton[17]=(Button) view.findViewById(R.id.button_kilo);

        for (int i=0; i<18; i++){boton[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 10; i++) {              //teclas del 0 al 9
                    if (v == boton[i]) {
                        digito ++;
                        if (digito < 5){
                        datoEntrada = datoEntrada + i;
                        imprime();
                        }
                        else mensaje("Debes indicar la unidad");
                    }
                }
                if (v == boton[10]){                        //tecla del punto
                    if (datoEntrada.indexOf('.') == -1)
                    datoEntrada = datoEntrada + ".";
                    imprime();
                }
                if (v == boton[11]){                        //tecla borrar
                    salida.setText("");
                    datoEntrada = "";
                    imprime();
                    digito = 0;
                }                                           //teclas unidades
                if (v == boton[12])
                    calculador(0.000000001," pW");
                if (v == boton[13])
                    calculador(0.000001," nW");
                if (v == boton[14])
                    calculador(0.001," uW");
                if (v == boton[15])
                    calculador(1, " mW");
                if (v == boton[16])
                    calculador(1000, " W");
                if (v == boton[17])
                    calculador(1000000, " Kw");
            }
        });}
    return view;
    }

    public void imprime(){
        entrada.setText(datoEntrada);
    }

    public void calculador(double adaptador, String unidad){
        try {
            double numero = Double.parseDouble(datoEntrada);                     // entrada sin modificar para luego mostrar
            numeroEntrada = Double.parseDouble(datoEntrada) * adaptador;         // entrada adaptada a la formula
            numeroSalida = 10 * (Math.log10(numeroEntrada));                     // formula w a dBm
            entrada.setText(numero + unidad);                                    // número introducido mas unidad
            salida.setText(formato.format(numeroSalida) + " dBm");               // resultado de la formula
            digito = 0;
        } catch (Exception e){
            mensaje("Indica los Watios");
        }
    }

    public void mensaje (String mensaje){
        Activity activity;
        activity = getActivity();
        Toast toast = Toast.makeText(activity, mensaje, Toast.LENGTH_SHORT);
        toast.show();
    }

}
