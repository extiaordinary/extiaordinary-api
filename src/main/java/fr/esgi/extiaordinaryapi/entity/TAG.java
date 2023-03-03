package fr.esgi.extiaordinaryapi.entity;

public enum TAG {
    LOW(20), MEDIUM(40), HIGH(60);
    private int value;

    TAG(int value) {
        this.value = value;
    }
}
