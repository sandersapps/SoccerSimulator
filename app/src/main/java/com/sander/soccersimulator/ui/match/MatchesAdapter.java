package com.sander.soccersimulator.ui.match;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sander.soccersimulator.R;
import com.sander.soccersimulator.data.Match.MatchResult;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to display MatchResults in a RecyclerView.
 */
public class MatchesAdapter extends ListAdapter<MatchResult, MatchesAdapter.MatchViewHolder> {

    MatchesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MatchesAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MatchViewHolder(inflater.inflate(R.layout.viewholder_match, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.MatchViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static final DiffUtil.ItemCallback<MatchResult> DIFF_CALLBACK = new DiffUtil.ItemCallback<MatchResult>() {
        @Override
        public boolean areItemsTheSame(@NonNull MatchResult oldItem, @NonNull MatchResult newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MatchResult oldItem, @NonNull MatchResult newItem) {
            return oldItem.equals(newItem);
        }
    };

    static class MatchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.match_result)
        TextView matchResult;

        MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull MatchResult matchResult) {
            this.matchResult.setText(matchResult.getHomeTeam().getName() + " - " +
                    matchResult.getAwayTeam().getName() + " : " +
                    String.valueOf(matchResult.getHomeTeamGoals()) + " - " +
                    String.valueOf(matchResult.getAwayTeamGoals()));
        }
    }
}
