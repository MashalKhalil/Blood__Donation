package com.example.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    Spinner spi;
    EditText name,city,number;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spi=(Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this,R.array.BloodType,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spi.setAdapter(adapter);
        submit=(Button) findViewById(R.id.button);
        name=(EditText) findViewById(R.id.nam);
        city=(EditText) findViewById(R.id.city);
        number=(EditText) findViewById(R.id.num);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Blood Donors");
                DatabaseReference usersRef = ref.child("Donar");
                HashMap<String, Object>map=new HashMap<String,Object>();
                map.put("Name", name.getText().toString());
                map.put("City", city.getText().toString());
                map.put("Contact", number.getText().toString());
                map.put("blood group", spi.getSelectedItem().toString());
                usersRef.child("User").push().setValue(map);

            }
        });

    }
}