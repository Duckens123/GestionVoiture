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
import android.widget.Toast;

public class AddCar extends AppCompatActivity {
    EditText marque,modele,annee,couleur,numimma;
    Spinner transmission;
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        marque=findViewById(R.id.marquev);
        modele=findViewById(R.id.modelev);
        annee=findViewById(R.id.anneev);
        couleur=findViewById(R.id.couleurv);
        numimma=findViewById(R.id.numimmav);
        transmission=findViewById(R.id.transmissionv);
        btnsave=findViewById(R.id.btnsave);
        LoadVlues();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Voiture obj=new Voiture();
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
                    Toast.makeText(AddCar.this,"Mise ajout effectue avec succes",Toast.LENGTH_LONG).show();


                }
                else {
                    Voiture.Insert(AddCar.this,obj);
                    Toast.makeText(AddCar.this,"patient ajoute avec succes",Toast.LENGTH_LONG).show();

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