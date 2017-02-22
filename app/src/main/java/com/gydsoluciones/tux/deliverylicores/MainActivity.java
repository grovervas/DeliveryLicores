package com.gydsoluciones.tux.deliverylicores;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario, etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("WPL1Q4rz1Wx8QaOMuqam6GFJ6hQX4o8meePfOMvQ")
                .clientKey("Yw0c9L2sku7E8uuQKC8svXlvmNcngwVIljOl6UoZ")
                .server("https://parseapi.back4app.com/").build()
        );

        etUsuario = (EditText)findViewById(R.id.etUser);
        etClave = (EditText)findViewById(R.id.etClave);
    }

    public void Registrar(View view)
    {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    public void IniciarSesion(View view)
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        String usuario = etUsuario.getText().toString();
        String clave = etClave.getText().toString();
        dialog.setMessage("Iniciando sesión");
        dialog.show();
        ParseUser.logInInBackground(usuario, clave, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if(parseUser != null){
                    IniciarApp();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Datos incorrectos",Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }

    private void IniciarApp()
    {
        String usuario = etUsuario.getText().toString();
        Intent intent = new Intent(this,PrincipalActivity.class);
        intent.putExtra("USUARIO", usuario);
        startActivity(intent);
    }
}
