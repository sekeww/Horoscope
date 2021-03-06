package kz.sekeww.www.kundeliktizhuldyzzhoramaly.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kz.sekeww.www.kundeliktizhuldyzzhoramaly.R;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.fragments.Daily;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.fragments.Tomorrow;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.fragments.Monthly;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.fragments.Year;

public class DetailsActivity extends AppCompatActivity {

    public static String LOG_TAG = "my_log";
    int k = 0;

    private InterstitialAd interstitial;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView mTitleTextView;
    public int resName;
    private String zodiakName;
    private String zodiakDescriptionToday;
    private String zodiakDescriptionTomorrow;
    private String zodiakDescriptionWeek;
    private String zodiakDescriptionYear;
    private ViewPagerAdapter adapter;
    private int mPage;

    private RelativeLayout relativeLayout;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().addTestDevice("191E77D0E7000A3554E4F1A21D2455D0").addTestDevice("CB7C73537FF9BDF22D9D764D52779D44").build();
        mAdView.loadAd(adRequest);



//        interstitial = new InterstitialAd(getApplicationContext());
//        interstitial.setAdUnitId(getResources().getString(R.string.ads_interstitialBanner_id));
//
//        interstitial.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdClosed() {
//                requestNewInterstitial();
////                onPeopleClick(thePosition);
//            }
//        });
//
//        requestNewInterstitial();
        Intent intent = getIntent();
        //получаем строку и формируем имя ресурса
        resName = intent.getIntExtra("head", 0);

//        Toast.makeText(getApplicationContext(),
//                "Position :"+resName , Toast.LENGTH_LONG)
//                .show();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Жұлдыз Жорамалы");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mTitleTextView = findViewById(R.id.textTitle);
        mTitleTextView.setVisibility(View.INVISIBLE);

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            // optional
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            // optional
            @Override
            public void onPageSelected(int position) {
                mPage = position;
                if (position == 3){
                    mTitleTextView.setVisibility(View.GONE);
                }
                else {
                    mTitleTextView.setVisibility(View.VISIBLE);
                }
            }
            // optional
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        String url = "http://gregarious.kz/index.php/main/get_json_horoscope";

        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(LOG_TAG,response.toString());
                if(response.length()>0){
                    try {
                        JSONObject zodiakObject = response.getJSONObject(resName);
                        zodiakName = zodiakObject.getString("name");
                        zodiakDescriptionToday = zodiakObject.getString("today");
                        zodiakDescriptionTomorrow = zodiakObject.getString("tomorrow");
                        zodiakDescriptionWeek = zodiakObject.getString("week");
                        zodiakDescriptionYear = zodiakObject.getString("year");

                        Log.d(LOG_TAG,"zodiak object is "+zodiakObject);
                        Log.d(LOG_TAG,"zodiak name is "+zodiakName);
                        setupViewPager(viewPager);
                        tabLayout.setupWithViewPager(viewPager);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(viewPager, "Internet parsing problems!", Snackbar.LENGTH_INDEFINITE);

// Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(LOG_TAG, error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "No internet connection!!!", Toast.LENGTH_LONG)
                        .show();
            }
        });

        queue.add(request);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Log.d("my_log_inviewPager","zodiak name is "+zodiakName);
        mTitleTextView.setVisibility(View.VISIBLE);
        mTitleTextView.setText("--" + zodiakName + "--");
        adapter.addFragment(new Daily().newInstance(zodiakName,zodiakDescriptionToday), "Бүгінгі");
        adapter.addFragment(new Tomorrow().newInstance(zodiakName,zodiakDescriptionTomorrow), "Ертеңгі");
        adapter.addFragment(new Monthly().newInstance(zodiakName,zodiakDescriptionWeek), "Айлық");
        adapter.addFragment(new Year().newInstance(zodiakName,zodiakDescriptionYear), "Жылдық");
        viewPager.setAdapter(adapter);

        if (adapter.getItem(mPage).getView()!=null) {
            mShareActionProvider.setShareIntent(createShareIntent());
        } else {
            Snackbar snackbar = Snackbar
                    .make(viewPager, "Your ViewPage is empty, please check the Internet!", Snackbar.LENGTH_INDEFINITE);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private Intent createShareIntent() {
            View view = adapter.getItem(mPage).getView();

            TextView text = view.findViewById(R.id.textDesc);
            TextView date = view.findViewById(R.id.dateTextView);

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text.getText() + "\n\n" + date.getText() + "\n@" +
                    getString(R.string.app_name) + "\n\n" + "https://play.google.com/store/apps/details?id=kz.sekeww.www.kundeliktizhuldyzzhoramaly");
            sendIntent.setType("text/plain");
            return Intent.createChooser(sendIntent, "Share menu");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);
        MenuItem item_compat = menu.findItem(R.id.menu_item_compat);
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.menu_item_compat:
                Intent intent = new Intent();
                intent.setClass(DetailsActivity.this, CompChooseActivity.class);
                startActivity(intent);
                item.setEnabled(false);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
