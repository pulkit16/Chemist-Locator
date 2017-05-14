package com.pulkit.demon.chemistandbloodbanklocator;

import com.pulkit.demon.chemistandbloodbanklocator.fragment.MapsActivity;
import com.pulkit.demon.chemistandbloodbanklocator.list.ListChemist;
import com.pulkit.demon.chemistandbloodbanklocator.login.helper.SQLiteHandler;
import com.pulkit.demon.chemistandbloodbanklocator.login.helper.SessionManager;
import com.pulkit.demon.chemistandbloodbanklocator.login.activity.LoginActivity;


import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnChemist;
    private Button btnBloodBank;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnChemist = (Button) findViewById(R.id.btn_chemist);
        btnBloodBank =(Button) findViewById(R.id.btn_bloodbank);
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
    btnChemist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Chemist();
            }
        });
        btnBloodBank.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BloodBank();
            }
        });
    }


     /** Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void Chemist()
    {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }
    private void BloodBank()
    {
        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        startActivity(intent);
        finish();
    }
}