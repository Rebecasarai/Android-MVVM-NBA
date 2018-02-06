package com.rebecasarai.room.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rebecasarai.room.Fragments.TeamFragment.OnListFragmentInteractionListener;
import com.rebecasarai.room.Fragments.dummy.DummyContent.DummyItem;
import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTeamRecyclerViewAdapter extends RecyclerView.Adapter<MyTeamRecyclerViewAdapter.ViewHolder> {

    //private final List<DummyItem> mValues;

    private final List<Team> mTeams;
    private final OnListFragmentInteractionListener mListener;

    public MyTeamRecyclerViewAdapter(List<Team> items, OnListFragmentInteractionListener listener) {
        mTeams = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mTeams.get(position);
        holder.mIdView.setText(mTeams.get(position).getName());
        holder.mContentView.setText(mTeams.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Team mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
