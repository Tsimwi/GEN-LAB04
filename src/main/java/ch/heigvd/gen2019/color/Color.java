package ch.heigvd.gen2019.color;

public abstract class Color {
    String color;

    Color (String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.color;
    }
}
