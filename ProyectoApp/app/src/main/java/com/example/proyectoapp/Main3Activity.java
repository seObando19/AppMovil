package com.example.proyectoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Main3Activity extends AppCompatActivity {
    private EditText des,has;
    private Spinner spinner_Tipo;
    private int N=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //referenciando datos
        des=(EditText)findViewById(R.id.desde);
        has=(EditText)findViewById(R.id.Hasta);
        spinner_Tipo=(Spinner)findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_tipo,android.R.layout.simple_spinner_item);
        spinner_Tipo.setAdapter(adapter);
        //flecha atras
        ActionBar actionBar=getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }
    }
    //Botones

    public void productos(View view) {
        N=1;
        Intent intent=new Intent(Main3Activity.this,Main4Activity.class);
        intent.putExtra("res",N);
        startActivity(intent);

    }

    public void tipos(View view) {
        String val=(spinner_Tipo.getSelectedItem().toString());
        if (!(TextUtils.isEmpty(val))) {
            N = 2;
            Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
            intent.putExtra("res", N);
            intent.putExtra("mtipo", val);
            startActivity(intent);
        }else
        {
            Toast.makeText(this,"Seleccione alguna opcion",Toast.LENGTH_SHORT).show();
        }

    }

    public void precios(View view) {
        N=3;
        String d=des.getText().toString();
        String h=has.getText().toString();
            if(!(TextUtils.isEmpty(d)&&TextUtils.isEmpty(h)))
            {
                int mhas,mdes;
                mdes=Integer.parseInt(d);
                mhas=Integer.parseInt(h);
                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                intent.putExtra("res", N);
                intent.putExtra("des",mdes);
                intent.putExtra("has",mhas);
                startActivity(intent);
            }else
            {
                Toast.makeText(this,"Ingreso incorrecto",Toast.LENGTH_SHORT).show();
            }
    }
}
