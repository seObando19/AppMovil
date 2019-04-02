package com.example.proyectoapp;


import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

import static android.widget.Toast.LENGTH_SHORT;

public class Main4Activity extends AppCompatActivity {
    private FirebaseFirestore db;
    ArrayList<String> listDatos;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //referenciando datos
        recycler = (RecyclerView) findViewById(R.id.recyclerid);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listDatos = new ArrayList<String>();
        db = FirebaseFirestore.getInstance();
        //flecha atras
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }
        //obteniendo datos los el metodo Intent
        Bundle datos = this.getIntent().getExtras();
        int result = datos.getInt("res");
        String Tip = datos.getString("mtipo");
        int resd = datos.getInt("des");
        int resh = datos.getInt("has");

        //Validaciones pasa saber que boton oprimio la persona
        if (result == 1) {
            db.collection("producto2")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                listDatos = new ArrayList<String>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("SOP", document.getId() + " => " + document.getData());
                                    listDatos.add(document.getData().toString());
                                    AdapterDatos adapter = new AdapterDatos(listDatos);
                                    recycler.setAdapter(adapter);
                                }
                            } else {
                                Log.d("SOP", "Error getting documents: ", task.getException());
                                Toast.makeText(Main4Activity.this, "Ingreso de datos incorrecto", LENGTH_SHORT).show();

                            }
                        }
                    });

        }
        if (result == 2) {
            db.collection("producto2")
                    .whereEqualTo("Tipo", Tip)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                listDatos = new ArrayList<String>();
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("SOP", document.getId() + " => " + document.getData());
                                    listDatos.add(document.getData().toString());
                                    AdapterDatos adapter = new AdapterDatos(listDatos);
                                    recycler.setAdapter(adapter);
                                }
                            } else {
                                Log.d("SOP", "Error getting documents: ", task.getException());
                                Toast.makeText(Main4Activity.this, "Ingreso de datos incorrecto", LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        if (result == 3) {

                db.collection("producto2")
                        .whereGreaterThanOrEqualTo("Valor", resd)
                        .whereLessThanOrEqualTo("Valor", resh)
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value,
                                                @Nullable FirebaseFirestoreException e)
                            {
                                if (e != null) {
                                    Log.w("veg", "Listen failed.", e);
                                    return;
                                }
                                listDatos = new ArrayList<String>();
                                for (QueryDocumentSnapshot doc : value )
                                {
                                    if (doc.get("Nombre") != null || doc.get("Valor") != null)
                                    {
                                        Log.d("SOP", doc.getId() + " => " + doc.getData());
                                        listDatos.add(doc.getData().toString());
                                        AdapterDatos adapter = new AdapterDatos(listDatos);
                                        recycler.setAdapter(adapter);

                                        Toast.makeText(Main4Activity.this, "Registros encontrados", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(Main4Activity.this, "Consulta erronea", LENGTH_SHORT).show();
                                    }
                                }
                            }


                        });
        }
    }
}
