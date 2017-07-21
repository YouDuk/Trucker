package trucker.trucker;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;

public class MyTruckFragment extends Fragment {

    ArrayList<Integer> trucks_list = new ArrayList<>();
    GridView trucks_list_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_my_truck, container, false);

        for(int i=0; i<10; i++) {
            trucks_list.add(R.drawable.fragment_my_truck_a);
            trucks_list.add(R.drawable.fragment_my_truck_b);
            trucks_list.add(R.drawable.fragment_my_truck_c);
            trucks_list.add(R.drawable.fragment_my_truck_b);
            trucks_list.add(R.drawable.fragment_my_truck_c);
            trucks_list.add(R.drawable.fragment_my_truck_a);
        }

        AttentionTruckListAdapter truckListAdapter = new AttentionTruckListAdapter(trucks_list, inflater, getContext());
        trucks_list_view = (GridView) view.findViewById(R.id.attention_trucks_list);
        trucks_list_view.setAdapter(truckListAdapter);



        UltraViewPager myTruckViewPager = (UltraViewPager) view.findViewById(R.id.my_truck_viewpager);
        myTruckViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);

        //initialize HomePagerAdapter，and add child view to UltraViewPager
        MyTruckPagerAdapter adapter = new MyTruckPagerAdapter(false);
        myTruckViewPager.setAdapter(adapter);

//        //initialize built-in indicator
//        myTruckViewPager.initIndicator();
//
//        //set style of indicators
//        myTruckViewPager.getIndicator()
//                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
//                .setFocusColor(R.color.colorPrimary)
//                .setNormalColor(Color.WHITE)
//                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
//
//
////        ultraViewPager.setAutoMeasureHeight(true);
//
//        //set the alignment
//        myTruckViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
//        //construct built-in indicator, and add it to  UltraViewPager
//        myTruckViewPager.getIndicator().build();

        //set an infinite loop
        myTruckViewPager.setInfiniteLoop(true);
        //enable auto-scroll mode
        myTruckViewPager.setAutoScroll(5000);


        return view;
    }

    public class AttentionTruckListAdapter extends BaseAdapter {
        ArrayList<Integer> data_list;
        LayoutInflater inflater;
        Context con;
        AttentionTruckListAdapter(ArrayList<Integer> data_list, LayoutInflater inflater, Context con){
            this.data_list=data_list;
            this.inflater = inflater;
            this.con = con;
        }
        @Override
        public int getCount() {
            return data_list.size();
        }

        @Override
        public Integer getItem(int i) {
            return data_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.attention_truck_list_item, viewGroup, false);

            Integer item = data_list.get(i);

            ImageView image = (ImageView) view.findViewById(R.id.image);
            Glide.with(con).load(item).into(image);



            return view;
        }
    }

}

