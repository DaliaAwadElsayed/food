package com.example.dalia.exam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listview extends AppCompatActivity {
ListView lv;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lv=(ListView)findViewById(R.id.lv);
        db = openOrCreateDatabase("Food",MODE_PRIVATE,null);
        Cursor c=db.rawQuery("Select name from foodtype ",null);
        ArrayList ar=new ArrayList();
        while(c.moveToNext()){
            ar.add(c.getString(0));

        }
        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.simple_list_item_1,ar);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cr=db.rawQuery("Select * from foodtype where name='"+lv.getItemAtPosition(position).toString()+"'",null);
                StringBuffer sb=new StringBuffer();
                while (cr.moveToNext()){
                    sb.append(cr.getString(0)+"\n");
                    sb.append(cr.getString(1)+"\n");
                    sb.append(cr.getString(2)+"\n");
                    sb.append(cr.getString(3)+"\n");

                }
                AlertDialog.Builder al=new AlertDialog.Builder(listview.this );
                al.setTitle("food  component");
                al.setMessage(sb.toString());
                al.show();
            }
        });

    }
}
