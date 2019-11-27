package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HostUpdate extends AppCompatActivity {

    EditText host_pswd;
    EditText host_name;
    EditText host_email;
    EditText host_contact;

    Button pswd_btn;
    Button details_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_update);
        host_pswd = (EditText) findViewById(R.id.hostpswd);
        host_name = (EditText) findViewById(R.id.hostname);
        host_email = (EditText) findViewById(R.id.hostemail);
        host_contact = (EditText) findViewById(R.id.hostcontact);
        pswd_btn = (Button) findViewById(R.id.button_pswd);
        details_btn = (Button) findViewById(R.id.hostsave);

        host_name.setEnabled(false);
        host_email.setEnabled(false);
        host_contact.setEnabled(false);
        details_btn.setEnabled(false);

        pswd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pswd = host_pswd.getText().toString();
                if (pswd.equals(MainActivity.app_db.Host.getH_pswd())){
                    host_name.setEnabled(true);
                    host_email.setEnabled(true);
                    host_contact.setEnabled(true);

                    host_name.setText(MainActivity.app_db.getHost().getH_name());
                    host_contact.setText(MainActivity.app_db.getHost().getH_contact());
                    host_email.setText(MainActivity.app_db.getHost().getH_email());
                    details_btn.setEnabled(true);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Invalid Password"+ MainActivity.app_db.getHost().getH_pswd(),
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
            }
        });

        details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h_name = host_name.getText().toString();
                String h_pswd = host_pswd.getText().toString();
                String h_email = host_email.getText().toString();
                String h_contact = host_contact.getText().toString();
                if (h_name.equals("") || h_email.equals("")||h_contact.equals("")||h_pswd.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please fill all details",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else{
                    host_pswd.setText("");
                    host_name.setText("");
                    host_email.setText("");
                    host_contact.setText("");
                    MainActivity.app_db.getHost().setH_contact(h_contact);
                    MainActivity.app_db.getHost().setH_email(h_email);
                    MainActivity.app_db.getHost().setH_name(h_name);
                    MainActivity.app_db.getHost().setH_pswd(h_pswd);
                    SaveState.saveData(MainActivity.app_db);
                    host_name.setEnabled(false);
                    host_contact.setEnabled(false);
                    host_email.setEnabled(false);
                    details_btn.setEnabled(false);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Host Details Updated",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
            }
        });

    }

}
