package kz.sekeww.www.kundeliktizhuldyzzhoramaly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.makeramen.roundedimageview.RoundedImageView;

import kz.sekeww.www.kundeliktizhuldyzzhoramaly.R;
import kz.sekeww.www.kundeliktizhuldyzzhoramaly.adapters.CompatListAdapter;

/**
 * Created by Askhat on 12/23/2016.
 */
public class CompChooseActivity extends AppCompatActivity {

    private static final String TAG = CompChooseActivity.class.getSimpleName();
    private RadioButton manRB;
    private RadioButton womanRB;
    private ImageView genderLeftImageView;
    private ImageView genderRightImageView;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;
    private GridView grid_view;
    private TextView leftHoroscopeTextView;
    private TextView rightHoroscopeTextView;
    private TextView compatTextView;
    private RoundedImageView leftHoroscopeImageView;
    private RoundedImageView rightHoroscopeImageView;

    private Boolean isLeftClicked = true;
    private Boolean isFemale = true;
    private Boolean isLeftChosen = false;
    private Boolean isRightChosen = false;

    public Integer rightImagePos;
    public Integer leftImagePos;

    private Toolbar toolbar;

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_choose);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Жарасымдылық");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        interstitial = new InterstitialAd(getApplicationContext());
        interstitial.setAdUnitId(getResources().getString(R.string.ads_interstitialBanner_id));
        requestNewInterstitial();

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                requestNewInterstitial();
                onViewCompatButtonClick();
            }

        });

        isLeftClicked = true;
        isFemale = true;

        manRB = (RadioButton) findViewById(R.id.manRB);
        manRB.setOnClickListener(radioButtonClickListener);

        womanRB = (RadioButton) findViewById(R.id.womanRB);
        womanRB.setChecked(true);
        womanRB.setOnClickListener(radioButtonClickListener);

        genderLeftImageView = (ImageView) findViewById(R.id.genderLeftImageView);
        genderLeftImageView.setTag(R.drawable.female);
        genderRightImageView = (ImageView) findViewById(R.id.genderRightImageView);
        genderRightImageView.setTag(R.drawable.male);

        leftHoroscopeImageView = (RoundedImageView) findViewById(R.id.leftHoroscopeImageView);
        rightHoroscopeImageView = (RoundedImageView) findViewById(R.id.rightHoroscopeImageView);

        leftHoroscopeTextView = (TextView) findViewById(R.id.leftHoroscopeTextView);
        rightHoroscopeTextView = (TextView) findViewById(R.id.rightHoroscopeTextView);

        compatTextView = (TextView) findViewById(R.id.compatTextView);

        leftLayout = (RelativeLayout) findViewById(R.id.leftLayout);
        rightLayout = (RelativeLayout) findViewById(R.id.rightLayout);

        leftLayout.setScaleX((float) 1.2);
        leftLayout.setScaleY((float) 1.2);
        rightLayout.setScaleX((float) 0.833333);
        rightLayout.setScaleY((float) 0.833333);
        rightHoroscopeImageView.setAlpha((float) 0.5);

        leftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftLayoutClick();
            }
        });

        rightLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightLayoutClick();
            }
        });

        CompatListAdapter adapter=new CompatListAdapter(this, MainActivity.itemname, MainActivity.imgid);
        grid_view=(GridView) findViewById(R.id.grid_view);
        grid_view.setTextFilterEnabled(true);
        grid_view.setAdapter(adapter);

        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // TODO Auto-generated method stub
                String slecteditemText = MainActivity.itemname[+position];
                //Toast.makeText(getApplicationContext(), slecteditemText, Toast.LENGTH_SHORT).show();

                Integer slecteditemImage = MainActivity.imgid[+position];

                if (isLeftClicked) {
                    leftHoroscopeTextView.setText(slecteditemText);
                    leftHoroscopeImageView.setImageResource(slecteditemImage);
                    isLeftChosen = true;
                    leftImagePos = position;
                } else {
                    rightHoroscopeTextView.setText(slecteditemText);
                    rightHoroscopeImageView.setImageResource(slecteditemImage);
                    isRightChosen = true;
                    rightImagePos = position;
                }

                if (isLeftChosen && isRightChosen){
                    compatTextView.setVisibility(View.VISIBLE);
                }
            }
        });

        compatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitial.isLoaded()) {
                    interstitial.show();
                } else {
                    Log.d(TAG, "The interstitial wasn't loaded yet.");
                    onViewCompatButtonClick();
                }
            }
        });

    }

    private void onViewCompatButtonClick() {
        Log.d("MyApp", genderLeftImageView+"");

        //TODO: defineText is configured in a way that first key word comes from female plus the male zodiak type
        //it may help us to sort the objects in Json array using same keyword system in objects: 144 textTypes...
        //we are going to send defineText into the new intent??? in which Json array will be acquired

        if (isFemale){
            String defineText = (String) leftHoroscopeTextView.getText() + rightHoroscopeTextView.getText();
            //Toast.makeText(getApplicationContext(), defineText, Toast.LENGTH_SHORT).show();
        }
        else {
            String defineText = (String) rightHoroscopeTextView.getText() + leftHoroscopeTextView.getText();
            //Toast.makeText(getApplicationContext(), defineText, Toast.LENGTH_SHORT).show();
        }

        if(isLeftClicked){
            leftLayout.setScaleX((float) ((float) 1 / 1.2));
            leftLayout.setScaleY((float) ((float) 1 / 1.2));

//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Do something after 5s = 5000ms
//                            rightHoroscopeImageView.setAlpha((float) 1);
//                        }
//                    }, 1500);
        }
        else {
            rightLayout.setScaleX((float) ((float) 1 / 1.2));
            rightLayout.setScaleY((float) ((float) 1 / 1.2));

//                    leftHoroscopeImageView.setAlpha((float) 1);
        }

        Intent intent = new Intent(CompChooseActivity.this, CompAboutActivity.class);

        intent.putExtra("leftImPos",leftImagePos)
                .putExtra("rightImPos",rightImagePos)
                .putExtra("leftGenIm",(Integer) genderLeftImageView.getTag())
                .putExtra("leftText",leftHoroscopeTextView.getText())
                .putExtra("rightText",rightHoroscopeTextView.getText());

        startActivity(intent);
        finish();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest1 = new AdRequest.Builder().addTestDevice("191E77D0E7000A3554E4F1A21D2455D0").build();
        interstitial.loadAd(adRequest1);
    }

    private void onRightLayoutClick() {

        isLeftClicked = false;

        if((Integer) genderRightImageView.getTag() == R.drawable.female) {
            womanRB.setChecked(true);
        } else {
            manRB.setChecked(true);
        }
            rightLayout.setScaleX((float) 1.2);
            rightLayout.setScaleY((float) 1.2);

            rightHoroscopeImageView.setAlpha((float) 1);
            leftHoroscopeImageView.setAlpha((float) 0.5);

            leftLayout.setScaleX((float) ((float) 1 / 1.2));
            leftLayout.setScaleY((float) ((float) 1 / 1.2));
    }

    private void onLeftLayoutClick() {

        isLeftClicked = true;

        if((Integer)genderLeftImageView.getTag() == R.drawable.female) {
            womanRB.setChecked(true);
        } else {
            manRB.setChecked(true);
        }

            leftLayout.setScaleX((float) 1.2);
            leftLayout.setScaleY((float) 1.2);

            rightHoroscopeImageView.setAlpha((float) 0.5);
            leftHoroscopeImageView.setAlpha((float) 1);

            rightLayout.setScaleX((float) ((float) 1 / 1.2));
            rightLayout.setScaleY((float) ((float) 1 / 1.2));
//        Toast.makeText(getApplicationContext(), rightLayout.getScaleX()+"", Toast.LENGTH_SHORT).show();
    }

//    public int pxToDp(int px) {
//        DisplayMetrics displayMetrics = CompChooseActivity.this.getResources().getDisplayMetrics();
//        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
//        return dp;
//    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()) {
                case R.id.manRB:

                    if (isLeftClicked) {
                        isFemale = false;
                        genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
                        genderLeftImageView.setTag(R.drawable.male);
                        genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
                        genderRightImageView.setTag(R.drawable.female);
                    }
                    else {
                        isFemale = true;
                        genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
                        genderRightImageView.setTag(R.drawable.male);
                        genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
                        genderLeftImageView.setTag(R.drawable.female);
                    }
                        break;
                case R.id.womanRB:

                    if (isLeftClicked) {
                        isFemale = true;
                        genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
                        genderLeftImageView.setTag(R.drawable.female);
                        genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
                        genderRightImageView.setTag(R.drawable.male);
                    }
                    else {
                        isFemale = false;
                        genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
                        genderRightImageView.setTag(R.drawable.female);
                        genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
                        genderLeftImageView.setTag(R.drawable.male);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
