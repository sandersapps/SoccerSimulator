package com.sander.soccersimulator.ui.pool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sander.soccersimulator.R;
import com.sander.soccersimulator.data.Pool.PoolResult;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to display PoolResults in a RecyclerView
 */
public class PoolAdapter extends ListAdapter<PoolResult, PoolAdapter.PoolViewHolder> {

    PoolAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public PoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PoolViewHolder(inflater.inflate(R.layout.viewholder_pool, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PoolViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static final DiffUtil.ItemCallback<PoolResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<PoolResult>() {
        @Override
        public boolean areItemsTheSame(@NonNull PoolResult oldItem, @NonNull PoolResult newItem) {
            return oldItem.getTeamId() == newItem.getTeamId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PoolResult oldItem, @NonNull PoolResult newItem) {
            return oldItem.equals(newItem);
        }
    };

    static class PoolViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_name)
        TextView name;

        @BindView(R.id.team_points)
        TextView points;

        @BindView(R.id.team_goal_balance)
        TextView goalBalance;

        @BindView(R.id.team_goals_for)
        TextView goalsFor;

        @BindView(R.id.team_goals_against)
        TextView goalsAgainst;

        PoolViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(PoolResult poolResult) {
            name.setText("name: " + poolResult.getTeamName());
            points.setText("points: " + String.valueOf(poolResult.getPoints()));
            goalBalance.setText("goal balance: " + String.valueOf(poolResult.getGoalBalance()));
            goalsFor.setText("goals for: " + String.valueOf(poolResult.getGoalsFor()));
            goalsAgainst.setText("goals against: " + String.valueOf(poolResult.getGoalsAgainst()));
        }
    }
}