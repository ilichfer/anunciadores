package com.anunciadores.dto;

public class MinistryMember {

    private int positionId;
    private String position;
    private String personName;
    private String ministryName;
    private int personId;

    public MinistryMember() {}

    public MinistryMember(String position, String personName,String ministryName,int positionId, int personId) {
        this.position = position;
        this.personName = personName;
        this.ministryName = ministryName;
        this.positionId = positionId;
        this.personId = personId;
    }

    public String getPosition() {
        return position;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getMinistryName() {
        return ministryName;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public void setMinistryName(String ministryName) {
        this.ministryName = ministryName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
