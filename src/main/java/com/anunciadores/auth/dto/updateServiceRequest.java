package com.anunciadores.auth.dto;

import java.util.List;

public class updateServiceRequest {
    private String date;
    private int ministryId;
    private String ministry;
    private List<assignments> assignments;

    public updateServiceRequest() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMinistryId() {
        return ministryId;
    }

    public void setMinistryId(int ministryId) {
        this.ministryId = ministryId;
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    public List<assignments> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<assignments> assignments) {
        this.assignments = assignments;
    }
}
