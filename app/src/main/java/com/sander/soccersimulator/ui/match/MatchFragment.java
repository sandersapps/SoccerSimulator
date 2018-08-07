package com.sander.soccersimulator.ui.match;

import android.os.Bundle;

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
 * Fragment which displays the outcome of all matches and has a button to continue to the outcome
 * of the pool.
 */
public class MatchFragment extends Fragment {

    private MatchViewModel viewModel;

    @BindView(R.id.matches)
    RecyclerView matches;

    private MatchesAdapter matchesAdapter;

    private Unbinder unbinder;

    public MatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MatchViewModel.Factory factory = new MatchViewModel.Factory(PoolRepository.getInstance());
        viewModel = ViewModelProviders.of(this, factory).get(MatchViewModel.class);
        matchesAdapter = new MatchesAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        unbinder = ButterKnife.bind(this, view);
        matchesAdapter.submitList(viewModel.getGameResults());
        matches.setLayoutManager(new LinearLayoutManager(requireContext()));
        matches.setAdapter(matchesAdapter);
        return view;
    }

    @OnClick(R.id.results_button)
    public void viewResults() {
        NavHostFragment.findNavController(this).navigate(R.id.action_matchFragment_to_poolFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
