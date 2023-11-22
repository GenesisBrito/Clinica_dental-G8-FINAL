package catolica.edu.clinica_dental_g8_final;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.CitasViewHolder> {

    private List<Cita> listaCitas;

    public CitasAdapter(List<Cita> listaCitas) {
        this.listaCitas = listaCitas;
    }

    @NonNull
    @Override
    public CitasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cita, parent, false);
        return new CitasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CitasViewHolder holder, int position) {
        Cita cita = listaCitas.get(position);
        holder.bind(cita);
    }

    @Override
    public int getItemCount() {
        return listaCitas.size();
    }

    public class CitasViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewFecha, textViewHora, textViewDescripcion;

        public CitasViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFecha = itemView.findViewById(R.id.Txt_FechaCita);
            textViewHora = itemView.findViewById(R.id.Txt_HoraCita);
            textViewDescripcion = itemView.findViewById(R.id.Txt_DescripcionCita);
        }

        public void bind(Cita cita) {
            textViewFecha.setText(cita.getFecha());
            textViewHora.setText(cita.getHora());
            textViewDescripcion.setText(cita.getDescripcion());
        }
    }
}


