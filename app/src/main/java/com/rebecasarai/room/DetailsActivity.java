package com.rebecasarai.room;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.rebecasarai.room.Fragments.MasterFragment;
import com.rebecasarai.room.Fragments.PruebaFragment;
import com.rebecasarai.room.Fragments.TeamFragment;
import com.rebecasarai.room.Fragments.dummy.DummyContent;
import com.rebecasarai.room.ViewModels.AddTeamVM;
import com.rebecasarai.room.ViewModels.FragmentsVM;

public class DetailsActivity extends AppCompatActivity implements TeamFragment.OnListFragmentInteractionListener {

    FragmentsVM mViewModel;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mViewModel = ViewModelProviders.of(this).get(FragmentsVM.class);


        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        TeamFragment fragment = new TeamFragment();
        fragmentTransaction.add(R.id.layoutDetails, fragment);
        fragmentTransaction.commit();


    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(getApplicationContext(), " index " + item,
                Toast.LENGTH_LONG).show();
        mViewModel.setTexto(item.content);

        // Create new fragment and transaction
        Fragment newFragment = new PruebaFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.layoutDetails, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
}
