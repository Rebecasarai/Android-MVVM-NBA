package com.rebecasarai.room.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.FragmentsVM;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.Views.TeamAdapter;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class DetailsFragment extends Fragment {


    FragmentsVM mViewModel;
    ListView mList;
    View rootView;
    EditText edit1, edit2;


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_details, container, false);
        mViewModel = ViewModelProviders.of(this).get(FragmentsVM.class);
        mList = rootView.findViewById(R.id.lista);
        edit1 = rootView.findViewById(R.id.edit1Fragment);
        edit2 = rootView.findViewById(R.id.edit2Fragment);

        mViewModel.setSelectedTeam(mViewModel.getFirstTeam());

        mViewModel.getSelectedTeam().observe(this, new Observer<Team>() {
                @Override
                public void onChanged(@Nullable Team team) {
                    edit1.setText(team.getName());
                    edit2.setText(team.getDescription());
                }
         });


        return rootView;
    }


}