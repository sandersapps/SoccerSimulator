package com.sander.soccersimulator.data.Match;

import com.sander.soccersimulator.data.Teams.Team;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This is where the actual matches are being played. For simplicity the match is broken down in 30
 * turns which all represent 3 minutes of the match. The Field has been broken up in 3 pieces which
 * are represented by {@link Field}. The ball is in possesion of {@link #possession}. Each turn one
 * of the teams makes an advance towards the goal of the other team. Which team advances is determined
 * by the relative strength of each team on the current part of the field and a randomly generated
 * number. When a team advances whilst already at the most forward position it attempts a shot at the
 * goal. Whether a goal is scored is determined by the relative strength of the attacking team and
 * the defending keeper.
 * For simplicity there is no reset halfway through the match and the ball can never skip a part of
 * the field (e.g. a keeper can't shoot it to the middle or opposite side of the field, he can only
 * throw it to defender). The Home team is playing from left to right and the Away team is playing
 * from right to left.
 */
public class Match {

    /**
     * The home team.
     */
    private Team home;

    /**
     * The away team.
     */
    private Team away;

    /**
     * The part of the field where the ball currently is.
     */
    private Field field;

    /**
     * What Side currently has ball posession
     */
    private Side possession;

    /**
     * A random used to determine the outcome of all events of the match.
     */
    private Random random;

    /**
     * Creates a new match and gives ball possession to a random team on the middle of the field.
     * @param home The home team
     * @param away The away team
     */
    public Match(@NonNull Team home, @NonNull Team away) {
        this.home = home;
        this.away = away;
        this.field = Field.MIDDLE;
        random = new Random();
        possession = random.nextBoolean() ? Side.HOME : Side.AWAY;
    }

    /**
     * An enum that represents the different parts of the field, the home team is playing from left
     * to right and the away team is playing from right to left.
     */
    private enum Field {
        LEFT,
        MIDDLE,
        RIGHT
    }

    /**
     * An enum that represents the two teams to make expressions in algorythms easier.
     */
    enum Side {
        HOME,
        AWAY
    }

    /**
     * Advances to the next turn. Each turn represents 3 minutes of the match.
     * @param matchResult The result of the match so far.
     */
    private void nextTurn(@NonNull MatchResult matchResult) {
        Side shot = advance(whoWinsThisRound());
        if(shot != null) {
            shotAtGoal(shot, matchResult);
        }
    }

    /**
     * Determines what team makes an advancing move this round.
     * @return The team that advances.
     */
    @NonNull
    private Side whoWinsThisRound() {
        float homeChance = 0;
        switch (this.field) {
            case LEFT: {
                homeChance = home.getDefense() / (home.getDefense() + away.getAttack());
                break;
            }
            case MIDDLE: {
                homeChance = home.getMidfield() / (home.getMidfield() + away.getMidfield());
                break;
            }
            case RIGHT: {
                homeChance = home.getAttack() / (home.getAttack() + away.getDefense());
            }
        }
        return random.nextFloat() <= homeChance ? Side.HOME : Side.AWAY;
    }

    /**
     * Advances one side and returns wether a shot at a goal should be made.
     * @param side The side that advances this round.
     * @return The side that should attempt a shot at the goal, null if none should try.
     */
    @Nullable
    private Side advance(@NonNull Side side) {
        if(possession != side) {
            possession = side;
            return null;
        } else {
            switch (field) {
                case LEFT: {
                    field = side == Side.HOME ? Field.MIDDLE : Field.LEFT;
                    return side == Side.HOME ? null : Side.AWAY;
                }
                case MIDDLE: {
                    field = side == Side.HOME ? Field.RIGHT : Field.LEFT;
                    return null;
                }
                case RIGHT: {
                    field = side == Side.HOME ? Field.RIGHT : Field.MIDDLE;
                    return side == Side.HOME ? Side.HOME : null;
                }
            }
        }
        return null;
    }

    /**
     * Lets one of the sides make a shot at the goal and updates the MatchResult.
     * @param side The side that makes a shot at the goal.
     * @param matchResult The MatchResult that needs updating.
     */
    private void shotAtGoal(@NonNull Side side, @NonNull MatchResult matchResult) {
        switch (side) {
            case HOME: {
                float chance = home.getAttack() / (home.getAttack() + away.getKeeper());
                if(random.nextFloat() <= chance) {
                    matchResult.goal(Side.HOME);
                    field = Field.MIDDLE;
                    possession = Side.AWAY;
                    return;
                }
                break;
            }
            case AWAY: {
                float chance = away.getAttack() / (away.getAttack() + home.getKeeper());
                if(random.nextFloat() <= chance) {
                    matchResult.goal(Side.AWAY);
                    field = Field.MIDDLE;
                    possession = Side.HOME;
                    return;
                }
            }
        }
        possession = random.nextBoolean() ? Side.HOME : Side.AWAY;
    }

    /**
     * Play the Match and return the MatchResult of the match.
     * @return The result of the match.
     */
    @NonNull
    public MatchResult play() {
        MatchResult matchResult = new MatchResult(home, away);
        for(int i = 0; i < 30; i++) {
            nextTurn(matchResult);
        }
        return matchResult;
    }
}
