package pl.zabrze.zs10.timery3p2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int sekundy;
    TextView textViewAktualnyCzas;
    Button buttonZapisz;
    Button buttonStart;
    Button buttonStop;
    Button buttonReset;
    boolean czyWystartowal=false;
    TextView textViewZapisaneCzasy;
    String czasDoZapisu = "Zapisano:\n";
    String czas ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewAktualnyCzas = findViewById(R.id.textViewczasAktualny);
        idzieCzas();
        buttonZapisz = findViewById(R.id.buttonZapisz);
        textViewZapisaneCzasy = findViewById(R.id.textViewZapisaneCzasy);
        buttonZapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czasDoZapisu+= czas+"\n";
                        textViewZapisaneCzasy.setText(czasDoZapisu);
                    }
                }
        );
        buttonStart  = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyWystartowal = true;
                    }
                }
        );
        buttonStop = findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyWystartowal = false;
                    }
                }
        );
        buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sekundy = 0;
                        wyswietlCzas(sekundy);
                    }
                }
        );


    }


    private void idzieCzas(){
        Handler handler = new Handler();
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        if(czyWystartowal) {
                            sekundy++;
                        }
                       // textViewAktualnyCzas.setText(String.valueOf(sekundy));
                        wyswietlCzas(sekundy);
                        handler.postDelayed(this,1000);
                    }
                }
        );
    }
    private void wyswietlCzas(int sekundy){
        int sekundy60 = sekundy%60;
        int godziny = sekundy/3600;
        int minuty60 = (sekundy - godziny*3600)/60;
        czas = String.format("%02d:%02d:%02d",godziny,minuty60,sekundy60) ;
        textViewAktualnyCzas.setText(String.format("%02d:%02d:%02d",godziny,minuty60,sekundy60));
    }
}