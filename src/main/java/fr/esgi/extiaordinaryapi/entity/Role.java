package fr.esgi.extiaordinaryapi.entity;

public enum Role {
    USER("user"),
    COACH("coach");

    private String label;

    Role(String label) {
        this.label = label;
    }
}
