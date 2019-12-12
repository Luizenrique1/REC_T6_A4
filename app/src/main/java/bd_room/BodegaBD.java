package bd_room;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Controlador.DAO;
import Entidades.Producto;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Producto.class}, version = 1, exportSchema = false)
public abstract class BodegaBD extends RoomDatabase {
    public abstract DAO DAO();

    private static volatile BodegaBD INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BodegaBD getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BodegaBD.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BodegaBD.class, "Bodega").build();
                }
            }
        }
        return INSTANCE;
    }


}
