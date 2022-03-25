package br.edu.ifsp.ctd.reservapp;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_TEXT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.edu.ifsp.ctd.reservapp.databinding.ActivitySecondAtivityBinding;


public class SecondAtivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySecondAtivityBinding activitySecondAtivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_ativity);
        activitySecondAtivityBinding = ActivitySecondAtivityBinding.inflate(getLayoutInflater());
        setContentView(activitySecondAtivityBinding.getRoot());
        activitySecondAtivityBinding.btnWhats.setOnClickListener(this);
        activitySecondAtivityBinding.btnClose.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnWhats) {
            String reclamacao = activitySecondAtivityBinding.edDescriRecla.getText().toString();
            whatsapp(activitySecondAtivityBinding.edDescriRecla.getText().toString());
            //Toast.makeText(this,"TESTE BOTAO", Toast.LENGTH_LONG).show();
        }if(view.getId() ==  R.id.btnClose){
            finish();
        }
    }

    private void whatsapp(String msg) {
        Intent intent = new Intent(ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.putExtra(EXTRA_TEXT, msg);

        String reclamacao = activitySecondAtivityBinding.edDescriRecla.getText().toString();
        //Toast.makeText(this, reclamacao, Toast.LENGTH_LONG).show();
        //Verificando se o Whatsapp Esta instalado
        //Verificando se algum App atende a ação da intent
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Por Favor, instale o Whatsapp" + "\n" +
                    "A Mensagem Não Pode Ser Entregue" + "\n" +
                    reclamacao, Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }
}