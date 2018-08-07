package com.sander.soccersimulator.ui.pool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sander.soccersimulator.R;
import com.sander.soccersimulator.data.Pool.PoolRepository;

/**
 * Fragment that shows the results of the Pool and a reset button
 */
public class PoolFragment extends Fragment {

    private PoolViewModel viewModel;

    @BindView(R.id.pool_results)
    RecyclerView poolResults;

    private PoolAdapter poolAdapter;

    private Unbinder unbinder;

    public PoolFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PoolViewModel.Factory factory = new PoolViewModel.Factory(PoolRepository.getInstance());
        viewModel = ViewModelProviders.of(this, factory).get(PoolViewModel.class);
        poolAdapter = new PoolAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pool, container, false);
        unbinder = ButterKnife.bind(this, view);
        poolAdapter.submitList(viewModel.getSortedPoolResults());
        poolResults.setLayoutManager(new LinearLayoutManager(requireContext()));
        poolResults.setAdapter(poolAdapter);
        return view;
    }

    @OnClick(R.id.reset_button)
    void reset() {
        NavHostFragment.findNavController(this).popBackStack(R.id.teamsOverview, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
