package com.sander.soccersimulator.ui.pool;

import com.sander.soccersimulator.data.Pool.PoolRepository;
import com.sander.soccersimulator.data.Pool.PoolResult;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class PoolViewModel extends ViewModel {

    private PoolRepository poolRepository;

    private PoolViewModel(@NonNull PoolRepository poolRepository) {
        this.poolRepository = poolRepository;
    }

    @NonNull
    ArrayList<PoolResult> getSortedPoolResults() {
        if (poolRepository.getPool() != null) {
            return poolRepository.getPool().getSortedPoolResults();
        } else {
            throw new IllegalStateException("pool hasn't been set");
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
            return (T) new PoolViewModel(poolRepository);
        }
    }
}
