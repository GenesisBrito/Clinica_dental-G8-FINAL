package catolica.edu.clinica_dental_g8_final;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import catolica.edu.clinica_dental_g8_final.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnDentista, btnAsistente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDentista=findViewById(R.id.btnDentista);
        btnAsistente=findViewById(R.id.btnAsistente);

    }
    public void selectRole(View view) {
        String selectedRole = "";
        // esta es la configuracion para llamar los botones
        if (view.getId() == R.id.btnDentista) {
            selectedRole = "Dentista";
            Intent intentDentista = new Intent(this, Login_Dentista.class);
            intentDentista.putExtra("selectedRole", selectedRole);
            startActivity(intentDentista);
        } else if (view.getId() == R.id.btnAsistente) {
            selectedRole = "Asistente";
            Intent intentAsistente = new Intent(this, Login_Asistente.class);
            intentAsistente.putExtra("selectedRole", selectedRole);
            startActivity(intentAsistente);
        }
    }
}