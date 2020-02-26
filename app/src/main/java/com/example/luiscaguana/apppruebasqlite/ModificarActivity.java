package com.example.luiscaguana.apppruebasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luiscaguana.apppruebasqlite.db.ContactosPersistencia;
import com.example.luiscaguana.apppruebasqlite.moden.Contacto;

import java.net.IDN;

public class ModificarActivity extends AppCompatActivity {


    EditText etId;
    EditText etEmail;
    EditText etNombre;

    ContactosPersistencia cp;

    Button btnBuscar;
    Button btnGuardar;
    Button btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etId = findViewById(R.id.etIdM);
        etEmail = findViewById(R.id.etEmailM);
        etNombre = findViewById(R.id.etNombreM);
        cp = new ContactosPersistencia(this);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnGuardar = findViewById(R.id.btnGuardarM);
        btnLimpiar = findViewById(R.id.btnCancelarM);
    }

    public void GuardarM(View view) {
        int id = Integer.parseInt(etId.getText().toString());

        String nombre = etNombre.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if(nombre.isEmpty() || email.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else{
            Contacto contacto = new Contacto(nombre,email);
            contacto.setId(id);
             cp.actualizar(contacto);
                Toast.makeText(this, getString(R.string.update_ok),
                        Toast.LENGTH_LONG).show();


        }



    }

    public void LimpiarM(View view) {
        etId.setText("");
        etEmail.setText("");
        etNombre.setText("");

        habilitar(true);

    }

    private void habilitar(boolean b) {

        etNombre.setEnabled(b);
        etEmail.setEnabled(b);
        etId.setEnabled(b);

        btnLimpiar.setEnabled(b);
        btnGuardar.setEnabled(b);
        btnBuscar.setEnabled(b);
    }

    public void BuscarM(View view) {
        String Sid = etId.getText().toString().trim();

        if (Sid.isEmpty()){
            Toast.makeText(this, (R.string.no_id),
                    Toast.LENGTH_LONG).show();

        }else{
            //Convertirlo a entero
            int Id = Integer.parseInt(Sid);
            Contacto contacto= cp.leercontacot(Id);

            if(contacto !=null) {

                etNombre.setText(contacto.getNombre());
                etEmail.setText(contacto.getEmail());

                habilitar(true);


            } else{
                Toast.makeText(this, (R.string.no_conta),
                        Toast.LENGTH_LONG).show();

            }

        }

    }
}
