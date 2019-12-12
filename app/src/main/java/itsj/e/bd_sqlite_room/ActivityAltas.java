package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Entidades.Producto;
import androidx.room.Room;

import bd_room.BodegaBD;

public class ActivityAltas extends Activity {

    Spinner Tipo;
    EditText txtIdProducto, txtNombre, txtCantidad, txtPrecio;
    Button botonAñadir, botonCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);


        Tipo = findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapterEdad = ArrayAdapter.createFromResource(this, R.array.Tipo_de_Producto, android.R.layout.simple_spinner_item);
        adapterEdad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Tipo.setAdapter(adapterEdad);



        txtIdProducto = findViewById(R.id.txtNumControl);
        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txt_cantidadP);
        txtPrecio = findViewById(R.id.txt_precioP);

        botonAñadir = findViewById(R.id.botonAñadir);
        botonCancelar = findViewById(R.id.botonCancelar);

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtIdProducto.getText().equals("") || txtNombre.getText().toString().equals("") || txtPrecio.getText().toString().equals("") || txtCantidad.getText().equals("") || Tipo.getSelectedItemPosition()==0){
                    if (txtIdProducto.length()==0){ txtIdProducto.setError("Campo Vacio"); }
                    if (txtNombre.length()==0){ txtNombre.setError("Campo Vacio"); }
                    if (txtPrecio.length()==0){ txtPrecio.setError("Campo Vacio"); }
                    if (txtCantidad.length()==0){ txtCantidad.setError("Campo Vacio"); }
                    if (Tipo.getSelectedItemPosition()==0){((TextView)Tipo.getSelectedView()).setError("Tipo Producto Invalido");}
                }else {
                    BodegaBD bd = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();
                    Producto a = new Producto(txtIdProducto.getText().toString(), txtNombre.getText().toString(), Tipo.getSelectedItem().toString(), Integer.parseInt(txtCantidad.getText().toString()), txtPrecio.getText().toString());

                    try {
                        bd.DAO().insertAll(a);
                        List<Producto> listaProductos = bd.DAO().getAll();
                        for (Producto al : listaProductos) {
                            if (al.getIDproducto().equals(a.getIDproducto())) {
                                Toast.makeText(getApplicationContext(), "Se añadio un registro", Toast.LENGTH_SHORT).show();
                                txtIdProducto.setText("");
                                txtNombre.setText("");
                                Tipo.setSelection(0);
                                txtCantidad.setText("");
                                txtPrecio.setText("");
                                Log.i("producto -->", al.toString());
                            } else {
                                Toast.makeText(getApplicationContext(), "No se pudo añadir el registro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Id ya existe", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}

