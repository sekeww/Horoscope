package kz.sekeww.www.kundeliktizhuldyzzhoramaly.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import kz.sekeww.www.kundeliktizhuldyzzhoramaly.R;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.adapters.CustomListAdapter;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    GridView grid_view;

    private LinearLayout gridsLineaLayout;
    private LinearLayout aboutLinearLayout;
    private Drawer.Result drawerResult = null;

    private InterstitialAd interstitial;
    private int thePosition;

    public static String[] itemname ={
            "Тоқты",
            "Торпақ",
            "Егіздер",
            "Шаян",
            "Арыстан",
            "Бикеш",
            "Таразы",
            "Сарышаян",
            "Мерген",
            "Тауешкі",
            "Балықтар",
            "Суқұйғыш"
    };

    public static Integer[] imgid={
            R.drawable.apic1b,
            R.drawable.apic2b,
            R.drawable.apic3b,
            R.drawable.apic8b,
            R.drawable.apic5b,
            R.drawable.apic6b,
            R.drawable.apic7b,
            R.drawable.apic4b,
            R.drawable.apic9b,
            R.drawable.apic10b,
            R.drawable.apic12b,
            R.drawable.apic11b
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(),getResources().getString(R.string.ads_app_id));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            drawerResult = new Drawer()
                    .withActivity(this)
                    .withToolbar(toolbar)
                    .withActionBarDrawerToggle(true)
                    .withHeader(R.layout.drawer_header)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(2),
                            new PrimaryDrawerItem().withName(R.string.drawer_item_compatibility).withIcon(FontAwesome.Icon.faw_heart).setEnabled(true).withIdentifier(3),
                            new DividerDrawerItem(),
                            new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(1),
                            new SecondaryDrawerItem().withName(R.string.drawer_item_rate).withIcon(FontAwesome.Icon.faw_youtube_play).withIdentifier(70)
                    ).withOnDrawerItemClickListener(handlerOnClick(drawerResult,MainActivity.this))
                    .withOnDrawerListener(new Drawer.OnDrawerListener() {
                        @Override
                        public void onDrawerOpened(View drawerView) {
                            // Скрываем клавиатуру при открытии Navigation Drawer
                            InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
                        }

                        @Override
                        public void onDrawerClosed(View drawerView) {

                        }
                    })
                    .build();

            gridsLineaLayout = (LinearLayout) findViewById(R.id.gridsLineaLayout);
            aboutLinearLayout = (LinearLayout) findViewById(R.id.aboutLinearLayout);

            CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
            grid_view=(GridView) findViewById(R.id.grid_view);
            grid_view.setTextFilterEnabled(true);
            grid_view.setAdapter(adapter);

            grid_view.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    thePosition = position;
                    if (interstitial.isLoaded()) {
                        interstitial.show();
                    } else {
                        Log.d(TAG, "The interstitial wasn't loaded yet.");
                        onGridItemClick(position);
                    }

                }
            });

        interstitial = new InterstitialAd(getApplicationContext());
        interstitial.setAdUnitId(getResources().getString(R.string.ads_interstitialBanner_id));
        requestNewInterstitial();

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                requestNewInterstitial();
                onGridItemClick(thePosition);
            }

        });
        }

    private void onGridItemClick(int position){
        String Slecteditem = itemname[+position];
//                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DetailsActivity.class);

        intent.putExtra("head", position);

        //запускаем вторую активность
        startActivity(intent);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest1 = new AdRequest.Builder().addTestDevice("191E77D0E7000A3554E4F1A21D2455D0").addTestDevice("CB7C73537FF9BDF22D9D764D52779D44").build();
        interstitial.loadAd(adRequest1);
    }

    @Override
    public void onBackPressed(){
        if(drawerResult.isDrawerOpen()){
            drawerResult.closeDrawer();
        } else if (gridsLineaLayout.getVisibility()==View.VISIBLE){
            finish();
        }
        else{
            gridsLineaLayout.setVisibility(View.VISIBLE);
            aboutLinearLayout.setVisibility(View.GONE);
        }
    }

    @Override
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

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public Drawer.OnDrawerItemClickListener handlerOnClick(final Drawer.Result drawerResult, final AppCompatActivity activity) {
        return new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                //check if the drawerItem is set.
                //there are different reasons for the drawerItem to be null
                //--> click on the header
                //--> click on the footer
                //those items don't contain a drawerItem

                if (drawerItem != null) {

                    Intent intent = null;

                    if (drawerItem.getIdentifier() == 1) {
                        gridsLineaLayout.setVisibility(View.GONE);
                        aboutLinearLayout.setVisibility(View.VISIBLE);
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Fragment1()).commit();
                    }
                    else if (drawerItem.getIdentifier() == 2) {
                        gridsLineaLayout.setVisibility(View.VISIBLE);
                        aboutLinearLayout.setVisibility(View.GONE);
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new Fragment1()).commit();
                    }
                    else if (drawerItem.getIdentifier() == 3) {
                        intent = new Intent(MainActivity.this, CompChooseActivity.class);
                    }
                    else if (drawerItem.getIdentifier() == 70) {
                        // Rate App
                        try {

                            Intent int_rate = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getApplicationContext().getPackageName()));
                            int_rate.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.getApplicationContext().startActivity(int_rate);
                        } catch (Exception e) {
                            //
                        }
                    }
                    if (intent != null) {
                        MainActivity.this.startActivity(intent);
                    }
                }
            }
        };
    }

}