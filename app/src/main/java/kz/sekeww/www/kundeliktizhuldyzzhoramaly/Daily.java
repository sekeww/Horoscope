 package kz.sekeww.www.kundeliktizhuldyzzhoramaly;

 import android.os.Bundle;
 import android.support.v4.app.Fragment;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.LinearLayout;
 import android.widget.TextView;

 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.Random;

 /**
 * Created by Askhat on 4/12/2016.
 */
public class Daily extends Fragment {

    private static final String ARG_zNAME = "zname";
    private static final String ARG_zDESC_TODAY = "zdescToday";

    // TODO: Rename and change types of parameters
    private String zname;
    private String zodiakDescriptionToday;

    private int i1;
    private int i2;
    private int i3;

    public Daily() {
        // Required empty public constructor
    }

    public static Daily newInstance(String zname, String zodiakDescriptionToday) {

        Log.d("my_log_daily_instance","zodiak name is "+zname);

        Daily fragment = new Daily();
        Bundle args = new Bundle();
        args.putString(ARG_zNAME, zname);
        args.putString(ARG_zDESC_TODAY, zodiakDescriptionToday);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("my_log_oncreate","zodiak name is "+zname);

        if (getArguments() != null) {

            zname = getArguments().getString(ARG_zNAME);
            zodiakDescriptionToday = getArguments().getString(ARG_zDESC_TODAY);

            Log.d("my_log_oncreate","zodiak name is "+zname);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily, container, false);

        TextView titleTextView = (TextView) v.findViewById(R.id.textTitle);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.textDesc);
        TextView dateTextView = (TextView) v.findViewById(R.id.dateTextView);
        TextView businessTextView = (TextView) v.findViewById(R.id.businessTextView);
        TextView loveTextView = (TextView) v.findViewById(R.id.loveTextView);
        TextView healthTextView = (TextView) v.findViewById(R.id.healthTextView);
        LinearLayout imageContentLayout = (LinearLayout) v.findViewById(R.id.imageContentLayout);

        imageContentLayout.setVisibility(View.VISIBLE);

        Log.d("my_log_daily_ocview","zodiak name is "+zname);

        Calendar c = Calendar.getInstance();
        System.out.println("Current date => "+c.get(c.DATE));
        System.out.println("zname length => "+zname.toCharArray().length);
        switch (zname) {
            case "Тоқты": chooseith(c,zname);
                            break;
            case "Торпақ": chooseith(c,zname);
                break;
            case "Егіздер": chooseith(c,zname);
                break;
            case "Шаян": chooseith(c,zname);
                break;
            case "Арыстан": chooseith(c,zname);
                break;
            case "Бикеш": chooseith(c,zname);
                break;
            case "Таразы": chooseith(c,zname);
                break;
            case "Сарышаян": chooseith(c,zname);
                break;
            case "Мерген": chooseith(c,zname);
                break;
            case "Тауешкі": chooseith(c,zname);
                break;
            case "Балықтар": chooseith(c,zname);
                break;
            case "Суқұйғыш": chooseith(c,zname);
                break;
        }


        businessTextView.setText("Бизнес: "+i1);
        loveTextView.setText("Махаббат: "+i2);
        healthTextView.setText("Денсаулық: "+i3);


        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = df.format(c.getTime());

        titleTextView.setText(zname);
        descriptionTextView.setText(zodiakDescriptionToday);
        dateTextView.setText(formattedDate);

        return v;
    }

     private void chooseith(Calendar c, String zname) {
         switch ((c.get(c.DATE)+zname.toCharArray().length)%10) {

             case 1: i1=4;i2=5;i3=4;
                 break;
             case 2: i1=5;i2=5;i3=5;
                 break;
             case 3: i1=4;i2=4;i3=5;
                 break;
             case 4: i1=5;i2=3;i3=4;
                 break;
             case 5: i1=4;i2=4;i3=4;
                 break;
             case 6: i1=5;i2=4;i3=4;
                 break;
             case 7: i1=5;i2=4;i3=5;
                 break;
             case 8: i1=3;i2=5;i3=4;
                 break;
             case 9: i1=4;i2=5;i3=4;
                 break;
             case 0: i1=4;i2=5;i3=3;
                 break;
         }
     }
 }
