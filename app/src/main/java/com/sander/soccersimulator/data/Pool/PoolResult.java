package com.sander.soccersimulator.data.Pool;

import com.sander.soccersimulator.data.Teams.Team;

import androidx.annotation.NonNull;

/**
 * The result for a single team in a pool.
 */
public class PoolResult implements Comparable<PoolResult> {

    /**
     * The team whom these results belong to.
     */
    private Team team;

    /**
     * The amount of points gathered, you get 3 for winning and 1 for a draw.
     */
    private int points;

    /**
     * The amount of goals scored.
     */
    private int goalsFor;

    /**
     * The amount of times the opponent scored a goal.
     */
    private int goalsAgainst;

    PoolResult(@NonNull Team team) {
        this.team = team;
    }

    /**
     * Increases the amount of points this team has gathered.
     * @param points the amount of points
     */
    void addPoints(int points) {
        this.points = this.points + points;
    }

    /**
     * Increases the amount of goals this team has scored
     * @param goals the amount of goals
     */
    void addGoalsFor(int goals) {
        this.goalsFor = this.goalsFor + goals;
    }

    /**
     * Increases how often an opponent has scored against this team.
     * @param goals the amount of goals
     */
    void addGoalsAgainst(int goals) {
        this.goalsAgainst = this.goalsAgainst + goals;
    }

    public String getTeamName() {
        return team.getName();
    }

    public int getTeamId() {
        return team.getId();
    }

    public int getPoints() {
        return points;
    }

    public int getGoalBalance() {
        return goalsFor - goalsAgainst;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    @Override
    public String toString() {
        return "PoolResult{" +
                "team=" + team +
                ", points=" + points +
                ", goalAgainst=" + goalsAgainst +
                ", goalsFor=" + goalsFor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoolResult that = (PoolResult) o;

        if (points != that.points) return false;
        if (goalsAgainst != that.goalsAgainst) return false;
        if (goalsFor != that.goalsFor) return false;
        return team != null ? team.equals(that.team) : that.team == null;
    }

    @Override
    public int hashCode() {
        int result = team != null ? team.hashCode() : 0;
        result = 31 * result + points;
        result = 31 * result + goalsAgainst;
        result = 31 * result + goalsFor;
        return result;
    }

    /**
     * implemented comparable to sort an ArrayList of PoolResult objects to show who the winner of
     * the entire Pool is.
     * @param poolResult {@see compareTo}
     * @return {@see compareTo}
     */
    @Override
    public int compareTo(@NonNull PoolResult poolResult) {
        if(this.points < poolResult.getPoints()) {
            return 1;
        } else if(this.points > poolResult.getPoints()) {
            return -1;
        } else {
            if(this.getGoalBalance() < poolResult.getGoalBalance()) {
                return 1;
            } else if(this.getGoalBalance() > poolResult.getGoalBalance()) {
                return -1;
            } else {
                if(this.getGoalsFor() < poolResult.getGoalsFor()) {
                    return 1;
                } else if(this.getGoalsFor() > poolResult.getGoalsFor()) {
                    return -1;
                } else {
                    if(this.getGoalsAgainst() < poolResult.getGoalsAgainst()) {
                        return 1;
                    } else if(this.getGoalsAgainst() < poolResult.getGoalsAgainst()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        }
    }
}