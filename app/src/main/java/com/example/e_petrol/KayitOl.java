package com.example.e_petrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class KayitOl extends AppCompatActivity {
    Spinner fuel,car;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        car=findViewById(R.id.cartype);
        fuel=findViewById(R.id.fueltype);
        back=findViewById(R.id.back);
        ArrayAdapter adapter1=ArrayAdapter.createFromResource(this,R.array.FuelType,
                android.R.layout.simple_spinner_item);
        fuel.setAdapter(adapter1);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.CarType,
                android.R.layout.simple_spinner_item);
        car.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(KayitOl.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
