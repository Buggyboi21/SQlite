package com.example.sql_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editname,editsurname,editage,editTextid;
    Button btnAddData;

    Button btnviewALL;
    Button btnviewupdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        editname = (EditText) findViewById(R.id.edittext_name);
        editsurname = (EditText) findViewById(R.id.edittext_surname);
        editage = (EditText) findViewById(R.id.edittext_AGE);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnviewALL = (Button) findViewById(R.id.button_viewAll);
        btnDelete = (Button) findViewById(R.id.delete);
        AddData();
        viewALL();
        UpdateData();
        DeleteData();
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDB.deleteData(editTextid.getText().toString());
                        if (deleteRows > 0)
                            Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }
    public void UpdateData(){
        btnviewupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isupdte = myDB.updateData(editTextid.getText().toString(), editname.getText().toString(),editsurname.getText().toString()
                                ,editage.getText().toString());
                        if(isupdte == true)
                            Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editname.getText().toString(),editsurname.getText().toString()
                        ,editage.getText().toString());

                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();

                    }
                }
        );

    }

    public void viewALL() {
        btnviewALL.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDB.getAlldata();
                        if (res.getCount() == 0) {
                            showMessage("ERROR", "NOTHING FOUND");
                            return;
                        }

                        StringBuffer Buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            Buffer.append("id :" + res.getString(0)+"\n");
                            Buffer.append("name :" + res.getString(1)+"\n");
                            Buffer.append("surname :" + res.getString(2)+"\n");
                            Buffer.append("AGE :" + res.getString(3)+"\n");
                        }

                        showMessage("Data",Buffer.toString());

                    }
                }
        );
    }

    public void showMessage (String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

