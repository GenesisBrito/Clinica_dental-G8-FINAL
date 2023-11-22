package catolica.edu.clinica_dental_g8_final;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pantalla_1_Dentista extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText noteEditText;
    private TextView notesTextView;
    private Button saveNoteButton;
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout inicio, grupo_citas, grupo_pacientes, grupo_tratamientos, grupo_comunicacion, grupo_configuracion, grupo_salir;
    private static final String NOTES_PREFS = "NotasPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1_dentista);

        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
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
                recreate();
            }
        });

        grupo_citas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Pantalla_1_Dentista.this, citas.class);
            }
        });

        grupo_pacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Pantalla_1_Dentista.this, paciente.class);
            }
        });

        grupo_tratamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Pantalla_1_Dentista.this, tratamientos.class);
            }
        });

        grupo_comunicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Pantalla_1_Dentista.this, comunicacion.class);
            }
        });

        grupo_configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(Pantalla_1_Dentista.this, configuracion.class);
            }
        });

        grupo_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Pantalla_1_Dentista.this, "salir", Toast.LENGTH_SHORT).show();
            }
        });

        calendarView = findViewById(R.id.calendarView);
        noteEditText = findViewById(R.id.noteEditText);
        notesTextView = findViewById(R.id.notesTextView);
        saveNoteButton = findViewById(R.id.saveNoteButton);

        SharedPreferences sharedPreferences = getSharedPreferences(NOTES_PREFS, MODE_PRIVATE);

        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Establecer la fecha actual como la fecha seleccionada en el CalendarView
        calendarView.setDate(calendar.getTimeInMillis(), true, true);

        // Establecer el listener para la selección de fecha
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Se ejecuta cuando el usuario selecciona un día en el calendario
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(Pantalla_1_Dentista.this, "Fecha seleccionada: " + selectedDate, Toast.LENGTH_SHORT).show();

                // Recuperar todas las notas almacenadas en SharedPreferences y mostrarlas en notesTextView
                Set<String> notasGuardadas = sharedPreferences.getStringSet(selectedDate, new HashSet<>());
                StringBuilder notas = new StringBuilder();
                for (String nota : notasGuardadas) {
                    notas.append(nota).append("\n");
                }
                notesTextView.setText(notas.toString());
            }
        });

        // Manejar el botón para guardar la nota
        saveNoteButton.setOnClickListener(view -> {
            String nota = noteEditText.getText().toString().trim();
            if (!nota.isEmpty()) {
                // Guardar la nota en SharedPreferences
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Set<String> notasGuardadas = sharedPreferences.getStringSet(selectedDate, new HashSet<>());
                notasGuardadas.add(nota);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet(selectedDate, notasGuardadas);
                editor.apply();

                Toast.makeText(Pantalla_1_Dentista.this, "Nota guardada correctamente", Toast.LENGTH_SHORT).show();

                // Limpiar el campo de texto después de guardar la nota
                noteEditText.setText("");
            } else {
                Toast.makeText(Pantalla_1_Dentista.this, "Por favor, ingrese una nota", Toast.LENGTH_SHORT).show();
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

