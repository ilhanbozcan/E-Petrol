package com.example.e_petrol;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class History extends AppCompatActivity {
    TextView hello,favfuel,avarage;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference read=database.getReference("Users").child(user.getDisplayName()).child("information");

    int avrg=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hello=findViewById(R.id.hello);
        favfuel=findViewById(R.id.favfuel);
        avarage=findViewById(R.id.avarage);
        hello.setText("Hello "+user.getDisplayName());

        avarage.setText("Avarage Fuel(per month) = "+avrg);

        read.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Users i=new Users();
                    i=dataSnapshot.getValue(Users.class);
                    favfuel.setText("Favourite Brand= "+i.getFavmark());
                    System.out.println(i.getFavmark());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
