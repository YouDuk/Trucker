package trucker.trucker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        UltraViewPager homeViewPager = (UltraViewPager) view.findViewById(R.id.home_viewpager);
        homeViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

        //initialize HomePagerAdapter，and add child view to UltraViewPager
        PagerAdapter adapter = new HomePagerAdapter(false);
        homeViewPager.setAdapter(adapter);

//        //initialize built-in indicator
//        ultraViewPager.initIndicator();
//
//        //set style of indicators
//        ultraViewPager.getIndicator()
//                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setFocusColor(Color.GREEN)
//                .setNormalColor(Color.WHITE)
//                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));

        homeViewPager.setPageTransformer(false, new UltraDepthScaleTransformer());
        homeViewPager.setMultiScreen(0.6f);
        homeViewPager.setItemRatio(1.0f);
//        ultraViewPager.setAutoMeasureHeight(true);

        //set the alignment
//        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        //construct built-in indicator, and add it to  UltraViewPager
//        ultraViewPager.getIndicatorz  ().build();

        //set an infinite loop
        homeViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        homeViewPager.setAutoScroll(5000);





        return view;
    }




}

