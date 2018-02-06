package com.rebecasarai.room.Fragments;


import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rebecasarai.room.Activities.AddTeamActivity;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.FragmentsVM;

/**
 * A simple {@link Fragment} subclass.
 */
public class PruebaFragment extends Fragment {

    TextView texto;
    FragmentsVM mViewModel;
    View rootView;

    public PruebaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_prueba, container, false);
        // mViewModel = ViewModelProviders.of(this).get(FragmentsVM.class);
        mViewModel = ViewModelProviders.of((FragmentActivity) getActivity()).get(FragmentsVM.class);
        texto= (TextView) rootView.findViewById(R.id.textoFragmentPrueba);
        String texto = mViewModel.getTexto();
        setTexto(texto);
        // Inflate the layout for this fragment
        return rootView;
    }

    public TextView getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto.setText(texto);
    }
}
