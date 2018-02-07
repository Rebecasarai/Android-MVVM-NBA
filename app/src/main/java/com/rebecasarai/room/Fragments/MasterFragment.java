package com.rebecasarai.room.Fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.FragmentsVM;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.Views.TeamAdapter;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements AdapterView.OnItemClickListener {

    FragmentsVM mViewModel;
    TeamAdapter mTeamAdapater;
    ListView mList;
    View rootView;
    TextView texto;

    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_master2, container, false);

        mViewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(FragmentsVM.class);
        mList = (ListView)rootView.findViewById(R.id.lista);

        mList.setOnItemClickListener(this);

        mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> teams) {
                for (int i = 0; i < teams.size(); i++) {
                    Log.v("Team: ", teams.get(i).toString());
                }
                mTeamAdapater = new TeamAdapter(getContext(), mViewModel, teams);

                mList.setAdapter(mTeamAdapater);
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Team team = (Team) parent.getAdapter().getItem(position);
        mViewModel.setSelectedTeam(team);

    }
}
