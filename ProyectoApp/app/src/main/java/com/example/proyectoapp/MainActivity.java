package com.example.proyectoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private EditText txtUser,txtPass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //referenciando los datos
        txtUser=findViewById(R.id.txtUser);
        txtPass=findViewById(R.id.txtPass);
        btnLogin=findViewById(R.id.btnLogin);
    }

    public void Login(View view) {
        //comvirtiendo en string los datos para validar si estan vacios
        String user=txtUser.getText().toString();
        String pass=txtPass.getText().toString();
        if(!(TextUtils.isEmpty(user)&&TextUtils.isEmpty(pass)))
        {
            if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
            {
                Toast.makeText(getApplicationContext(),"Inicio de sesion incorrecto",Toast.LENGTH_SHORT).show();

            }else
            {
                //validar si es administrador
                if(user.equals("admin")&&pass.equals("admin"))
                {
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    FirebaseMessaging.getInstance().subscribeToTopic("hombres");        //es como para validartodo en la base datos que es para hombres yo mujeres
                    startActivity(intent);
                    txtUser.setText("");
                    txtPass.setText("");
                }else
                {
                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                }
            }
        }else
        {
            Toast.makeText(this,"Inicio de sesion incorrecto",Toast.LENGTH_SHORT).show();
        }
    }
}
