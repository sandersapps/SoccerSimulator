package com.sander.soccersimulator.ui.teams;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.sander.soccersimulator.data.Pool.Pool;
import com.sander.soccersimulator.data.Pool.PoolRepository;
import com.sander.soccersimulator.data.Teams.TeamsRepository;


/**
 * An overview of all teams competing in the poule and a button to start the matches.
 */
public class TeamsFragment extends Fragment {

    private TeamsViewModel viewModel;

    @BindView(R.id.teams)
    RecyclerView teams;

    private TeamsAdapter teamsAdapter;

    private Unbinder unbinder;

    public TeamsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TeamsViewModel.Factory factory = new TeamsViewModel.Factory(TeamsRepository.getInstance(), PoolRepository.getInstance());
        viewModel = ViewModelProviders.of(this, factory).get(TeamsViewModel.class);
        teamsAdapter = new TeamsAdapter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_teams, container, false);
        unbinder = ButterKnife.bind(this, view);
        teamsAdapter.submitList(viewModel.getTeams());
        teams.setLayoutManager(new LinearLayoutManager(requireContext()));
        teams.setAdapter(teamsAdapter);
        return view;
    }

    @OnClick(R.id.play_button)
    void playGame() {
        viewModel.setPool(new Pool(viewModel.getTeams()));
        NavHostFragment.findNavController(this).navigate(R.id.action_teamsOverview_to_matchFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
