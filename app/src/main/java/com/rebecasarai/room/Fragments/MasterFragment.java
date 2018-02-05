package com.rebecasarai.room.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    MainActivityVM mViewModel;
    TeamAdaptera mTeamAdapater;
    ListView mList;
    View rootView;
    TextView texto;

    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //mList= (ListView)getActivity().findViewById(R.id.lista);

        rootView = inflater.inflate(R.layout.fragment_master2, container, false);

        mViewModel = ViewModelProviders.of(this).get(MainActivityVM.class);
        mList = (ListView)rootView.findViewById(R.id.lista);

        mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> teams) {

                //mTeams = teams;
                for (int i = 0; i < teams.size(); i++) {
                    Log.v("Team: ", teams.get(i).toString());
                }
                mTeamAdapater = new TeamAdaptera(getContext(), mViewModel, teams);

                //mTeamAdapater.setData(teams);
                mList.setAdapter(mTeamAdapater);

            }
        });


        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //mList = (ListView) getView().findViewById(R.id.lista);

        /*mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> teams) {

                //mTeams = teams;
                for (int i = 0; i < teams.size(); i++) {
                    Log.v("Team: ", teams.get(i).toString());
                }
                mTeamAdapater = new TeamAdaptera(getContext(), mViewModel, teams);

                mTeamAdapater.setData(teams);
                mList.setAdapter(mTeamAdapater);

            }
        });*/
    }
}
