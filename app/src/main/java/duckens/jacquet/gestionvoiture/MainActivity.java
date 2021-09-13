package duckens.jacquet.gestionvoiture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button btnconnect;
    private EditText temail,tpassword;
    private FirebaseAuth mAuth;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        temail=findViewById(R.id.txtemail);
        tpassword=findViewById(R.id.txtpwd);
        signup=findViewById(R.id.tinscrire);
        btnconnect=findViewById(R.id.btnconnect);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Signup.class));

            }
        });
        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser();
            }
        });
    }
    public  void LoginUser(){
        String email=temail.getText().toString();
        String pass=tpassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            temail.setError("Email obligatoire");
            temail.requestFocus();

        }else  if(TextUtils.isEmpty(pass)){
            tpassword.setError("Mot de passe obligatoire");
            tpassword.requestFocus();

        }else {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(MainActivity.this,ListCars.class));
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this,"Echec de connection"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}