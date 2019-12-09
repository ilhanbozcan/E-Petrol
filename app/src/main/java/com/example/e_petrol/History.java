package com.example.e_petrol;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//Database boş olduğundan hata veriyor.!!!!!!!!!!!!!!!!!!!!!
public class History extends AppCompatActivity {
    ArrayList<Posts> list=new ArrayList<>();

    RecyclerView recyclerView;
    Context context=this;
    Button new_history;
    TextView hello,favfuel,avarage;
    EditText liter;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference read=database.getReference("Users").child(user.getDisplayName()).child("information");
    DatabaseReference myRef = database.getReference();
    DatabaseReference Ref=database.getReference("Users").child(user.getDisplayName()).child("History").child(myRef.getKey());

    Date date=new Date();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    String a=df.format(date);
    Spinner favMark;
    int avrg=0;

    public void CreateHistory(String mark,String fuel,String date){
        Posts post=new Posts(mark,fuel,date);
        String keys=myRef.getKey();

        myRef.child("Users").child(user.getDisplayName()).child("History").child(keys).setValue(post);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        hello=findViewById(R.id.hello);
        favfuel=findViewById(R.id.favfuel);
        avarage=findViewById(R.id.avarage);
        favMark=findViewById(R.id.which_mark);
        liter=findViewById(R.id.liter_howmuch);
        ArrayAdapter adapter2=ArrayAdapter.createFromResource(this,R.array.FuelMark,
                android.R.layout.simple_spinner_item);
        favMark.setAdapter(adapter2);
        new_history=findViewById(R.id.push);
        new_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String litre=liter.getText().toString();
                String favMarka=favMark.getSelectedItem().toString();
                final Calendar c = Calendar.getInstance();
                String dates;
                int a=c.get(Calendar.MONTH)+1;
                dates=c.get(Calendar.DAY_OF_MONTH)+"."+a+"."+c.get(Calendar.YEAR);
                CreateHistory(favMarka,litre,dates);
            }
        });

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
        Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Posts i=new Posts();
                    i=dataSnapshot.getValue(Posts.class);
                    AddList(i.date,i.mark,i.liter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView=findViewById(R.id.anamenü);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.canScrollVertically();
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


    }
    public void AddList(String x,String y,String v){
        list.add(new Posts(x,v,y));
        ListAdapter listAdapter=new ListAdapter(list,context);
        recyclerView.setAdapter(listAdapter);



    }
}
