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

public class UserLogout extends AppCompatActivity {

    public static Context cur_context;
    public static String ems_email;
    public static String ems_pswd;

    EditText user_Name_logout;
    EditText user_Contact_logout;
    EditText user_Email_logout;
    public static String name_logout;
    public static String contact_logout;
    public static String email_logout;
    Button Check_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_logout);

        cur_context = getApplicationContext();
        ems_email = getString(R.string.EMS_email);
        ems_pswd = getString(R.string.EMS_pswd);
        user_Name_logout = (EditText) findViewById(R.id.editname_logout);
        user_Contact_logout = (EditText) findViewById(R.id.editcontact_logout);
        user_Email_logout = (EditText) findViewById(R.id.editemail_logout);
        Check_out = (Button) findViewById(R.id.button_logout);

        Check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_logout = user_Name_logout.getText().toString();
                contact_logout = user_Contact_logout.getText().toString();
                email_logout = user_Email_logout.getText().toString();

                if (name_logout.equals("") || email_logout.equals("") || contact_logout.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please fill all details",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else if (!MainActivity.app_db.visitors.containsKey(name_logout+contact_logout+email_logout)){
                    Toast toast = Toast.makeText(UserLogout.cur_context,
                            "Visitor Not Logged In",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else{
                    AsyncTaskRunner_logout task = new AsyncTaskRunner_logout();
                    task.execute();
                }
            }
        });

    }
}

class AsyncTaskRunner_logout extends AsyncTask<String, String, String> {

    private String resp;


    @Override
    protected String doInBackground(String... params) {
        try {
            visitor cur_visitor = MainActivity.app_db.getVisitors().get(UserLogout.name_logout+UserLogout.contact_logout+UserLogout.email_logout);
            cur_visitor.setV_checkout();
            GMailSender sender = new GMailSender(UserLogout.ems_email, UserLogout.ems_pswd);
            sender.sendMail("Your Visit to EMS",
                    "Name: " + UserLogout.name_logout + "\nContact Number: " + UserLogout.contact_logout + "\nCheck-in Time: " + cur_visitor.getV_checkin() + "\nCheck-out Time: " + cur_visitor.getV_checkout() + "\nHost name: " + MainActivity.app_db.getHost().getH_name()+"\n\n\nThanks for Visiting EMS.",
                    UserLogout.ems_email,
                    cur_visitor.getV_email());
            MainActivity.app_db.visitors.remove(UserLogout.name_logout+UserLogout.contact_logout+UserLogout.email_logout);
            SaveState.saveData(MainActivity.app_db);
        } catch (Exception e) {
            Toast toast = Toast.makeText(UserLogout.cur_context,
                    "Check Your Internet Connection Or Invalid Email.",
                    Toast.LENGTH_SHORT);

            toast.show();
            Log.e("SendMail", e.getMessage(), e);
        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        Toast toast = Toast.makeText(UserLogout.cur_context,
                "Details Sent to Visitor!",
                Toast.LENGTH_SHORT);

        toast.show();

    }


    @Override
    protected void onPreExecute() {
        Toast toast = Toast.makeText(UserLogout.cur_context,
                "Initiated Message to Visitor!",
                Toast.LENGTH_SHORT);

        toast.show();
    }
}
