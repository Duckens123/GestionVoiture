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

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText temail,pwd;
    Button btnSignup;
    TextView connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        temail=findViewById(R.id.txtemail);
        pwd=findViewById(R.id.txtpwd);
        connect=findViewById(R.id.tconnect);
        btnSignup=findViewById(R.id.btnsignup);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,MainActivity.class));

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUser();

                            }
        });
    }

    private void CreateUser(){
        String email=temail.getText().toString();
        String pass=pwd.getText().toString();
        if(TextUtils.isEmpty(email)){
            temail.setError("Email obligatoire");
            temail.requestFocus();

        }else  if(TextUtils.isEmpty(pass)){
            pwd.setError("Mot de passe obligatoire");
            pwd.requestFocus();

        }else {
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Signup.this,"Compte Cree avec succes",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this,MainActivity.class));
                        finish();
                    }else {
                        Toast.makeText(Signup.this,"Echec"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
    }
