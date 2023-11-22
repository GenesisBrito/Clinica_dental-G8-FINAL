package catolica.edu.clinica_dental_g8_final;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import catolica.edu.clinica_dental_g8_final.db.DbHelper;

public class create_cita extends AppCompatActivity {

    EditText Txt_FechaCita, Txt_HoraCita, Txt_DescripcionCita;
    Button Btn_AgregarCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cita);

        Txt_FechaCita = findViewById(R.id.Txt_FechaCita);
        Txt_HoraCita = findViewById(R.id.Txt_HoraCita);
        Txt_DescripcionCita = findViewById(R.id.Txt_DescripcionCita);
        Btn_AgregarCita = findViewById(R.id.Btn_AgregarCita);

        Btn_AgregarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = Txt_FechaCita.getText().toString();
                String hora = Txt_HoraCita.getText().toString();
                String descripcion = Txt_DescripcionCita.getText().toString();

                SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                SimpleDateFormat inputTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

                try {
                    Date date = inputDateFormat.parse(fecha);
                    Date time = inputTimeFormat.parse(hora);

                    if (date != null && time != null) {
                        String fechaFormateada = inputDateFormat.format(date);
                        String horaFormateada = inputTimeFormat.format(time);

                        guardarCitaEnSharedPreferences(fechaFormateada, horaFormateada, descripcion);
                        mostrarMensajeGuardadoExitoso();
                        finish();
                    } else {
                        mostrarMensajeErrorFormato();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    mostrarMensajeErrorFormato();
                }

                DbHelper dbHelper = new DbHelper(create_cita.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (db != null){
                    Toast.makeText(create_cita.this, "BASE DE DATOS CREADA", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(create_cita.this, "ERROR AL CREAR BASE DE DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    // Guardar datos en SharedPreferences
    private void guardarCitaEnSharedPreferences(String fecha, String hora, String descripcion) {
        SharedPreferences sharedPreferences = getSharedPreferences("Citas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Fecha", fecha);
        editor.putString("Hora", hora);
        editor.putString("Descripcion", descripcion);
        editor.apply();
    }


    private void mostrarMensajeGuardadoExitoso() {
        Toast.makeText(this, "Cita guardada exitosamente", Toast.LENGTH_SHORT).show();
    }

    private void mostrarMensajeErrorFormato() {
        Toast.makeText(this, "Formato de fecha u hora inválido", Toast.LENGTH_SHORT).show();
    }
}



