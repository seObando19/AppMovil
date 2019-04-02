package com.example.proyectoapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class Main2Activity extends AppCompatActivity {
    private EditText lprecio,lnombre,ltipo;
    private Button btn_add;
    private FirebaseFirestore db;
    private Spinner spinner_Tipo;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //referenciando datos
        lprecio=(EditText)findViewById(R.id.txt_precio);
        lnombre=(EditText)findViewById(R.id.txt_nombre);
        spinner_Tipo=(Spinner)findViewById(R.id.spinnerTipo);
        btn_add=(Button)findViewById(R.id.btn_add);
        //Manejo del spinner para escoger productos
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_tipo,android.R.layout.simple_spinner_item);
        spinner_Tipo.setAdapter(adapter);
        //Flecha atras
        ActionBar actionBar=getSupportActionBar();
        if (actionBar !=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }
    }

    public void agregar(View view) {
        // Access a Cloud Firestore instance from your Activity<
        db = FirebaseFirestore.getInstance();
        final String nombre=(lnombre.getText().toString());
        String tipo=(spinner_Tipo.getSelectedItem().toString());
        String precio=(lprecio.getText().toString());
        Map<String,Object> data=new HashMap<>();

        //validacion de que los campos no esten vacios

        if(TextUtils.isEmpty(tipo)||TextUtils.isEmpty(precio)||TextUtils.isEmpty(nombre))
        {
            //Toast.makeText(this, "Ingreso", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Ingreso de datos incorrecto", LENGTH_SHORT).show();
        }else
        {
            //consultando datos
            // Toast.makeText(this, "Ingreso", Toast.LENGTH_SHORT).show();
            final int val=Integer.parseInt(precio);
            data.put("Nombre",nombre);
            data.put("Valor",val);
            data.put("Tipo",tipo);

            db.collection("producto2")
                    .add(data)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //Log.d("veg", "DocumentSnapshot written with ID: " + documentReference.getId());
                            Toast.makeText(Main2Activity.this, "Ingreso exitoso", LENGTH_SHORT).show();
                            lnombre.setText("");
                            lprecio.setText("");
                            /*
                             ****************************************************************************************************************/
                            //Notificaciones para versiones menores a 8.0
                            Intent lo_intent2 = new Intent(Main2Activity.this, Main2Activity.class);
                            lo_intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            PendingIntent lo_pendingIntent = PendingIntent.getActivity(Main2Activity.this, 0, lo_intent2, PendingIntent.FLAG_ONE_SHOT);

                            //El request en 0 permite reemplazar la actividad y que no se acumulen en la main activity.
                            //El flag one shot nos permite que se ejecute una sola vez

                            //Utilizo servicio de la notificación
                            NotificationManager lo_notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            //Ringtone para la notificación - sonido
                            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                            Notification.Builder lo_notificationBuilder = new Notification.Builder(Main2Activity.this)
                                    .setSmallIcon(R.drawable.ic_stat_name)
                                    .setContentTitle("--- New Product right now ---")
                                    .setContentText(nombre + " con valor de " + val + " " + " !APROVECHA¡ :D")
                                    .setAutoCancel(true) // para que se cierre automaticamente al ser tocada por el ususrio
                                    .setSound(defaultSoundUri)
                                    .setContentIntent(lo_pendingIntent);

                            if (lo_notificationManager != null)
                            {
                                lo_notificationManager.notify("",0,lo_notificationBuilder.build());
                            }
                            /*
                             * **************************************************************************************************************/



                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Log.w("veg", "Error adding document", e);
                            Toast.makeText(Main2Activity.this, "Ingreso erroneo",Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    public void regresar(View view) {finish(); }
}
