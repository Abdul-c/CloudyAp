package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends AppCompatActivity {
    //    private CustomPerson person;
    public static final int HOME_TAB_ID = 0;
    //    private ViewPager viewPager;
    public static final int ADD_IMAGE_TAB_ID = 1;
    public static final int SEARCH_TAB_ID = 2;
    public  static  final  int CHAT_TAB_ID =3;
    public static final int DEFAULT_TAB_ID = -1;
    private static final int UPLOAD_REQ = 1;
    private static final String CURRENT_SATET_TAG = "currentTabState";
    public static int currentTabState = DEFAULT_TAB_ID;
    public static String currentUserId;
    public static ContentResolver cr;
    public static PackageManager pm;
    @SuppressLint("StaticFieldLeak")
    public static MainActivity self;
    public static FragmentManager fm;

    public static boolean isDirectedToOtherProfiles = false;
    private ImageView addButton, searchButton, chatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initials();
        onClickListeners();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }

    private void initials() {
        self = this;
        pm = getPackageManager();
        cr = getContentResolver();
        fm = getSupportFragmentManager();
        addButton = findViewById(R.id.add_tab);
        searchButton = findViewById(R.id.search_tab);
        chatButton=findViewById(R.id.chat_tab);


    }

    private void onClickListeners() {


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeBackOtherImageResources(ADD_IMAGE_TAB_ID)) {
                    Intent intent = new Intent(getApplicationContext(), upload.class);
                    startActivityForResult(intent, UPLOAD_REQ);
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeBackOtherImageResources(SEARCH_TAB_ID)) {
                    Intent intent = new Intent(getApplicationContext(), browsing.class);
                    startActivityForResult(intent, UPLOAD_REQ);
                }
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (changeBackOtherImageResources(CHAT_TAB_ID)) {
                    Intent intent = new Intent(getApplicationContext(), chat.class);
                    startActivityForResult(intent, UPLOAD_REQ);
                }
            }
        });


    }



    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(CURRENT_SATET_TAG, currentTabState);
    }

    /**
     * @param pressedIconState Id of pressed icon.
     * @return True if the user pressed a new icon, false if the user pressed the current tab icon.
     */
    private boolean changeBackOtherImageResources(int pressedIconState) {
        int preTabState = currentTabState;
        currentTabState = pressedIconState;
        if (preTabState == currentTabState)
            return false;
        switch (preTabState) {

            case ADD_IMAGE_TAB_ID:
//                addButton.setImageResource(R.drawable.plus_icon_stroke);
                break;

            case SEARCH_TAB_ID:
                searchButton.setImageResource(R.drawable.search_icon_stroke);
                break;

            default:
                break;
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentTabState = -1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPLOAD_REQ:
                if (requestCode == RESULT_OK) {

                }
                break;
        }
    }
    public void searching(View view){
        Intent homeIntent=new Intent(MainActivity.this, search.class);
        startActivity(homeIntent);
    }
}
