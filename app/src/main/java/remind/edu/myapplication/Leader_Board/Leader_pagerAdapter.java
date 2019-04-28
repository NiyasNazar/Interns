package remind.edu.myapplication.Leader_Board;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import remind.edu.myapplication.Sub_details.Documents;
import remind.edu.myapplication.Sub_details.Videos;

public class Leader_pagerAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public Leader_pagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               Mock_up homeFragment = new Mock_up();
                return homeFragment;
            case 1:
                Central sportFragment = new Central();
                return sportFragment;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}