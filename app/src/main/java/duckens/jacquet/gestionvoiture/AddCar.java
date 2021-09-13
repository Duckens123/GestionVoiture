package duckens.jacquet.gestionvoiture;

import androidx.appcompat.app.AppCompatActivity;
import duckens.jacquet.gestionvoiture.Modele.Session;
import duckens.jacquet.gestionvoiture.Modele.Voiture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddCar extends AppCompatActivity {
    EditText marque,modele,annee,couleur,numimma;
    Spinner transmission;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        marque=(EditText)findViewById(R.id.marquev);
        modele=(EditText)findViewById(R.id.modelev);
        annee=(EditText)findViewById(R.id.anneev);
        couleur=(EditText)findViewById(R.id.couleurv);
        numimma=(EditText)findViewById(R.id.numimmav);
        transmission=findViewById(R.id.transmissionv);
        btnsave=findViewById(R.id.btnsave);
        LoadVlues();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Voiture obj=new Voiture();
                LoadVlues();
                if(Session.getCurrentVoiture()!=null){
                    obj=Session.getCurrentVoiture();
                }
                obj.setMarque(marque.getText().toString());
                obj.setCouleur(couleur.getText().toString());
                if(transmission.getSelectedItem().equals("Automatique") || transmission.getSelectedItem().equals("Manuelle")){
                    obj.setTransmission(transmission.getSelectedItem().toString());
                }
                else {
                    obj.setTransmission("");
                }
                obj.setAnnee(annee.getText().toString());
                obj.setModele(modele.getText().toString());
                obj.setNumImmatriculation(numimma.getText().toString());

                if(obj.getId()>0){
                    Voiture.update(AddCar.this,obj);
                }
                else {
                    Voiture.Insert(AddCar.this,obj);
                }
                Session.setCurrentVoiture(null);
                startActivity(new Intent(AddCar.this,ListCars.class));
                finish();
            }
        });

    }
    void LoadVlues(){
        Voiture obj=new Voiture();
        if(Session.getCurrentVoiture()!=null){
            obj=Session.getCurrentVoiture();
            marque.setText(obj.getMarque());
            modele.setText(obj.getModele());
            annee.setText(obj.getAnnee());
            couleur.setText(obj.getCouleur());
            numimma.setText(obj.getNumImmatriculation());
            transmission.setSelection(1);
        }
    }
}