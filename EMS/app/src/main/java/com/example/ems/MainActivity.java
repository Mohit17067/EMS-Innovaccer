package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Button go;
    RadioGroup rg;
    RadioButton option;
    public static Database app_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
        this.app_db = SaveState.loadData();
        addListenerOnButton();

    }


    public void addListenerOnButton() {
        rg = (RadioGroup) findViewById(R.id.options);
        go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = rg.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                option = (RadioButton) findViewById(selectedId);

                if(option == null){
                    Toast.makeText(getApplicationContext(), "Please select an option to proceed!!!",
                            Toast.LENGTH_SHORT).show();
                }
                else if(option.getText().equals("Visitor Check In") ) {


                    Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                    startActivity(intent);
                }
                else if(option.getText().equals("Visitor Check Out")){
                    Intent intent = new Intent(getApplicationContext(), UserLogout.class);
                    startActivity(intent);
                }
                else if(option.getText().equals("Update Host Details")){
                    Intent intent = new Intent(getApplicationContext(), HostUpdate.class);
                    startActivity(intent);
                }

            }

        });
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}

 class SaveState implements Serializable {

    public static Database db = null;

    public static Database getDatabase(){
        if( db == null ) {
            db = new Database();
            saveData(db);
        }
        return db;
    }

    public static void saveData(Database instance){
        ObjectOutput out;
        try {

            File outFile = new File(Environment.getExternalStorageDirectory(), "appSaveState.data");
            out = new ObjectOutputStream(new FileOutputStream(outFile));
            out.writeObject(instance);
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public static Database loadData(){
        ObjectInput in;
        try {
            File inFile = new File(Environment.getExternalStorageDirectory(), "appSaveState.data");

            in = new ObjectInputStream(new FileInputStream(inFile));
            db=(Database) in.readObject();

            in.close();
        } catch (Exception e) {e.printStackTrace();}
        return getDatabase();
    }
}
