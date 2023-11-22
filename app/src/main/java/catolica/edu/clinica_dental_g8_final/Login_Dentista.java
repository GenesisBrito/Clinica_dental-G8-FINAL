package catolica.edu.clinica_dental_g8_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Dentista extends AppCompatActivity {

    private EditText TxtDentistaContrasena, TxtDentistausuario;
    private Button btnIniciarDentista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dentista);

        TxtDentistausuario = findViewById(R.id.TxtDentistausuario);
        TxtDentistaContrasena = findViewById(R.id.TxtDentistaContrasena);

        btnIniciarDentista = findViewById(R.id.btnIniciarDentista);

        btnIniciarDentista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = TxtDentistausuario.getText().toString().trim();
                String contrasena = TxtDentistaContrasena.getText().toString().trim();

                if (usuario.isEmpty()) {
                    TxtDentistausuario.setError("Por favor, ingrese el usuario");
                    return;
                }
                if (contrasena.isEmpty()) {
                    TxtDentistaContrasena.setError("Por favor, ingrese la contraseña");
                    return;
                }

                boolean credencialesValidas = validateCredentials(usuario, contrasena);
                if (credencialesValidas) {
                    // Si las credenciales son válidas, se inicia la nueva actividad
                    Intent intent = new Intent(Login_Dentista.this, Pantalla_1_Dentista.class);
                    startActivity(intent);
                } else {
                    // Si las credenciales son inválidas, se muestra un mensaje de error
                    Toast.makeText(Login_Dentista.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TxtDentistausuario.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getAction() == KeyEvent.ACTION_DOWN
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Si se presiona Enter o se activa la acción 'Siguiente' en el teclado virtual
                    // se mueve el foco al campo de contraseña (TxtDentistaContrasena)
                    TxtDentistaContrasena.requestFocus();
                    return true;
                }
                return false;
            }
        });
    }

    // Método para simular la validación de credenciales (ejemplo simulado)
    private boolean validateCredentials(String usuario, String contrasena) {
        // Simulación de validación con datos predefinidos (deberías cambiar esto por una autenticación real)
        return usuario.equals("Daniela") && contrasena.equals("muelitas23");
    }
}
