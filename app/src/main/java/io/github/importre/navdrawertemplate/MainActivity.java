package io.github.importre.navdrawertemplate;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends BaseActivity {

    private MainFragment mainFragment;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private View navDrawer;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.main_fragment);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        init();
        setupNavDrawer();
        drawerToggle.syncState();
    }

    private void init() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        navDrawer = drawerLayout.findViewById(R.id.navdrawer);
        listView = (ListView) findViewById(R.id.nav_listview);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupNavDrawer() {
        int color = getResources().getColor(R.color.main_dark);
        drawerLayout.setStatusBarBackgroundColor(color);
        drawerLayout.setDrawerListener(drawerToggle);

        populateNavDrawer();
        removeTopMarginOfNavDrawer();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void removeTopMarginOfNavDrawer() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        navDrawer.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams)
                        listView.getLayoutParams();
                lp.topMargin = 0;
                listView.setLayoutParams(lp);
                return insets;
            }
        });
    }

    private void populateNavDrawer() {
        View header = getLayoutInflater().inflate(
                R.layout.nav_drawer_header, listView, false);
        listView.addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1);
        for (int i = 0; i < 20; i++) {
            adapter.add(getString(R.string.app_name) + " " + i);
        }
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, view.toString(), Toast.LENGTH_SHORT).show();
                closeDrawersDelayed(300);
            }
        });
    }

    private void closeDrawersDelayed(long delayMillis) {
        drawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.closeDrawers();
            }
        }, delayMillis);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navDrawer)) {
            drawerLayout.closeDrawers();
            return;
        }

        super.onBackPressed();
    }
}
