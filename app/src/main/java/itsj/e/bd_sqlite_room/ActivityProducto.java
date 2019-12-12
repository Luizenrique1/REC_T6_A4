package itsj.e.bd_sqlite_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_producto);
    }

    public void abrirActivities(View v) {
        switch (v.getId()) {

            case R.id.btn_AgregarPr:
                Intent a = new Intent(this, ActivityAltas.class);
                startActivity(a);
                break;
            case R.id.btn_EliminarPr:
                Intent b = new Intent(this, ActivityBajas.class);
                startActivity(b);
                break;
            case R.id.btn_ModificarPr:
                Intent c = new Intent(this, ActivityCambios.class);
                startActivity(c);
                break;
            case R.id.btn_consultasPr:
                Intent d = new Intent(this, ActivityConsultas.class);
                startActivity(d);
                break;

        }
    }
}
