package com.sander.soccersimulator.data.Match;

import com.sander.soccersimulator.data.Teams.Team;

import androidx.annotation.NonNull;

/**
 * Represents the result of a single match
 */
public class MatchResult {

    /**
     * An int that is incremente each time a new MatchResult is created to give it a unique id.
     */
    private static int AUTOINCREMENT_ID = 0;

    /**
     * The unique id of this MatchResult
     */
    private int id;

    /**
     * The Team that was playing at home during this match.
     */
    private Team homeTeam;

    /**
     * The team that was playing away during this match.
     */
    private Team awayTeam;

    /**
     * The amount of goals that the home team scored this match.
     */
    private int homeTeamGoals;

    /**
     * The amount of goals that the away team scored this match.
     */
    private int awayTeamGoals;

    /**
     * Constructor which creates a new MatchResult with a unique id.
     * @param home The team that was playing at home during this match.
     * @param away The team that was playing away during this match.
     */
    MatchResult(@NonNull Team home, @NonNull Team away) {
        this.homeTeam = home;
        this.awayTeam = away;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
        this.id = AUTOINCREMENT_ID++;
    }

    /**
     * Indicate that one of the sides has scored a goal.
     * @param side The side which scored a goal.
     */
    void goal(Match.Side side) {
        switch (side) {
            case HOME: {
                homeTeamGoals++;
                break;
            }
            case AWAY: {
                awayTeamGoals++;
                break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "id=" + id +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchResult that = (MatchResult) o;

        if (id != that.id) return false;
        if (homeTeamGoals != that.homeTeamGoals) return false;
        if (awayTeamGoals != that.awayTeamGoals) return false;
        if (!homeTeam.equals(that.homeTeam)) return false;
        return awayTeam.equals(that.awayTeam);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + homeTeam.hashCode();
        result = 31 * result + awayTeam.hashCode();
        result = 31 * result + homeTeamGoals;
        result = 31 * result + awayTeamGoals;
        return result;
    }
}
