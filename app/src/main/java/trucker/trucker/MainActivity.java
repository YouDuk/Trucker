package trucker.trucker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private Fragment home_fragement;
    private Fragment schedule_fragment;
    private Fragment request_fragment;
    private Fragment my_truck_fragment;

    public void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setCustomView(R.layout.actionbar_home);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setActionBar();

        home_fragement = new HomeFragment();
        schedule_fragment = new TruckersFragment();
        request_fragment = new RequestFragment();
        my_truck_fragment = new MyTruckFragment();


        pager = (ViewPager) findViewById(R.id.view_pager);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(home_fragement, "1");
        adapter.addFragment(schedule_fragment, "2");
        adapter.addFragment(request_fragment, "3");
        adapter.addFragment(my_truck_fragment, "4");

        pager.setAdapter(adapter); // 처음으로 0번째 Fragment 를 보여줍니다.
//        pager.setCurrentItem(0);



        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.activity_main_home_icon),
                        Color.parseColor("#FFFFFF")
                ).title("Home")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.activity_main_truckers_icon),
                        Color.parseColor("#FFFFFF")
                ).title("Truckers")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.activity_main_request_icon),
                        Color.parseColor("#FFFFFF")
                ).title("Request")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.activity_main_profile_icon),
                        Color.parseColor("#FFFFFF")
                ).title("My Truck")
                        .build()
        );

        navigationTabBar.setModels(models);


        navigationTabBar.setViewPager(pager, 0);
        navigationTabBar.setBadgeTitleColor(Color.WHITE);



    }


    public class MyAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyAdapter(FragmentManager fm)
        {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title)
        {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragments.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitles.get(position);
        }
    }


}

