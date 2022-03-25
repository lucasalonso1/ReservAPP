package br.edu.ifsp.ctd.reservapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.ctd.reservapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CICLO_ACTIVITY = "CICLO_ACTIVITY";
    // criando o objeto viewbinding da activity (layout -> xml)
    ActivityMainBinding activityMainBinding;
    public static final String SIZE = "br.edu.ifsp.primeiroapp.SIZE";
    public static final String QTDFLAVOR = "br.edu.ifsp.primeiroapp.QTDFLAVOR";
    public static final String FRISTFLAVOR = "br.edu.ifsp.primeiroapp.FRISTFLAVOR";
    public static final String SECONDFLAVOR = "br.edu.ifsp.primeiroapp.SECONDFLAVOR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        //importante: Setar o conteudo do acivityMainBinding
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.btnAdd.setOnClickListener(this);
        activityMainBinding.btnReclamar.setOnClickListener(this);
        Log.v(CICLO_ACTIVITY, "OnCreate: Iniciando Ciclo de Vida");

        RadioGroup s = activityMainBinding.rgQtd;

        s.setOnCheckedChangeListener((radioGroup, i) -> {
            boolean two = activityMainBinding.rbTwo.isChecked();
            if (two) {
                activityMainBinding.spnSecond.setVisibility(View.VISIBLE);
                activityMainBinding.tvSecond.setVisibility(View.VISIBLE);
            }else{
                activityMainBinding.spnSecond.setVisibility(View.INVISIBLE);
                activityMainBinding.tvSecond.setVisibility(View.INVISIBLE);
            }

        });

    }




    @Override
    public void onClick(View view) {
        //Toast.makeText(this, view.getId() ,Toast.LENGTH_LONG).show();
        //Intent intent =new Intent(this, SecondAtivity.class);
        //startActivity(intent);
        if(view.getId() ==  R.id.btnAdd){
            //funções do botao Realizar Pedido
            String size = ((TextView)activityMainBinding.spnSize.getSelectedView()).getText().toString();
            boolean one = activityMainBinding.rbOne.isChecked();
            String qtdFlavor = one ? "1" : "2";
            String fristFlavor ="";
            String secondFlavor ="";

            if (one) {
                fristFlavor = ((TextView) activityMainBinding.spnFrist.getSelectedView()).getText().toString();
                //Toast.makeText(this, size + "\n" + qtdFlavor + "\n" + fristFlavor, Toast.LENGTH_LONG).show();
            }else{
                fristFlavor = ((TextView) activityMainBinding.spnFrist.getSelectedView()).getText().toString();
                secondFlavor = ((TextView) activityMainBinding.spnSecond.getSelectedView()).getText().toString();
                //Toast.makeText(this, size + "\n" + qtdFlavor + "\n" + fristFlavor+ "\n" + secondFlavor, Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(this, OrderResume.class);
            intent.putExtra(SIZE,size);
            intent.putExtra(QTDFLAVOR,qtdFlavor);
            intent.putExtra(FRISTFLAVOR,fristFlavor);
            intent.putExtra(SECONDFLAVOR,secondFlavor);
            startActivity(intent);
        } if(view.getId() ==  R.id.btnReclamar){
            //Toast.makeText(this, "Reclamei", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, SecondAtivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(CICLO_ACTIVITY, "OnStar: Iniciando Ciclo de Vida - Visível");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(CICLO_ACTIVITY, "OnResume: Iniciando Ciclo de Vida - Primeiro Plano");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(CICLO_ACTIVITY, "OnPause: Finalizando Ciclo de Vida - Primeiro Plano");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(CICLO_ACTIVITY, "OnStop: Finalizando Ciclo de Vida - Visível");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(CICLO_ACTIVITY, "OnRestart: Preparando a execução do restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(CICLO_ACTIVITY, "OnDestroy: Finalizando o ciclo de vida completo");
    }
}