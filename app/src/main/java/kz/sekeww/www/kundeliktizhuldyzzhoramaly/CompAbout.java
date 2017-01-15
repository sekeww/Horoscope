package kz.sekeww.www.kundeliktizhuldyzzhoramaly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import static kz.sekeww.www.kundeliktizhuldyzzhoramaly.MainActivity.imgid;
import static kz.sekeww.www.kundeliktizhuldyzzhoramaly.MainActivity.itemname;

public class CompAbout extends AppCompatActivity {

    public int leftImagePos;
    public int rightImagePos;

    private RoundedImageView leftHoroscopeImageView;
    private RoundedImageView rightHoroscopeImageView;
    private RelativeLayout leftLayout;
    private RelativeLayout rightLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_about);

        leftHoroscopeImageView = (RoundedImageView) findViewById(R.id.leftHoroscopeImageView);
        rightHoroscopeImageView = (RoundedImageView) findViewById(R.id.rightHoroscopeImageView);

        Intent intent = getIntent();
        //получаем строку и формируем имя ресурса
        //Toast.makeText(getApplicationContext(), intent.getIntExtra("leftImPos",0)+"isZERO", Toast.LENGTH_SHORT).show();
        leftImagePos = imgid[+intent.getIntExtra("leftImPos",0)];
        leftHoroscopeImageView.setImageResource(leftImagePos);

        rightImagePos = imgid[+intent.getIntExtra("rightImPos",0)];
        rightHoroscopeImageView.setImageResource(rightImagePos);

        leftLayout = (RelativeLayout) findViewById(R.id.leftLayout);
        rightLayout = (RelativeLayout) findViewById(R.id.rightLayout);

        leftLayout.setScaleX((float) 0.833333);
        leftLayout.setScaleY((float) 0.833333);
        rightLayout.setScaleX((float) 0.833333);
        rightLayout.setScaleY((float) 0.833333);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Compitability.class));
        finish();
    }
}
