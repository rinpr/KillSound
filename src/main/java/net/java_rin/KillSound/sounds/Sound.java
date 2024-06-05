package net.java_rin.KillSound.sounds;

public enum Sound {
    NONE("NONE"),
    ONE("ONE"),
    TWO("TWO"),
    THREE("THREE"),
    FOUR("FOUR"),
    FIVE("FIVE"),
    SIX("SIX"),
    SEVEN("SEVEN");

    private final String stringValue;

    Sound(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}

