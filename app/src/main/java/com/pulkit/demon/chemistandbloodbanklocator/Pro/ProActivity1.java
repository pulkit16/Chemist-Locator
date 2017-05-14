package com.pulkit.demon.chemistandbloodbanklocator.Pro;

/**
 * Created by Demon on 13-05-2017.
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.pulkit.demon.chemistandbloodbanklocator.R;
import com.pulkit.demon.chemistandbloodbanklocator.login.activity.LoginActivity;
import com.pulkit.demon.chemistandbloodbanklocator.login.helper.SQLiteHandler;
import com.pulkit.demon.chemistandbloodbanklocator.login.helper.SessionManager;

import java.util.HashMap;


public class ProActivity1 extends AppCompatActivity {

    private static final String TAG = ProActivity1.class.getSimpleName();

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private View header;
    private SQLiteHandler db;
    private SessionManager session;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
//        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new AddViewFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        header = navigationView.inflateHeaderView(R.layout.nav_header_music);
        //setname();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
/*
                if (id == R.id.chemist) {
                    fragment = new ChemistMapFragment();
                } else if (id == R.id.blood_bank) {
                    fragment = new Myfragment();
                } else if (id == R.id.jan_aushadi) {
                    fragment=new JanAushadiMapFragment();

                }
                else if (id == R.id.cmap) {
                    fragment=new ChemistMap();

                }
                else if (id==R.id.clist)
                {
                    fragment= new ListChemist();
                }
                else if(id==R.id.janmap)
                {
                    fragment=new JanAushadi();
                }
                else if(id==R.id.janspin)
                {
                    fragment=new Spin();
                }
                else if(id==R.id.pro)
                {
                    Intent intent = new Intent(ProActivity.this, ProActivity.class);
                    startActivity(intent);
                    finish();

                }*/
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container_wrapper, fragment);
                transaction.commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
/*    private void setname()
    {
        TextView profileName = (TextView) header.findViewById(R.id.profile_name);
        TextView profilemail=(TextView) header.findViewById(R.id.profile_mail);
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");


        // Displaying the user details on the screen
        profileName.setText(name);
        profilemail.setText(email);
    }
*/
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(ProActivity1.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

}
