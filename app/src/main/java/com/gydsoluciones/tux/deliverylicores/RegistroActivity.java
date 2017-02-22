package com.gydsoluciones.tux.deliverylicores;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.SimpleDateFormat;

public class RegistroActivity extends AppCompatActivity {

    private EditText etEmail, etContra, etNombre, etApellido, etMovil, etNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etContra = (EditText)findViewById(R.id.etContra);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etApellidos);
        etMovil = (EditText)findViewById(R.id.etMovil);
        etNacimiento = (EditText)findViewById(R.id.etNacimiento);
    }

    public void RegistrarUsuario(View view)
    {
        final ProgressDialog dialogo;
        ParseUser user = new ParseUser();
        user.setUsername(etEmail.getText().toString());
        user.setPassword(etContra.getText().toString());
        user.setEmail(etEmail.getText().toString());

        user.put("nombre",etNombre.getText().toString());
        user.put("apellido",etApellido.getText().toString());
        user.put("movil",etMovil.getText().toString());

        try {
            SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
            user.put("nacimiento", fecha.parse(etNacimiento.getText().toString()));
        }catch(java.text.ParseException e)
        {
            e.printStackTrace();
        }
        dialogo = new ProgressDialog(this);
        dialogo.setMessage("Procesando...");
        dialogo.show();

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                {
                    Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No se pudo registrar, verifique su conexión a Internet",Toast.LENGTH_LONG).show();
                }
                dialogo.dismiss();
            }
        });
    }
}
