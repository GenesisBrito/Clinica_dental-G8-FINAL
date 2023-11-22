package catolica.edu.clinica_dental_g8_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Login_Asistente extends AppCompatActivity {

    EditText TxtAsistenteUsuario,TxtAsistenteContraseña;
    Button btnIniciarAsistente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_asistente);

        TxtAsistenteUsuario = findViewById(R.id.TxtAsistenteUsuario);
        TxtAsistenteContraseña = findViewById(R.id.TxtAsistenteContraseña);

        btnIniciarAsistente = findViewById(R.id.btnIniciarAsistente);

    }
}