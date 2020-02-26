package com.example.luiscaguana.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.luiscaguana.apppruebasqlite.db.ContactosPersistencia;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void registar(View view) {
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);

    }



    public void modificar(View view) {
        Intent i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }

    public void Borrar(View view) {
    }

    public void Consulrae(View view) {
    }
}
