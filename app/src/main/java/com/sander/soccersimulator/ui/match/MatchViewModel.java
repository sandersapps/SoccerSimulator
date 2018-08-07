package com.sander.soccersimulator.ui.match;

import com.sander.soccersimulator.data.Match.MatchResult;
import com.sander.soccersimulator.data.Pool.PoolRepository;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModel for the MatchFragment.
 */
class MatchViewModel extends ViewModel {

    private PoolRepository poolRepository;

    private MatchViewModel(@NonNull PoolRepository poolRepository) {
        this.poolRepository = poolRepository;
    }

    @NonNull
    ArrayList<MatchResult> getGameResults() {
        if(poolRepository.getPool() != null) {
            return poolRepository.getPool().getMatchResults();
        } else {
            throw new IllegalStateException("no pool in existance");
        }
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private PoolRepository poolRepository;

        Factory(@NonNull PoolRepository poolRepository) {
            this.poolRepository = poolRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MatchViewModel(poolRepository);
        }
    }
}
