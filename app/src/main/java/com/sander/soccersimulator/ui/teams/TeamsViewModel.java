package com.sander.soccersimulator.ui.teams;

import com.sander.soccersimulator.data.Pool.Pool;
import com.sander.soccersimulator.data.Pool.PoolRepository;
import com.sander.soccersimulator.data.Teams.Team;
import com.sander.soccersimulator.data.Teams.TeamsRepository;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * A ViewModel for the TeamsFragment
 */
public class TeamsViewModel extends ViewModel {

    private TeamsRepository teamsRepository;

    private PoolRepository poolRepository;

    private TeamsViewModel(@NonNull TeamsRepository teamsRepository, @NonNull PoolRepository poolRepository) {
        this.teamsRepository = teamsRepository;
        this.poolRepository = poolRepository;
    }

    @NonNull
    public ArrayList<Team> getTeams() {
        return teamsRepository.getTeams();
    }

    public void setPool(@NonNull Pool pool) {
        poolRepository.setPool(pool);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private TeamsRepository teamsRepository;

        private PoolRepository poolRepository;

        Factory(@NonNull TeamsRepository teamsRepository, @NonNull PoolRepository poolRepository) {
            this.teamsRepository = teamsRepository;
            this.poolRepository = poolRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TeamsViewModel(teamsRepository, poolRepository);
        }
    }
}
