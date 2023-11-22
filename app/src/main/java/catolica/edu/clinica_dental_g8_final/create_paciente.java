package catolica.edu.clinica_dental_g8_final;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class create_paciente extends AppCompatActivity {
    private EditText txtNombrePaciente, txtApellidoPaciente, txtDuiPaciente, txtDireccionPaciente, txtCelPaciente;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_paciente);

        // Enlazar los elementos de la interfaz con sus IDs correspondientes
        txtNombrePaciente = findViewById(R.id.Txt_NombrePaciente);
        txtApellidoPaciente = findViewById(R.id.Txt_ApellidoPaciente);
        txtDuiPaciente = findViewById(R.id.Txt_DuiPaciente);
        txtDireccionPaciente = findViewById(R.id.Txt_DireccionPaciente);
        txtCelPaciente = findViewById(R.id.Txt_CelPaciente);
        btnAdd = findViewById(R.id.btn_add);

        // Agregar un listener al botón para el evento de clic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String nombre = txtNombrePaciente.getText().toString().trim();
                String apellido = txtApellidoPaciente.getText().toString().trim();
                String dui = txtDuiPaciente.getText().toString().trim();
                String direccion = txtDireccionPaciente.getText().toString().trim();
                String celular = txtCelPaciente.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || apellido.isEmpty() || dui.isEmpty() || direccion.isEmpty() || celular.isEmpty()) {
                    Toast.makeText(create_paciente.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí puedes utilizar los datos ingresados para crear un nuevo paciente
                    // Por ejemplo, puedes guardarlos en una base de datos o realizar alguna otra acción
                    // Agrega tu lógica aquí

                    // Mostrar un mensaje de éxito
                    Toast.makeText(create_paciente.this, "Paciente agregado exitosamente", Toast.LENGTH_SHORT).show();

                    // Limpiar los EditText después de agregar el paciente
                    limpiarCampos();
                }
            }
        });
    }

    // Método para limpiar los campos después de agregar un paciente
    private void limpiarCampos() {
        txtNombrePaciente.setText("");
        txtApellidoPaciente.setText("");
        txtDuiPaciente.setText("");
        txtDireccionPaciente.setText("");
        txtCelPaciente.setText("");
    }
}
