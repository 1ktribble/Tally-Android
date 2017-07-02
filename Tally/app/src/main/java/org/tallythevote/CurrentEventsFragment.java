package org.tallythevote;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CurrentEventsFragment extends Fragment {

    private List<Event> mEventList;
    private RecyclerView.LayoutManager mLayoutManager;
    private EventFragmentAdapter mEventFragmentAdapter;
    private RecyclerView mRecyclerView;

    private final String addressLink = "http://maps.google.com/?q=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {
        mEventList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 9);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 26);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.YEAR, 2017);
        calendar2.set(Calendar.MONTH, 3);
        calendar2.set(Calendar.DATE, 15);

        mEventList.add(new Event(00001, 74055,
                "Special Primary Election \nHouse District #75",
                "Early voting occurs Thursday, May 4 and Friday May 5 from 8 a.m. to 6 p.m.",
                calendar,
                "<a href='"+addressLink+"Owasso Community Center'>Owasso Community Center</a>"));

        mEventList.add(new Event(00002, 74055,
                "Annual School Runoff",
                "Election has completed",
                calendar2,
                "<a href='"+addressLink+"Owasso Community Center'>Owasso Community Center</a>"));

        mEventList.add(new Event(00003, 74055,
                "Meet your rep!",
                "Meet Rep. Tom Cole serving the Nth district of Oklahoma",
                calendar1,
                "<a href='"+addressLink+"111 N Main St, Owasso, OK 74055'>Owasso Town Hall</a>"));

        mEventList.add(new Event(0004, 74055,
                "Special General Election \nHouse District #75",
                "Register Now!",
                calendar,
                "<a href='"+addressLink+"Owasso Community Center'>Owasso Community Center</a>"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_current_events, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.eventRecyclerView);

        mLayoutManager = new GridLayoutManager(getActivity(), 1);

        setRecyclerViewLayoutManager();

        mEventFragmentAdapter = new EventFragmentAdapter(mEventList);
        mRecyclerView.setAdapter(mEventFragmentAdapter);

        return rootView;
    }

    private void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        if(mRecyclerView.getLayoutManager() != null){
            scrollPosition = ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
