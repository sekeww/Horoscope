package kz.sekeww.www.kundeliktizhuldyzzhoramaly;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static kz.sekeww.www.kundeliktizhuldyzzhoramaly.Details.LOG_TAG;
import static kz.sekeww.www.kundeliktizhuldyzzhoramaly.MainActivity.imgid;
import static kz.sekeww.www.kundeliktizhuldyzzhoramaly.MainActivity.itemname;

public class CompAbout extends AppCompatActivity {

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
    private LinearLayout sample_root;

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

    private TextView plusSign;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_about);

        Intent intent = getIntent();

        sample_root = (LinearLayout) findViewById(R.id.sample_root);

        leftLayout = (RelativeLayout) findViewById(R.id.leftLayout);
        rightLayout = (RelativeLayout) findViewById(R.id.rightLayout);

        leftHoroscopeImageView = (RoundedImageView) findViewById(R.id.leftHoroscopeImageView);
        rightHoroscopeImageView = (RoundedImageView) findViewById(R.id.rightHoroscopeImageView);

        genderLeftImageView = (ImageView) findViewById(R.id.genderLeftImageView);
        genderRightImageView = (ImageView) findViewById(R.id.genderRightImageView);

        leftHoroscopeTextView = (TextView) findViewById(R.id.leftHoroscopeTextView);
        rightHoroscopeTextView = (TextView) findViewById(R.id.rightHoroscopeTextView);

        plusSign = (TextView) findViewById(R.id.plusSign);
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
        leftImagePos = imgid[+intent.getIntExtra("leftImPos",0)];
        leftHoroscopeImageView.setImageResource(leftImagePos);

        rightImagePos = imgid[+intent.getIntExtra("rightImPos",0)];
        rightHoroscopeImageView.setImageResource(rightImagePos);

        leftLayout.setScaleX((float) 0.833333);
        leftLayout.setScaleY((float) 0.833333);
        rightLayout.setScaleX((float) 0.833333);
        rightLayout.setScaleY((float) 0.833333);

        leftHoroscopeTextView.setText(intent.getStringExtra("leftText"));
        rightHoroscopeTextView.setText(intent.getStringExtra("rightText"));

        if (isLeftImgFemale){
            dataTableName = "compatibility" + Arrays.asList(itemname).indexOf(leftHoroscopeTextView.getText());
            resname = Arrays.asList(itemname).indexOf(rightHoroscopeTextView.getText());

        } else {
            dataTableName = "compatibility" + Arrays.asList(itemname).indexOf(rightHoroscopeTextView.getText());
            resname = Arrays.asList(itemname).indexOf(leftHoroscopeTextView.getText());
        }
        Log.d("my_log","My array number is: !!! " + Arrays.asList(itemname).indexOf(rightHoroscopeTextView.getText()) + "\n resname is: " + resname);

        requestData(dataTableName,resname);

    }

    private void requestData(String dataTableName, final int resName) {
        String url = "http://gregarious.kz/index.php/main/get_json_"+dataTableName;

        RequestQueue queue = Volley.newRequestQueue(this);
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("my_log",response.toString());
                if(response.length()>0){
                    try {
                        JSONObject zodiakObject = response.getJSONObject(resName);
                        zodiakId = zodiakObject.getInt("id");
                        zodiakPercentage = zodiakObject.getInt("percentage");
                        zodiakMarriage = zodiakObject.getInt("marriage");
                        zodiakMarriageDesc = zodiakObject.getString("marriage_desc");
                        zodiakLuck = zodiakObject.getInt("luck");
                        zodiakLuckDesc = zodiakObject.getString("luck_desc");
                        zodiakSexual = zodiakObject.getInt("sexual");
                        zodiakSexualDesc = zodiakObject.getString("sexual_desc");
                        zodiakWealth = zodiakObject.getInt("wealth");
                        zodiakWealthDesc = zodiakObject.getString("wealth_desc");
                        zodiakChildren = zodiakObject.getInt("children");
                        zodiakChildrenDesc = zodiakObject.getString("children_desc");


                        Log.d(LOG_TAG,"zodiak object is "+zodiakPercentage);
//                        Log.d(LOG_TAG,"zodiak name is "+zodiakName);
//                        tabLayout.setupWithViewPager(viewPager);
                        setupTextView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(sample_root, "Internet parsing problems!", Snackbar.LENGTH_INDEFINITE);

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
//                Toast.makeText(getApplicationContext(),
//                        "No internet connection!!!", Toast.LENGTH_LONG)
//                        .show();
            }
        });

        queue.add(request);

    }

    private void setupTextView() {
        plusSign.setText(zodiakPercentage + "%");
        textViewMarriage.setText("Тұрсмыстағы бақыт: " + zodiakMarriage);
        textViewMarriageDesc.setText(zodiakMarriageDesc);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Compitability.class));
        finish();
    }
}
