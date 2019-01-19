package com.example.student.teamproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new DatePickerFragment();
        fragmentManager
                .beginTransaction()
                .replace(R.id.contentContainer, fragment)
                .addToBackStack(null)
                .commit();

//        createJson();
    }

//    private final class UserNotes {
//        // List<NotesModel>
//        // userEmail from prefs
//
//        private String userEmail;
//        private List<NotesModel> userNotesList;
//
//        public UserNotes(String userEmail, List<NotesModel> userNotesList) {
//            this.userEmail = userEmail;
//            this.userNotesList = userNotesList;
//        }
//    }
//
//    private void createJson() {
//        SqliteDbUtils dbUtils = new SqliteDbUtils(this);
//        List<NotesModel> notesList;
//        String email, json;
//        Gson gson = new Gson();
//
//        notesList = dbUtils.getList();
//        email = LoginSharedPrefsUtils.getUserEmail(this);
//
//        UserNotes userNotes = new UserNotes(email, notesList);
//        json = gson.toJson(userNotes);
//
//        Log.d(TAG, "UserNotes JSON: " + json);
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            int backStackCount = getSupportFragmentManager().getBackStackEntryCount();

            if (backStackCount == 1) {
                super.onBackPressed();
            }

            super.onBackPressed();
        }
    }

//            Log.d(TAG, "backStackCount: " + backStackCount + " @onBackPressed()");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        String title = null;

        try {
            title = getSupportActionBar().getTitle().toString();
        } catch (Exception exception) {
            Log.e(TAG, "TopBar title is null. @onNavigationItemSelected(..)");
            exception.printStackTrace();
        }

        switch (id) {
            case R.id.nav_cal:
                if (title != null && !title.equals(getString(R.string.date_picker))) {
                    fragment = new DatePickerFragment();
                    //fragment = new CalFragment();
                }
                break;
            case R.id.nav_signing_in:
                if (title != null && !title.equals(getString(R.string.signing_in))) {
                    fragment = new SignInFragment();
                }
                break;
            case R.id.nav_list:
                if (title != null && !title.equals(getString(R.string.alert_list))) {
                    fragment = new EventsFragment();
                }
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
