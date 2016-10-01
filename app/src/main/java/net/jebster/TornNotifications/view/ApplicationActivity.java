package net.jebster.TornNotifications.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import net.jebster.TornNotifications.model.Globals;
import net.jebster.TornNotifications.R;
import net.jebster.TornNotifications.model.TornUser;
import net.jebster.TornNotifications.service.TornBackgroundService;
import net.jebster.TornNotifications.tools.Observer;

public class ApplicationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Observer {

    private static final String TAG = "ApplicationActivity";

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setDisplay(R.id.nav_home);

        Log.d(TAG, "StartBackgroundService");

        Globals.User().addObserver(TAG, this);

        startService(new Intent(this, TornBackgroundService.class));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return setDisplay(item.getItemId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        setDisplay(item.getItemId());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean setDisplay(int item){
        Fragment fragment = null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        String title = "";

        switch (item){
            case R.id.nav_home:
                fragment = new HomeFragment();
                title = "Home";
                break;
            case R.id.nav_notificcations:
                fragment = new SettingsFragment();
                title = "Settings";
                break;
        }

        setTitle(title);

        if(fragment != null){
            ft.replace(R.id.content_navigation, fragment);
            ft.commit();
            return true;
        }


        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void update() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View hView = navigationView.getHeaderView(0);
                TextView nav_user = (TextView)hView.findViewById(R.id.nav_username);
                nav_user.setText(Globals.User().getUsername());
            }
        });

    }
}
