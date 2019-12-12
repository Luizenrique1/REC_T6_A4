package itsj.e.bd_sqlite_room;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Entidades.Producto;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import bd_room.BodegaBD;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText txtBuscar;
    private Button btnBuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        txtBuscar = findViewById(R.id.txt_buscarC);
        btnBuscar = findViewById(R.id.btn_searchC);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        BodegaBD bd = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();
        ArrayList listaAlumnos = new ArrayList(bd.DAO().getAll());
        mAdapter = new MyAdapter(listaAlumnos);
        recyclerView.setAdapter(mAdapter);


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodegaBD bd2 = Room.databaseBuilder(getApplicationContext(), BodegaBD.class, "Bodega").allowMainThreadQueries().build();

                if (txtBuscar.getText().toString().equals("")){
                    ArrayList listaAlumnos = new ArrayList(bd2.DAO().getAll());
                    mAdapter = new MyAdapter(listaAlumnos);
                    recyclerView.setAdapter(mAdapter);
                }else{
                    String s = txtBuscar.getText().toString();
                    ArrayList listaAlumnos = new ArrayList(bd2.DAO().getAllbuscarAlumno(s));
                    mAdapter = new MyAdapter(listaAlumnos);
                    recyclerView.setAdapter(mAdapter);

                }


            }
        });



    }



}


class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Producto> productos;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public MyAdapter(ArrayList<Producto> listaProductos) {
        productos = listaProductos;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_registros, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(productos.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
}




