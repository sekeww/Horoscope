package kz.sekeww.www.kundeliktizhuldyzzhoramaly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import static kz.sekeww.www.kundeliktizhuldyzzhoramaly.MainActivity.imgid;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_about);

        Intent intent = getIntent();

        leftLayout = (RelativeLayout) findViewById(R.id.leftLayout);
        rightLayout = (RelativeLayout) findViewById(R.id.rightLayout);

        leftHoroscopeImageView = (RoundedImageView) findViewById(R.id.leftHoroscopeImageView);
        rightHoroscopeImageView = (RoundedImageView) findViewById(R.id.rightHoroscopeImageView);

        genderLeftImageView = (ImageView) findViewById(R.id.genderLeftImageView);
        genderRightImageView = (ImageView) findViewById(R.id.genderRightImageView);

        leftHoroscopeTextView = (TextView) findViewById(R.id.leftHoroscopeTextView);
        rightHoroscopeTextView = (TextView) findViewById(R.id.rightHoroscopeTextView);

        if (intent.getIntExtra("leftGenIm",0)==R.drawable.female){
            genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
            genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
        } else{
            genderLeftImageView.setImageDrawable(getResources().getDrawable(R.drawable.male));
            genderRightImageView.setImageDrawable(getResources().getDrawable(R.drawable.female));
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
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Compitability.class));
        finish();
    }
}
