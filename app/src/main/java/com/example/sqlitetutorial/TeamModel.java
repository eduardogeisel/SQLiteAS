package com.example.sqlitetutorial;

public class TeamModel {

    private int id;
    private String teamName;
    private int yearFounded;

    //constructors
    public TeamModel() {
        //non parameterized constructor
    }

    public TeamModel(int id, String teamName, int yearFounded) {
        this.id = id;
        this.teamName = teamName;
        this.yearFounded = yearFounded;
    }

    //toString is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return "TeamModel{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", yearFounded=" + yearFounded +
                '}';
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }
}
