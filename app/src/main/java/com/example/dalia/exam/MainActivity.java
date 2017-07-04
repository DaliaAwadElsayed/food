package com.example.dalia.exam;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText name, ingred, price;
    RadioGroup radiog;
    RadioButton r1;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        ingred = (EditText) findViewById(R.id.editText4);
        price = (EditText) findViewById(R.id.editText6);
        radiog = (RadioGroup) findViewById(R.id.radioGroup);
        b=(Button)findViewById(R.id.button);
      //  r1 = (RadioButton) findViewById(R.id.radioButton);
        db = openOrCreateDatabase("Food",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists Foodtype (name varchar,type varchar,ingred varchar,price varchar)");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type=radiog.getCheckedRadioButtonId();
                r1=(RadioButton)findViewById(type);
                db.execSQL("Insert into Foodtype Values ('"+name.getText().toString()+"','"+r1.getText().toString()+"','"+ingred.getText().toString()+"','"+price.getText().toString()+"') ");
                Toast.makeText(MainActivity.this, "added :))", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,listview.class);
                startActivity(i);
            }
        });
    }
}