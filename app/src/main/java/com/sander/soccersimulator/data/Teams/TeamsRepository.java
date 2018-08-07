package com.sander.soccersimulator.data.Teams;

import java.util.ArrayList;

import androidx.annotation.NonNull;

/**
 * Repository to get the teams that are currently in use across different fragments.
 */
public class TeamsRepository {

    private static TeamsRepository instance;

    private ArrayList<Team> teams;

    private TeamsRepository() {
        teams = new ArrayList<>();
        teams.add(new Team(1,"BLUE", 0.8f, 0.4f, 0.7f, 0.2f));
        teams.add(new Team(2,"YELLOW", 0.3f, 0.6f, 0.4f, 0.6f));
        teams.add(new Team(3,"RED", 0.2f, 0.9f, 0.8f, 0.5f));
        teams.add(new Team(4,"GREEN", 0.6f, 0.7f, 0.3f, 0.8f));
    }

    @NonNull
    public ArrayList<Team> getTeams() {
        return teams;
    }

    public static TeamsRepository getInstance() {
        if(instance == null) {
            synchronized (TeamsRepository.class) {
                if(instance == null) {
                    instance = new TeamsRepository();
                }
            }
        }
        return instance;
    }
}
