package com.rebecasarai.room.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.rebecasarai.room.Fragments.MasterFragment;
import com.rebecasarai.room.Fragments.PruebaFragment;
import com.rebecasarai.room.Fragments.TeamFragment;
import com.rebecasarai.room.Fragments.dummy.DummyContent;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.AddTeamVM;
import com.rebecasarai.room.ViewModels.FragmentsVM;
import com.rebecasarai.room.models.Team;

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
    public void onListFragmentInteraction(Team item) {
        Toast.makeText(getApplicationContext(), " index " + item,
                Toast.LENGTH_LONG).show();
        mViewModel.setTexto(item.getName());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.add_team:
                intent = new Intent(this, AddTeamActivity.class);
                startActivity(intent);
                return true;

            case R.id.add4:
                mViewModel.insertTeams();
                return true;

            case R.id.help:

                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
