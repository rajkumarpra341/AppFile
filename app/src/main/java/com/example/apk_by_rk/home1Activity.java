package com.example.apk_by_rk;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class home1Activity extends AppCompatActivity {
      DataBaseHelper mydb ;
      EditText edName , edMarks ,edId;
      Button insertBtn , showBtn ,updateBtn ,deleteBtn ;

      ListView tv ;
      ArrayList list ;

      ArrayAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        edName = findViewById(R.id.editName);
        edMarks = findViewById(R.id.editMarks);
        edId = findViewById(R.id.editId);
        insertBtn = findViewById(R.id.insertBtn);
        showBtn = findViewById(R.id.showBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
    mydb =  new DataBaseHelper(this);
       inserdata();
       showData();
       update();
       deleted();
    }
    public void inserdata()
    {
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edName.length() == 0 || edMarks.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill Details", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = mydb.insertData(edName.getText().toString(), edMarks.getText().toString());
                    if (inserted) {
                        Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void update()
    {
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edName.length() == 0 || edMarks.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill Details", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean updated = mydb.updateData(edId.getText().toString(), edName.getText().toString(), edMarks.getText().toString());
                    if (updated) {
                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error in updating", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
    public void showData()
    {
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = mydb.ShowData();
                if(cursor.getCount()==0)
                {
                    message("error" , "No data");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext())
                {
                    buffer.append("Id : " + cursor.getString(0) + " \n")
                            .append("name : " + cursor.getString(1) + " \n")
                            .append("marks : " + cursor.getString(2) + " \n");
                }

                message("Data" , buffer.toString());
            }
        });
    }

    public void deleted()
    {
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edId.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please select id", Toast.LENGTH_SHORT).show();
                } else {
                    Integer delete = mydb.delete(edId.getText().toString());
                    if (delete > 0) {
                        Toast.makeText(getApplicationContext(), "Data deleted", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getApplicationContext(), "Error in deletion", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void message(String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title)
                .setMessage(message)
                .show();
    }
}