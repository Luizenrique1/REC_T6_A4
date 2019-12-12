package itsj.e.bd_sqlite_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activityLogin extends AppCompatActivity {
        EditText txtUsuario, txtContra;
        Button btnInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnInicio= findViewById(R.id.btn_login);
        txtContra= findViewById(R.id.txt_contra);
        txtUsuario= findViewById(R.id.txt_usuario);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUsuario.getText().toString().equals("admin") && txtContra.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Redireccionando...", Toast.LENGTH_SHORT).show();
                    inicioSession();
                }else{
                    txtUsuario.setError("Ususario Incorecto");
                    txtContra.setError("Contraseña incorecta");
                    Toast.makeText(getApplicationContext(), "Usuario o Contraseña Incorectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void inicioSession() {
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);

    }
}
