package com.example.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {

    public static Context cur_context;
    public static String ems_email;
    public static String ems_pswd;

     EditText user_Name;
     EditText user_Contact;
     EditText user_Email;
     public static String name;
     public static String contact;
     public static String email;
    Button Check_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        cur_context = getApplicationContext();
        ems_email = getString(R.string.EMS_email);
        ems_pswd = getString(R.string.EMS_pswd);
        user_Name = (EditText) findViewById(R.id.editname);
        user_Contact = (EditText) findViewById(R.id.editcontact);
        user_Email = (EditText) findViewById(R.id.editemail);
        Check_in = (Button) findViewById(R.id.button);

        Check_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = user_Name.getText().toString();
                contact = user_Contact.getText().toString();
                email = user_Email.getText().toString();

                if (name.equals("") || email.equals("") || contact.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please fill all details",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else if (MainActivity.app_db.visitors.containsKey(name+contact+email)){
                    Toast toast = Toast.makeText(UserLogin.cur_context,
                            "Visitor already Logged In",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else{
                    AsyncTaskRunner task = new AsyncTaskRunner();
                    task.execute();
                }
            }
        });

    }
}

class AsyncTaskRunner extends AsyncTask<String, String, String> {

    private String resp;


    @Override
    protected String doInBackground(String... params) {
        try {
            visitor cur_visitor = new visitor(UserLogin.name, UserLogin.email, UserLogin.contact);
            GMailSender sender = new GMailSender(UserLogin.ems_email, UserLogin.ems_pswd);
            sender.sendMail("EMS Visitor Check In",
                    "Name: " + UserLogin.name + "\nContact Number: " + UserLogin.contact + "\nEmail Address: " + UserLogin.email + "\nCheckin Time: " + cur_visitor.getV_checkin(),
                    UserLogin.ems_email,
                    MainActivity.app_db.Host.getH_email());
            MainActivity.app_db.visitors.put(UserLogin.name+UserLogin.contact+UserLogin.email, cur_visitor);
            SaveState.saveData(MainActivity.app_db);
        } catch (Exception e) {
            Toast toast = Toast.makeText(UserLogin.cur_context,
                    "Check Your Internet Connection",
                    Toast.LENGTH_SHORT);

            toast.show();
            Log.e("SendMail", e.getMessage(), e);
        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        Toast toast = Toast.makeText(UserLogin.cur_context,
                "Details Sent to Host!",
                Toast.LENGTH_SHORT);

        toast.show();

    }


    @Override
    protected void onPreExecute() {
        Toast toast = Toast.makeText(UserLogin.cur_context,
                "Initiated Message to Host!",
                Toast.LENGTH_SHORT);

        toast.show();
    }
}