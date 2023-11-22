package catolica.edu.clinica_dental_g8_final;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import catolica.edu.clinica_dental_g8_final.db.DbHelper;


public class citas extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout inicio, grupo_citas, grupo_pacientes, grupo_tratamientos, grupo_comunicacion, grupo_configuracion, grupo_salir;

    FloatingActionButton add_citas;

    private RecyclerView recyclerViewCitas;
    private CitasAdapter citasAdapter;
    private List<Cita> listaCitas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        add_citas= findViewById(R.id.add_citas);
        inicio = findViewById(R.id.inicio);
        grupo_citas = findViewById(R.id.grupo_citas);
        grupo_pacientes = findViewById(R.id.grupo_pacientes);
        grupo_tratamientos = findViewById(R.id.grupo_tratamientos);
        grupo_comunicacion = findViewById(R.id.grupo_comunicacion);
        grupo_configuracion = findViewById(R.id.grupo_configuracion);
        grupo_salir = findViewById(R.id.grupo_salir);


        menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    openDrawer(drawerLayout);
                    return true;
                }
                return false;
            }
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(citas.this, Pantalla_1_Dentista.class);
            }
        });

        grupo_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre el drawer
                openDrawer(drawerLayout);
            }
        });

        grupo_pacientes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                redirectActivity(citas.this, paciente.class);
            }
        });

        grupo_tratamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(citas.this, tratamientos.class);
            }
        });

        grupo_comunicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(citas.this, comunicacion.class);
            }
        });

        grupo_configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(citas.this, configuracion.class);
            }
        });

        grupo_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(citas.this, "salir", Toast.LENGTH_SHORT).show();
            }
        });


        add_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(citas.this, "Agregar cita", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(citas.this, create_cita.class);
                startActivity(intent);
            }
        });

        // Recuperar datos de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("Citas", Context.MODE_PRIVATE);
        String fecha = sharedPreferences.getString("Fecha", "");
        String hora = sharedPreferences.getString("Hora", "");
        String descripcion = sharedPreferences.getString("Descripcion", "");

        if (!fecha.isEmpty() && !hora.isEmpty() && !descripcion.isEmpty()) {
            // Si hay citas guardadas, crear una lista y agregar la cita
            List<Cita> listaCitas = new ArrayList<>();
            Cita cita = new Cita(fecha, hora, descripcion);
            listaCitas.add(cita);

            // Configurar RecyclerView con el adaptador
            RecyclerView recyclerViewCitas = findViewById(R.id.recyclerViewCitas);
            CitasAdapter citasAdapter = new CitasAdapter(listaCitas);
            recyclerViewCitas.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewCitas.setAdapter(citasAdapter);
        } else {
            Toast.makeText(this, "No hay citas guardadas", Toast.LENGTH_SHORT).show();
        }
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
