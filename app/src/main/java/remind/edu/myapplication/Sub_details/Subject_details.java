package remind.edu.myapplication.Sub_details;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import it.sauronsoftware.ftp4j.FTPClient;
import remind.edu.myapplication.R;

public class Subject_details extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView subname;
    CircleImageView imv;
    Typeface buttonfont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        String sub_name=getIntent().getStringExtra("subname");
        String imgurl=getIntent().getStringExtra("Image_url");
        subname=(TextView)findViewById(R.id.tv_subj_name);
        subname.setText(sub_name);
       buttonfont = Typeface.createFromAsset(getAssets(), "fonts/DIN_Alternate_Bold.ttf");
        imv=(CircleImageView)findViewById(R.id.profile_image);
        Glide.with(Subject_details.this).load(imgurl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imv);

        Typeface hintfont = Typeface.createFromAsset(getAssets(), "fonts/Bethanie_Snake_PersonalUseOnly.ttf");
        subname.setTypeface(hintfont);
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("DOCUMENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("VIDEOS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        changeTabsFont();
        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(buttonfont);
                }
            }
        }
    }
}

