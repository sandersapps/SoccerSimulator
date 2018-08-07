package com.sander.soccersimulator.ui.teams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sander.soccersimulator.R;
import com.sander.soccersimulator.data.Teams.Team;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An adapter to display Teams in a RecyclerView.
 */
public class TeamsAdapter extends ListAdapter<Team, TeamsAdapter.TeamsViewHolder> {

    TeamsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return  new TeamsViewHolder(inflater.inflate(R.layout.viewholder_team, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static final DiffUtil.ItemCallback<Team> DIFF_CALLBACK = new DiffUtil.ItemCallback<Team>() {
        @Override
        public boolean areItemsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.equals(newItem);
        }
    };

    static class TeamsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_name)
        TextView name;

        @BindView(R.id.team_attack)
        TextView attack;

        @BindView(R.id.team_midfield)
        TextView midfield;

        @BindView(R.id.team_defense)
        TextView defense;

        @BindView(R.id.team_keeper)
        TextView keeper;

        TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Team team) {
            name.setText("name: " + team.getName());
            attack.setText("attack: " + String.valueOf(team.getAttack()));
            midfield.setText("midfield: " + String.valueOf(team.getMidfield()));
            defense.setText("defense: " + String.valueOf(team.getDefense()));
            keeper.setText("keeper: " + String.valueOf(team.getKeeper()));
        }
    }
}