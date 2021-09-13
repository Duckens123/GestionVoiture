package duckens.jacquet.gestionvoiture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import duckens.jacquet.gestionvoiture.Modele.Session;
import duckens.jacquet.gestionvoiture.Modele.Voiture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ListCars extends AppCompatActivity {
    ListView lvvoiture;
    AdapterCars adapterCars;
    private FirebaseAuth mAuth;
    ArrayAdapter<Voiture> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cars);
        lvvoiture=findViewById(R.id.lvvoiture);
        mAuth = FirebaseAuth.getInstance();
        List<Voiture> list=new ArrayList<>();
        list=Voiture.selectAll(ListCars.this);
        adapterCars = new AdapterCars(ListCars.this, android.R.layout.simple_list_item_1, list);
        lvvoiture.setAdapter(adapterCars);

        lvvoiture.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               new AlertDialog.Builder(ListCars.this)
                       .setTitle("Confirmation de suppression")
                       .setMessage("Voulez-vous vraiment supprimer cette voiture>")
                       .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                              Voiture obj=new Voiture();
                              obj=adapterCars.getItem(position);
                              Voiture.delete(ListCars.this,obj.getId());
                              startActivity(new Intent(ListCars.this,ListCars.class));
                                finish();
                           }
                       })
                       .setNegativeButton("Nom", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                           }
                       })
                       .setCancelable(false)
                       .show();
                return true;
            }
        });
        lvvoiture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Voiture obj=new Voiture();
                obj=adapterCars.getItem(position);
                Session.setCurrentVoiture(obj);
                startActivity(new Intent(ListCars.this,AddCar.class));
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(ListCars.this,MainActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addcar:
                Intent intent= new Intent(ListCars.this,AddCar.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.viderListe:
               Voiture.deleteAll(ListCars.this);
                startActivity(new Intent(ListCars.this, ListCars.class));
                finish();
                return true;
            case R.id.logout:
                startActivity(new Intent(ListCars.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}