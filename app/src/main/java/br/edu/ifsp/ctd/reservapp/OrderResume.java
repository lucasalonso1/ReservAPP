package br.edu.ifsp.ctd.reservapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import br.edu.ifsp.ctd.reservapp.databinding.ActivityMainBinding;
import br.edu.ifsp.ctd.reservapp.databinding.ActivityOrderResumeBinding;

public class OrderResume extends AppCompatActivity {
    ActivityOrderResumeBinding activityOrderResumeBinding;
    public static String MESSAGETOSEND = "br.edu.ifsp.primeiroapp.MESSAGETOSEND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_order_resume);
        activityOrderResumeBinding = ActivityOrderResumeBinding.inflate(getLayoutInflater());
        setContentView(activityOrderResumeBinding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            String size = intent.getStringExtra(MainActivity.SIZE);
            String qtdFlavor = intent.getStringExtra(MainActivity.QTDFLAVOR);
            String fristFlavor = intent.getStringExtra(MainActivity.FRISTFLAVOR);
            String secondFlavor = intent.getStringExtra(MainActivity.SECONDFLAVOR);
            String messageToSend;
            //activityOrderResumeBinding.etMessage.setText(size+qtdFlavor+fristFlavor+secondFlavor);

            if (qtdFlavor.equals("2")) {
                //Toast.makeText(this, Locale.getDefault().getLanguage(), Toast.LENGTH_LONG).show();
                if (Locale.getDefault().getLanguage() == "en"){
                    messageToSend = "Hello, I'll have a " + size
                            + " pizza with " + qtdFlavor + " flavors, of which frist half " + fristFlavor +
                            " and the other " + secondFlavor + ". I'll take out in a little while! Tanks.";
                }else {
                    messageToSend = "Olá, Gostaria de pedir um pizza tamanho " + size
                            + " com " + qtdFlavor + " sabores, sendo metade " + fristFlavor +
                            " e metade " + secondFlavor + ". Passo daqui a pouco para buscar! Obrigado.";
                }
                activityOrderResumeBinding.etMessage.setText(messageToSend);
                MESSAGETOSEND = messageToSend;

                /*Toast.makeText(this, "Olá, Gostaria de pedir um pizza tamanho " + size
                        + " com " + qtdFlavor + " sabores, sendo metade " + fristFlavor +
                        " e metade " + secondFlavor + ". Passo daqui a pouco para buscar. Obrigado."
                        , Toast.LENGTH_LONG).show();*/
            } else {
                if (Locale.getDefault().getLanguage() == "en"){
                    messageToSend = "Hello, I'll have a "
                            + size + " pizza " + fristFlavor
                            + " flavor. I'll take out in a little while! Tanks.";
                }else{
                    messageToSend = "Olá, Gostaria de pedir um pizza tamanho "
                            + size + " sabor " + fristFlavor
                            + ". Passo daqui a pouco para buscar! Obrigado.";
                }
                activityOrderResumeBinding.etMessage.setText(messageToSend);
                MESSAGETOSEND = messageToSend;

                /*Toast.makeText(this, "Olá, Gostaria de pedir um pizza tamanho "
                        + size + " sabor " + fristFlavor
                        + ". Passo daqui a pouco para buscar. Obrigado.", Toast.LENGTH_LONG).show();*/
            }
        }
        activityOrderResumeBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnSend) {
                    whatsapp(MESSAGETOSEND);
                }
            }
        });
        activityOrderResumeBinding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (view.getId() == R.id.btnClose) {
                    finish();
                 }
            }
        });
    }

    private void whatsapp(String wMsg) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");
        intent.putExtra(Intent.EXTRA_TEXT, wMsg);
        //Toast.makeText(this, wMsg, Toast.LENGTH_LONG).show();
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this, "Instale o Wpp", Toast.LENGTH_LONG).show();
        } else {
            startActivity(intent);
        }
    }
}