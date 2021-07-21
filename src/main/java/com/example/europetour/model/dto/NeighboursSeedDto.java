package com.example.europetour.model.dto;

import java.util.Set;

public class NeighboursSeedDto {

    private String Code;
    private Set<String> neighbours;

    public NeighboursSeedDto() {
    }

    public Set<String> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<String> neighbours) {
        this.neighbours = neighbours;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
