package com.example.codescanner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codescanner.model.Apreensao;
import com.example.codescanner.model.Auxiliar;

import java.util.Date;

public class MostrarApreensao extends AppCompatActivity{

    TextView apreensaoId, apreensaoIdValue, apreensaoData, apreensaoDataValue, apreensaoDescricao, apreensaoDescricaoValue, apreensaoProdutos;
    Button button;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_apreensao);

        apreensaoId = findViewById(R.id.apreensaoId);
        apreensaoIdValue = findViewById(R.id.apreensaoIdValue);
        apreensaoData = findViewById(R.id.apreensaoData);
        apreensaoDataValue = findViewById(R.id.apreensaoDataValue);
        apreensaoDescricao = findViewById(R.id.apreensaoDescricao);
        apreensaoDescricaoValue = findViewById(R.id.apreensaoDescricaoValue);
        listView = findViewById(R.id.listView);

        Apreensao apreensao = getIntent().getExtras().getParcelable("apreensao");
        Auxiliar auxiliar = getIntent().getExtras().getParcelable("auxiliar");
        apreensaoIdValue.setText(String.valueOf(apreensao.getId()));
        apreensaoDescricaoValue.setText(apreensao.getDescricao());
        apreensaoDataValue.setText(auxiliar.getData());


        if(auxiliar.getNomeItens() != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, auxiliar.getNomeItens());
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(this, "Não há produtos vinculados!", Toast.LENGTH_SHORT).show();
        }
    }
}