package com.example.codescanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.codescanner.http.API;
import com.example.codescanner.http.RetrofitService;
import com.example.codescanner.model.Apreensao;
import com.example.codescanner.model.Auxiliar;
import com.example.codescanner.model.ValueScanned;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scaneando...");
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        RetrofitService retrofitService = new RetrofitService();
        API api = retrofitService.getRetrofit().create(API.class);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                Log.e("Valor: ", result.getContents());
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Resultado QRCode");
                builder.setPositiveButton("Scanear novamente", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        scanCode();
                    }
                }).setNegativeButton("Concluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        ValueScanned valueScanned = new ValueScanned();
                        valueScanned.setValueScanned(result.getContents());

                        api.recebeId(valueScanned).enqueue(new Callback<Apreensao>() {
                            @Override
                            public void onResponse(Call<Apreensao> call, Response<Apreensao> response) {
                                Apreensao apreensao = response.body();
                                api.recebeItens().enqueue(new Callback<String[]>() {
                                    @Override
                                    public void onResponse(Call<String[]> call, Response<String[]> response2) {
                                        Auxiliar auxiliar = new Auxiliar();
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                        String data = simpleDateFormat.format(apreensao.getData()).toString();
                                        auxiliar.setData(data);
                                        Log.e("ladata: ", auxiliar.getData());
                                        auxiliar.setNomeItens(response2.body());
                                        Intent intent = new Intent(MainActivity.this,
                                                MostrarApreensao.class);
                                        intent.putExtra("apreensao", apreensao);
                                        intent.putExtra("auxiliar", auxiliar);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<String[]> call, Throwable t) {
                                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Erro no nomeItens: ", t);
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<Apreensao> call, Throwable t) {
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Erro: ", t);
                            }
                        });
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                Toast.makeText(this, "Sem resultados", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}