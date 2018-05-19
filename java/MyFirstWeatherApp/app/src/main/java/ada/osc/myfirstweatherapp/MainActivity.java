package ada.osc.myfirstweatherapp;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;
    private ViewPager viewPager;
    private CustomViewPagerFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initToolbar();

        adapter = new CustomViewPagerFragmentAdapter(getSupportFragmentManager());

        ArrayList<LocationWrapper> locationWrappers = new ArrayList<>();
        locationWrappers.add(new LocationWrapper("Osijek"));

        adapter.setAdapterData(locationWrappers);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initNavigationDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_activity_drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.main_activity_view_pager);
        if (viewPager != null) {
            viewPager.setOffscreenPageLimit(3);
        }
    }

    private void initNavigationDrawer() {
        mNavigationView = (NavigationView) findViewById(R.id.main_activity_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleItemSelectedClick(item.getItemId());
                return false;
            }
        });
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.main_activity_title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void handleItemSelectedClick(int itemID) {
        switch (itemID) {
            case R.id.menu_add_new_location: {
                startActivity(new Intent(this, AddNewLocationActivity.class));
                drawerLayout.closeDrawers();
                break;
            }
        }
    }

    private void createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Constants.SNOW_CASE: {
                    setWeatherIcon(Constants.SNOW);
                    break;
                }
                case Constants.RAIN_CASE: {
                    setWeatherIcon(Constants.RAIN);
                    break;
                }
                case Constants.CLEAR_CASE: {
                    setWeatherIcon(Constants.SUN);
                    break;
                }
                case Constants.MIST_CASE: {
                    setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.FOG_CASE: {
                    setWeatherIcon(Constants.FOG);
                    break;
                }
                case Constants.HAZE_CASE: {
                    setWeatherIcon(Constants.FOG);
                    break;
                }

                case Constants.CLOUD_CASE: {
                    setWeatherIcon(Constants.CLOUD);
                    break;
                }
            }
    }

    // TODO: 18/05/2018 load image using the constants (hint image base + path)
    private void setWeatherIcon(String iconPath) {

    }

    private double toCelsiusFromKelvin(double temperature) {
        return temperature - 273;
    }
}