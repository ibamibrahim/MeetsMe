package id.meetsme.meetsme.navigationactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.main.MainActivity;
import id.meetsme.meetsme.messagelist.MessageListActivity;
import id.meetsme.meetsme.myprofile.MyProfileActivity;
import id.meetsme.meetsme.services.LocalServices;

public class NavigationActivity extends BaseActivity {

    private static final String TAG = "NavigationActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        isLoggedIn();
        initUI();
        logDetail();
    }

    private void logDetail() {
        Log.i(TAG, "token: " + LocalServices.getToken(getApplicationContext()));
        Log.i(TAG, "userdetail: " + LocalServices.getUserDetail(getApplicationContext()));
        Log.i(TAG, "userId: " + LocalServices.getUserId(getApplicationContext()));
        Log.i(TAG, "userInterest: " + LocalServices.getUserInterest(getApplicationContext()));
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_firsttab),
                        R.color.colorPrimaryDark)
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_firsttab))
                        .title("Message")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_secondtab),
                        R.color.colorPrimaryDark)
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_secondtab))
                        .title("Search people")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_thirdtab),
                        R.color.colorPrimaryDark)
                        .selectedIcon(getResources().getDrawable(R.drawable.ic_thirdtab))
                        .title("My profile")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 1);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 10);
                }
            }
        }, 500);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    MessageListActivity tab1 = new MessageListActivity();
                    return tab1;
                case 1:
                    MainActivity tab2 = new MainActivity();
                    return tab2;
                case 2:
                    MyProfileActivity tab3 = new MyProfileActivity();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show n total pages.
            return 3;
        }
    }
}
