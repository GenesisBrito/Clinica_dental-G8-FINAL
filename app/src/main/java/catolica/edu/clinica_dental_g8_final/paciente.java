package catolica.edu.clinica_dental_g8_final;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class paciente extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout inicio, grupo_citas, grupo_pacientes, grupo_tratamientos, grupo_comunicacion, grupo_configuracion, grupo_salir;

    FloatingActionButton add_pacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        inicio = findViewById(R.id.inicio);
        grupo_citas = findViewById(R.id.grupo_citas);
        grupo_pacientes = findViewById(R.id.grupo_pacientes);
        grupo_tratamientos = findViewById(R.id.grupo_tratamientos);
        grupo_comunicacion = findViewById(R.id.grupo_comunicacion);
        grupo_configuracion = findViewById(R.id.grupo_configuracion);
        grupo_salir = findViewById(R.id.grupo_salir);
        add_pacientes =findViewById(R.id.add_pacientes);

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
                redirectActivity(paciente.this, Pantalla_1_Dentista.class);
            }
        });

        grupo_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(paciente.this, citas.class);
            }
        });

        grupo_pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre el drawer
                openDrawer(drawerLayout);
            }
        });

        grupo_tratamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(paciente.this, tratamientos.class);
            }
        });

        grupo_comunicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(paciente.this, comunicacion.class);
            }
        });

        grupo_configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(paciente.this, configuracion.class);
            }
        });

        grupo_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(paciente.this, "salir", Toast.LENGTH_SHORT).show();
            }
        });


        add_pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(paciente.this, "Agregar cita", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(paciente.this, create_paciente.class);
                startActivity(intent);
            }
        });
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
