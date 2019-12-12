package itsj.e.bd_sqlite_room;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirActivities(View v) {
        switch (v.getId()) {

            case R.id.btn_productomenu:
                Intent a = new Intent(this, ActivityProducto.class);
                startActivity(a);
                break;
            case R.id.btn_pedidomenu:
                Intent b = new Intent(this, ActivityBajas.class);
                startActivity(b);
                break;
        }
    }

}
