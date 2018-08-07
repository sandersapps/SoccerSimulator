package com.sander.soccersimulator.data.Teams;

import androidx.annotation.NonNull;

/**
 * This represents a team.
 */
public class Team {

    /**
     * A unique id for the team.
     */
    private int id;

    /**
     * The name of the team.
     */
    private String name;

    /**
     * The relative strength of the attackers in this team.
     */
    private float attack;

    /**
     * The relative strength of the midfielders in this team.
     */
    private float midfield;

    /**
     * The relative strength of the defenders in this team.
     */
    private float defense;

    /**
     * The relative strength of the keeper in this team.
     */
    private float keeper;

    Team(int id, @NonNull String name, float attack, float midfield, float defense, float keeper) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.midfield = midfield;
        this.defense = defense;
        this.keeper = keeper;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public float getAttack() {
        return attack;
    }

    public float getMidfield() {
        return midfield;
    }

    public float getDefense() {
        return defense;
    }

    public float getKeeper() {
        return keeper;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", attack=" + attack +
                ", midfield=" + midfield +
                ", defense=" + defense +
                ", keeper=" + keeper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (Float.compare(team.attack, attack) != 0) return false;
        if (Float.compare(team.midfield, midfield) != 0) return false;
        if (Float.compare(team.defense, defense) != 0) return false;
        if (Float.compare(team.keeper, keeper) != 0) return false;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (attack != +0.0f ? Float.floatToIntBits(attack) : 0);
        result = 31 * result + (midfield != +0.0f ? Float.floatToIntBits(midfield) : 0);
        result = 31 * result + (defense != +0.0f ? Float.floatToIntBits(defense) : 0);
        result = 31 * result + (keeper != +0.0f ? Float.floatToIntBits(keeper) : 0);
        return result;
    }
}
