package com.example.e_petrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button sign_button,login_button;
    FirebaseAuth mfirebase;
    TextView login_mail,login_pass;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfirebase= FirebaseAuth.getInstance();

        login_mail=findViewById(R.id.login_mail);
        login_pass=findViewById(R.id.login_pass);
        login_button= findViewById(R.id.login_button);
        sign_button=findViewById(R.id.sign_button);

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,KayitOl.class);
                startActivity(intent);
            }
        });
        mAuthStateListener =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser= mfirebase.getCurrentUser();
                if(mFirebaseUser!=null){
                    Toast.makeText(MainActivity.this,"Giriş yapıldı.",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Lütfen giriş yapın.",Toast.LENGTH_SHORT).show();

                }
            }
        };
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=login_mail.getText().toString();
                String pwd=login_pass.getText().toString();
                if(email.isEmpty()){
                    login_mail.setError("Lütfen mailinizi giriniz");
                    login_mail.requestFocus();
                }
                else if(pwd.isEmpty()){
                    login_pass.setError("Şifrenizi giriniz");
                    login_pass.requestFocus();
                }

                else if(!(email.isEmpty()&& pwd.isEmpty())){//eğer boş değilse
                    Toast.makeText(MainActivity.this,"Giriş yapılıyor...",Toast.LENGTH_SHORT).show();

                    mfirebase.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(MainActivity.this,"Hatalı Giriş",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Intent intent=new Intent(MainActivity.this,MainPage.class);
                                        startActivity(intent);
                                        Toast.makeText(MainActivity.this,"Giriş başarılı.",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

                else{
                    Toast.makeText(MainActivity.this,"Hatalı giriş",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

