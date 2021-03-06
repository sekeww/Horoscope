package kz.sekeww.www.kundeliktizhuldyzzhoramaly.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import kz.sekeww.www.kundeliktizhuldyzzhoramaly.AlertDialogFragment;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.Compatibility;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class CompAboutActivity extends AppCompatActivity {

    private static final String TAG = CompAboutActivity.class.getSimpleName();

    public int leftImagePos;
    public int rightImagePos;

    private RoundedImageView leftHoroscopeImageView;
    private RoundedImageView rightHoroscopeImageView;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;
    private ImageView genderLeftImageView;
    private ImageView genderRightImageView;
    private TextView leftHoroscopeTextView;
    private TextView rightHoroscopeTextView;

    private boolean isLeftImgFemale = false;
    private String dataTableName;
    private int resname;
    private RelativeLayout sample_root;

    private ShareActionProvider mShareActionProvider;

    private int zodiakId;
    private int zodiakPercentage;

    private int zodiakMarriage;
    private String zodiakMarriageDesc;
    private int zodiakLuck;
    private String zodiakLuckDesc;
    private int zodiakSexual;
    private String zodiakSexualDesc;
    private int zodiakWealth;
    private String zodiakWealthDesc;
    private int zodiakChildren;
    private String zodiakChildrenDesc;

    private TextView mPlusSignTextView;
    private TextView textViewMarriage;
    private TextView textViewMarriageDesc;
    private TextView textViewLuck;
    private TextView textViewLuckDesc;
    private TextView textViewSexual;
    private TextView textViewSexualDesc;
    private TextView textViewWealth;
    private TextView textViewWealthDesc;
    private TextView textViewChildren;
    private TextView textViewChildrenDesc;

    private Compatibility mCompatibility;
    private int mIndexCompat = 0;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_about);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Жарасымдылық");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().addTestDevice("191E77D0E7000A3554E4F1A21D2455D0").addTestDevice("CB7C73537FF9BDF22D9D764D52779D44").build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();

        sample_root = (RelativeLayout) findViewById(R.id.sample_root);

        leftLayout = (RelativeLayout) findViewById(R.id.leftLayout);
        rightLayout = (RelativeLayout) findViewById(R.id.rightLayout);

        leftHoroscopeImageView = (RoundedImageView) findViewById(R.id.leftHoroscopeImageView);
        rightHoroscopeImageView = (RoundedImageView) findViewById(R.id.rightHoroscopeImageView);

        genderLeftImageView = (ImageView) findViewById(R.id.genderLeftImageView);
        genderRightImageView = (ImageView) findViewById(R.id.genderRightImageView);

        leftHoroscopeTextView = (TextView) findViewById(R.id.leftHoroscopeTextView);
        rightHoroscopeTextView = (TextView) findViewById(R.id.rightHoroscopeTextView);

        mPlusSignTextView = (TextView) findViewById(R.id.plusSign);
        textViewMarriage = (TextView) findViewById(R.id.textViewMarriage);
        textViewMarriageDesc = (TextView) findViewById(R.id.textViewMarriageDesc);
        textViewLuck = (TextView) findViewById(R.id.textViewLuck);
        textViewLuckDesc = (TextView) findViewById(R.id.textViewLuckDesc);
        textViewChildren = (TextView) findViewById(R.id.textViewChildren);
        textViewChildrenDesc = (TextView) findViewById(R.id.textViewChildrenDesc);
        textViewSexual = (TextView) findViewById(R.id.textViewSexual);
        textViewSexualDesc = (TextView) findViewById(R.id.textViewSexualDesc);
        textViewWealth = (TextView) findViewById(R.id.textViewWealth);
        textViewWealthDesc = (TextView) findViewById(R.id.textViewWealthDesc);

        if (intent.getIntExtra("leftGenIm",0)==R.drawable.female){
            genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
            genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));

            isLeftImgFemale = true;
        } else{
            genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
            genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));

            isLeftImgFemale = false;
        }

        //получаем строку и формируем имя ресурса
        //Toast.makeText(getApplicationContext(), intent.getIntExtra("leftImPos",0)+"isZERO", Toast.LENGTH_SHORT).show();
        leftImagePos = MainActivity.imgid[+intent.getIntExtra("leftImPos",0)];
        leftHoroscopeImageView.setImageResource(leftImagePos);

        rightImagePos = MainActivity.imgid[+intent.getIntExtra("rightImPos",0)];
        rightHoroscopeImageView.setImageResource(rightImagePos);

        leftLayout.setScaleX((float) 0.833333);
        leftLayout.setScaleY((float) 0.833333);
        rightLayout.setScaleX((float) 0.833333);
        rightLayout.setScaleY((float) 0.833333);

        leftHoroscopeTextView.setText(intent.getStringExtra("leftText"));
        rightHoroscopeTextView.setText(intent.getStringExtra("rightText"));

        if (isLeftImgFemale){
            dataTableName = "compatibility" + Arrays.asList(MainActivity.itemname).indexOf(leftHoroscopeTextView.getText());
            resname = Arrays.asList(MainActivity.itemname).indexOf(rightHoroscopeTextView.getText());

        } else {
            dataTableName = "compatibility" + Arrays.asList(MainActivity.itemname).indexOf(rightHoroscopeTextView.getText());
            resname = Arrays.asList(MainActivity.itemname).indexOf(leftHoroscopeTextView.getText());
        }
        Log.d("my_log","My array number is: !!! " + Arrays.asList(MainActivity.itemname).indexOf(rightHoroscopeTextView.getText()) + "\n resname is: " + resname);

        //requestData(dataTableName,resname);
        getCompatibility(dataTableName);
    }


    private void getCompatibility(String dataTableName) {
        if (isNetworkAvailable()) {
            //toggleRefresh();

            String url = "http://gregarious.kz/index.php/main/get_json_" + dataTableName;
            OkHttpClient client = new OkHttpClient();
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, okhttp3.Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //toggleRefresh();
                        }
                    });

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mCompatibility = getCompatDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                    catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        } else {
            Toast.makeText(this, R.string.network_unavailable , Toast.LENGTH_LONG).show();
        }
    }

    private void updateDisplay() {
        mPlusSignTextView.setText(mCompatibility.getPercentage() + "%");

        textViewMarriage.setText(mCompatibility.getFormattedMarriage());
        textViewLuck.setText(mCompatibility.getFormattedLuck());
        textViewChildren.setText(mCompatibility.getFormattedChildren());
        textViewSexual.setText(mCompatibility.getFormattedSexual());
        textViewWealth.setText(mCompatibility.getFormattedWealth());


        textViewMarriageDesc.setText(mCompatibility.getMarriageDesc());
        textViewLuckDesc.setText(mCompatibility.getLuckDesc());
        textViewSexualDesc.setText(mCompatibility.getSexualDesc());
        textViewWealthDesc.setText(mCompatibility.getWealthDesc());
        textViewChildrenDesc.setText(mCompatibility.getChildrenDesc());

        mShareActionProvider.setShareIntent(createShareIntent());
    }

    private Compatibility getCompatDetails(String jsonData) throws JSONException {
        JSONArray zodiakArray = new JSONArray(jsonData);
        JSONObject zodiakObject = zodiakArray.getJSONObject(resname);

        Log.d(TAG, "oject or array?: " + zodiakArray);
        Log.d(TAG, "oject or array?: " + zodiakObject);

        Compatibility compatibility = new Compatibility();

        compatibility.setPercentage(zodiakObject.getInt("percentage"));
        compatibility.setMarriage(zodiakObject.getInt("marriage"));
        compatibility.setMarriageDesc(zodiakObject.getString("marriage_desc"));
        compatibility.setLuck(zodiakObject.getInt("luck"));
        compatibility.setLuckDesc(zodiakObject.getString("luck_desc"));
        compatibility.setSexual(zodiakObject.getInt("sexual"));
        compatibility.setSexualDesc(zodiakObject.getString("sexual_desc"));
        compatibility.setChildren(zodiakObject.getInt("children"));
        compatibility.setChildrenDesc(zodiakObject.getString("children_desc"));
        compatibility.setWealth(zodiakObject.getInt("wealth"));
        compatibility.setWealthDesc(zodiakObject.getString("wealth_desc"));

        return compatibility;
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, CompChooseActivity.class));
        finish();
    }

//    private Intent createShareIntent() {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, text.getText() + "\n\n" + date.getText() + "\n@" +
//                getString(R.string.app_name) + "\n\n" + "https://play.google.com/store/apps/details?id=kz.sekeww.www.kundeliktizhuldyzzhoramaly");
//        sendIntent.setType("text/plain");
//        return Intent.createChooser(sendIntent, "Share menu");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        MenuItem item_compat = menu.findItem(R.id.menu_item_compat);
        item_compat.setVisible(false);
        item_compat.setEnabled(false);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        // Return true to display menu
        return true;
    }

    private String textCreator(){
        String string = " ";
        if (isLeftImgFemale) {
            string = leftHoroscopeTextView.getText() + "(Ә)" + " - " + rightHoroscopeTextView.getText() + "(Ұ)";
        }
        else {
            string = leftHoroscopeTextView.getText() + "(Ұ)" + " - " + rightHoroscopeTextView.getText() + "(Ә)";
        }
        return string;
    }

    private Intent createShareIntent() {

        TextView marriage = findViewById(R.id.textViewMarriage);
        TextView luck = findViewById(R.id.textViewLuck);
        TextView sexual = findViewById(R.id.textViewSexual);
        TextView wealth = findViewById(R.id.textViewWealth);
        TextView children = findViewById(R.id.textViewChildren);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                textCreator()+ "\n"
                        + "Жарасымдылық" + " " + mPlusSignTextView.getText() + "\n\n"
                        + marriage.getText() + "\n"
                        + luck.getText() + "\n"
                        + sexual.getText() + "\n"
                        + wealth.getText() + "\n"
                        + children.getText() +"\n\n@"
                        + getString(R.string.app_name) + "\n\n"
                        + "https://play.google.com/store/apps/details?id=kz.sekeww.www.kundeliktizhuldyzzhoramaly");
        sendIntent.setType("text/plain");
        return Intent.createChooser(sendIntent, "Share menu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CompChooseActivity.class));
                finish();
                break;
        }
        return true;
    }

}
