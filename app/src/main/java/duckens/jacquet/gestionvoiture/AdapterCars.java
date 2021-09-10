package duckens.jacquet.gestionvoiture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import duckens.jacquet.gestionvoiture.Modele.Voiture;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class AdapterCars extends ArrayAdapter<Voiture> {

    Context context;
    final List<Voiture> objs;
    public AdapterCars(@NonNull Context context, int resource, @NonNull List<Voiture> objects) {
        super(context, resource, objects);
        this.context=context;
        objs=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row_view;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            row_view=inflater.inflate(R.layout.view_cars,parent,false);

        }
        else {
            row_view = convertView;
        }
        TextView marque=row_view.findViewById(R.id.marque);
        TextView modele=row_view.findViewById(R.id.modele);
        TextView annee=row_view.findViewById(R.id.annee);
        TextView transmission=row_view.findViewById(R.id.transmission);
        TextView couleur=row_view.findViewById(R.id.couleur);
        TextView numimma=row_view.findViewById(R.id.numimma);
        Voiture voiture=(Voiture)objs.get(position);
        marque.setText(voiture.getMarque());
        modele.setText(voiture.getModele());
        annee.setText(voiture.getAnnee());
        transmission.setText(voiture.getTransmission());
        couleur.setText(voiture.getCouleur());
        numimma.setText(voiture.getNumImmatriculation());

        return  row_view;

        //return super.getView(position, convertView, parent);
    }
}
