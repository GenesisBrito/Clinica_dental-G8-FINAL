package catolica.edu.clinica_dental_g8_final.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "clinica-dent.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creación de las tablas
        db.execSQL("CREATE TABLE citas (" +
                "id INTEGER PRIMARY KEY," +
                "fecha DATE NOT NULL," +
                "hora TIME NOT NULL," +
                "id_dentista INTEGER NOT NULL," +
                "id_paciente INTEGER NOT NULL," +
                "descripcion TEXT DEFAULT NULL)");

        db.execSQL("CREATE TABLE facturas (" +
                "id INTEGER PRIMARY KEY," +
                "id_cita INTEGER NOT NULL," +
                "monto DECIMAL(10,2) NOT NULL," +
                "fecha_emision DATE NOT NULL)");

        db.execSQL("CREATE TABLE mensajes_chat (" +
                "id INTEGER PRIMARY KEY," +
                "remitente VARCHAR(50) DEFAULT NULL," +
                "receptor VARCHAR(50) DEFAULT NULL," +
                "mensaje TEXT DEFAULT NULL," +
                "fecha_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");

        db.execSQL("CREATE TABLE notas_calendario (" +
                "id INTEGER PRIMARY KEY," +
                "fecha DATE NOT NULL," +
                "titulo VARCHAR(255) DEFAULT NULL," +
                "contenido TEXT DEFAULT NULL)");

        db.execSQL("CREATE TABLE pacientes (" +
                "id INTEGER PRIMARY KEY," +
                "nombre VARCHAR(100) NOT NULL," +
                "edad INTEGER DEFAULT NULL," +
                "direccion VARCHAR(255) DEFAULT NULL)");

        db.execSQL("CREATE TABLE registro_medico (" +
                "id INTEGER PRIMARY KEY," +
                "id_paciente INTEGER NOT NULL," +
                "fecha DATE NOT NULL," +
                "descripcion TEXT DEFAULT NULL," +
                "medicamentos TEXT DEFAULT NULL)");

        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY," +
                "nombre VARCHAR(100) NOT NULL," +
                "rol VARCHAR(50) NOT NULL," +
                "usuario VARCHAR(50) NOT NULL," +
                "contrasena VARCHAR(255) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar las tablas si existen al actualizar la versión de la base de datos
        db.execSQL("DROP TABLE IF EXISTS citas");
        db.execSQL("DROP TABLE IF EXISTS facturas");
        db.execSQL("DROP TABLE IF EXISTS mensajes_chat");
        db.execSQL("DROP TABLE IF EXISTS notas_calendario");
        db.execSQL("DROP TABLE IF EXISTS pacientes");
        db.execSQL("DROP TABLE IF EXISTS registro_medico");
        db.execSQL("DROP TABLE IF EXISTS usuarios");

        // Volver a crear las tablas
        onCreate(db);
    }
}

