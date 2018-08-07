package com.sander.soccersimulator.data.Pool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Repository to get the pool instance that is currently in use across different fragments.
 */
public class PoolRepository {

    private static PoolRepository instance;

    private Pool pool;

    private PoolRepository() {
    }

    @Nullable
    public Pool getPool() {
        return pool;
    }

    public void setPool(@NonNull Pool pool) {
        this.pool = pool;
    }

    @NonNull
    public static PoolRepository getInstance() {
        if(instance == null) {
            synchronized (PoolRepository.class) {
                if(instance == null) {
                    instance = new PoolRepository();
                }
            }
        }
        return instance;
    }
}
