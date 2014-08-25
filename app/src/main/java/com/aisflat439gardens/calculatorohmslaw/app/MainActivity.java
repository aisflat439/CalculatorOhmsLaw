package com.aisflat439gardens.calculatorohmslaw.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity{

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    //nav Drawer title
    private CharSequence mDrawerTitle;

    //used to store app title
    private CharSequence mTitle;

    //slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // load side menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // get nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        //add items to the array
//------------------------------------------------------------------------------------
//---------- look here to add more calculators as neccesary --------------------------
//------------------------------------------------------------------------------------

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
        // placeholder cut out counter
       // navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));

        // recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // settint the new nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        //enable action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
      //  getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, // nav menu toggle icon
                R.string.app_name, // nav drawer open
                R.string.app_name // nav drawer close
        ){
            public void onDrawerClosed(View view){
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView){
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar logos
                invalidateOptionsMenu();
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }

//-------------------------------------------------------------------------------------
//  Slide menu item click listener
//-------------------------------------------------------------------------------------

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.contact_developer:
                Intent intent2 = new Intent(Intent.ACTION_SEND);
                intent2.setType("plain/text");
                intent2.putExtra(Intent.EXTRA_EMAIL, new String[] { "ohmcalculator@gmail.com" });
                intent2.putExtra(Intent.EXTRA_SUBJECT, "Ohm Calculator Feedback");
                intent2.putExtra(Intent.EXTRA_TEXT, "...");
                startActivity(Intent.createChooser(intent2, ""));

                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

//
    //  called when invalidateOptionsMenu() is triggered
//
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        // if drawer is open hide action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

//--------------------------------------------------------
//  display fragment view for sleecetced drawer
// ----------------------------------------------------------

    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new  FragmentOhmCalculator();
                break;
            case 1:
                fragment = new FragmentResistorSelector();
                break;
            case 2:
                fragment = new FragmentLEDResistorDrop();
                break;
            case 3:
                fragment = new FragmentMCDLumensCalculator();
                break;
            case 4:
                break;
               // fragment = new FragmentAstableCircut();
            // add dBm to Watts Calculator
            case 5:
                break;
               // fragment = new FragmentLEDSeriesCalculator();
            case 6:
                fragment = new FragmentSingleLEDCalculator();
                break;
            case 7:
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            // this is the key behind fragments here
            fragmentManager.beginTransaction()                  // tell it it will have a transaction
                    .replace(R.id.frame_container, fragment)    // tell it to swap
                    .commit();                                  // tell it do it

            // update selected item and title, close drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e("MainActivity", "Error creating Fragment");
        }

    }

    @Override
    public void setTitle(CharSequence title){
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

//---------------------------------------------------------------------
//     When using ActionBarDrawerToggle, you must call it during
//      onPostCreate() and onConfigurationChanged()
//---------------------------------------------------------------------

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        // sync the toggle state after onRestore occurs
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        // pass any configuration to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
