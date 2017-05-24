package org.tallythevote;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class PoliticianFragment extends Fragment {

    private List<Politician> mPoliticianList;
    private RecyclerView.LayoutManager mLayoutManager;
    private PoliticianFragmentAdapter mPoliticianFragmentAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    private void getData() {
        mPoliticianList = new ArrayList<>();

        mPoliticianList.add(new Politician(0001, "John Smaligo",
                "Commissioner District 1", "County","918-596-5020 ",
                "jsmaligo@tulsacounty.org ",
                "In 2006 John decided to put his legislative experience to use in local\n" +
                        "government and made the decision to run for Tulsa County Commissioner. The first \n" +
                        "Republican to ever hold the District 1 seat, John believes that his bipartisan approach \n" +
                        "in the legislature will enable him to better represent the most diverse district in Tulsa \n" +
                        "County."));
        mPoliticianList.add(new Politician(0002, "Karen Keith",
                "Commissioner District 2", "County", "918-596-5016",
                "kkeith@tulsacounty.org ", "Description Coming Soon!"));
        mPoliticianList.add(new Politician(0003, "Ron Peters",
                "Commissioner District 3", "County", "918-596-5010",
                "rpeters@tulsacounty.org", "Description Coming Soon!"));
        mPoliticianList.add(new Politician(0004, "Ken Yazel",
                "Assessor", "County", "918-596-5100 ",
                "assessor@tulsacounty.org ", "Description Coming Soon!"));
        mPoliticianList.add(new Politician(0005, "Michael Willis",
                "County Clerk", "County", "918-596-5801",
                "mwillis@tulsacounty.org", "Description Coming Soon!"));

        mPoliticianList.get(0).setPoliticianImage(org.tallythevote.R.mipmap.ic_smaligo__c);
        mPoliticianList.get(1).setPoliticianImage(org.tallythevote.R.mipmap.ic_keith);
        mPoliticianList.get(2).setPoliticianImage(org.tallythevote.R.mipmap.ic_peters);
        mPoliticianList.get(3).setPoliticianImage(org.tallythevote.R.mipmap.ic_yazel);
        mPoliticianList.get(4).setPoliticianImage(org.tallythevote.R.mipmap.ic_willis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(org.tallythevote.R.layout.fragment_current_politicians, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(org.tallythevote.R.id.politicianRecyclerView);

        mLayoutManager = new GridLayoutManager(getActivity(), 2);

        setRecyclerViewLayoutManager();

        mPoliticianFragmentAdapter = new PoliticianFragmentAdapter(mPoliticianList);
        mRecyclerView.setAdapter(mPoliticianFragmentAdapter);

        return rootView;
    }

    public void setRecyclerViewLayoutManager(){
        int scrollPosition = 0;

        if(mRecyclerView.getLayoutManager() != null){
            scrollPosition = ((GridLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
