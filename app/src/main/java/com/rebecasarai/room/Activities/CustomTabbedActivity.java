package com.rebecasarai.room.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rebecasarai.room.Fragments.MasterFragment;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.FragmentsVM;

public class CustomTabbedActivity extends AppCompatActivity implements View.OnClickListener{

    FragmentsVM mFragmentsVM;
    Button mBtnPrev, mBtnNext;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tabbed);
        mFragmentsVM = ViewModelProviders.of(this).get(FragmentsVM.class);

        fragmentManager = getSupportFragmentManager();
        MasterFragment fragment = new MasterFragment();
        FragmentTransaction fragmentTransaccion = fragmentManager.beginTransaction();
        fragmentTransaccion.add(R.id.customTabbedLayout, fragment);
        fragmentTransaccion.addToBackStack(null);
        fragmentTransaccion.commit();

        mBtnPrev = (Button) findViewById(R.id.Prev);
        mBtnPrev.setOnClickListener(this);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


    }
}

