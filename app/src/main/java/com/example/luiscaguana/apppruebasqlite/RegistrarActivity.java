package com.example.luiscaguana.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luiscaguana.apppruebasqlite.db.ContactosPersistencia;
import com.example.luiscaguana.apppruebasqlite.moden.Contacto;

public class RegistrarActivity extends AppCompatActivity {


    EditText etNombre;
    EditText etEmail;
    //conexion con la base de datos
    ContactosPersistencia cp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        etNombre = findViewById(R.id.etNombreR);
        etEmail = findViewById(R.id.etEmailR);

        cp = new ContactosPersistencia(this);

    }

    public void GuardarR(View view) {
        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if(nombre.isEmpty() || email.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else{
            Contacto contacto = new Contacto(nombre,email);
            Long id = cp.insertarContactos(contacto);

            if (id != -1){
                Toast.makeText(this, getString(R.string.insertat_ok),
                Toast.LENGTH_LONG).show();
                contacto.setId(id);
            }else {
                Toast.makeText(this, getString(R.string.insertat_ok_no),
                        Toast.LENGTH_LONG).show();
            }

        }
    }

    public void LimpiarR(View view) {
        etNombre.setText("");
        etEmail.setText("");
    }
}
