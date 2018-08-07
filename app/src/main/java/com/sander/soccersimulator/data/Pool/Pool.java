package com.sander.soccersimulator.data.Pool;

import com.sander.soccersimulator.data.Match.Match;
import com.sander.soccersimulator.data.Match.MatchResult;
import com.sander.soccersimulator.data.Teams.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import androidx.annotation.NonNull;

/**
 * Holds the results for a single Pool.
 */
public class Pool {

    /**
     * The results of all individual matches.
     */
    private ArrayList<MatchResult> matchResults;

    /**
     * The end result of the pool for each team.
     */
    private ArrayList<PoolResult> poolResults;

    /**
     * Create a new Pool for the given teams and generates the results.
     * @param teams The theam that play in this pool
     */
    public Pool(@NonNull ArrayList<Team> teams) {
        Collections.shuffle(teams);
        playAllGames(teams);
        calculatePoolResults(teams, matchResults);
    }

    /**
     * Plays all games in the pool and updates the {@link #matchResults}.
     * @param teams The teams that play these matches
     */
    private void playAllGames(@NonNull ArrayList<Team> teams) {
        matchResults = new ArrayList<>();
        for(int i = 0; i < teams.size(); i++) {
            for(int j = i+1; j < teams.size(); j++) {
                Match match = new Match(teams.get(i), teams.get(j));
                matchResults.add(match.play());
            }
        }
    }

    /**
     * Calculates the results per team for the entire pool based on the matchResults
     * @param teams The teams that have played in this pool.
     * @param matchResults The results of all individual matches.
     */
    private void calculatePoolResults(@NonNull ArrayList<Team> teams, @NonNull ArrayList<MatchResult> matchResults) {
        poolResults = new ArrayList<>();
        HashMap<Team, PoolResult> resultsMap = new HashMap<>();
        for(Team team : teams) {
            resultsMap.put(team, new PoolResult(team));
        }
        for(MatchResult matchResult : matchResults) {
            if(matchResult.getHomeTeamGoals() > matchResult.getAwayTeamGoals()) {
                resultsMap.get(matchResult.getHomeTeam()).addPoints(3);
            } else if (matchResult.getHomeTeamGoals() < matchResult.getAwayTeamGoals()) {
                resultsMap.get(matchResult.getAwayTeam()).addPoints(3);
            } else {
                resultsMap.get(matchResult.getHomeTeam()).addPoints(1);
                resultsMap.get(matchResult.getAwayTeam()).addPoints(1);
            }
            resultsMap.get(matchResult.getHomeTeam()).addGoalsFor(matchResult.getHomeTeamGoals());
            resultsMap.get(matchResult.getHomeTeam()).addGoalsAgainst(matchResult.getAwayTeamGoals());
            resultsMap.get(matchResult.getAwayTeam()).addGoalsFor(matchResult.getAwayTeamGoals());
            resultsMap.get(matchResult.getAwayTeam()).addGoalsAgainst(matchResult.getHomeTeamGoals());
        }
        poolResults.addAll(resultsMap.values());
        Collections.sort(poolResults);
    }

    @NonNull
    public ArrayList<MatchResult> getMatchResults() {
        return matchResults;
    }

    @NonNull
    public ArrayList<PoolResult> getSortedPoolResults() {
        return poolResults;
    }
}
