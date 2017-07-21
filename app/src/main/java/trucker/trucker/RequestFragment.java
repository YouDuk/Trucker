package trucker.trucker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RequestFragment extends Fragment {
    RequestDialog requestDialog;
    ArrayList<Truck> trucks_list = new ArrayList<>();
    ListView trucks_list_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_request, container, false);

        for(int i=0; i<10; i++) {
            trucks_list.add(new Truck(R.drawable.fragment_request_default, "프라임 토스트", "오늘 10시 이동할 예정입니다 감사합니다."));
            trucks_list.add(new Truck(R.drawable.fragment_request_default, "타코엔 타코", "감사합니다. 다음 주 방문 예정입니다."));
            trucks_list.add(new Truck(R.drawable.fragment_request_default, "오늘의 공구상", "오후 3시 출발하겠습니다."));
            trucks_list.add(new Truck(R.drawable.fragment_request_default, "요쿠르트", "지금 정류장앞에 있습니다."));
        }

        TruckListAdapter truckListAdapter = new TruckListAdapter(trucks_list, inflater);
        trucks_list_view = (ListView) view.findViewById(R.id.truck_list);
        trucks_list_view.setAdapter(truckListAdapter);
        trucks_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                requestDialog.show();
            }
        });


        requestDialog = new RequestDialog(getContext(), getActivity());



        return view;
    }


    public class TruckListAdapter extends BaseAdapter{
        ArrayList<Truck> data_list;
        LayoutInflater inflater;
        TruckListAdapter(ArrayList<Truck> data_list, LayoutInflater inflater){
            this.data_list=data_list;
            this.inflater = inflater;
        }
        @Override
        public int getCount() {
            return data_list.size();
        }

        @Override
        public Truck getItem(int i) {
            return data_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.truck_list_item, viewGroup, false);

            Truck item = data_list.get(i);

            TextView name = (TextView) view.findViewById(R.id.name);
            TextView message = (TextView) view.findViewById(R.id.message);

            name.setText(item.name);
            message.setText(item.message);


            return view;
        }
    }


}

