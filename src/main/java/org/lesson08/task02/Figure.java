package org.lesson08.task02;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Класс - фигура.
 */
public class Figure implements Serializable {
    private static final long serialVersionUID = 13052019L;

    private Integer width;
    private Integer height;
    private Color color;

    public Figure(Integer width, Integer height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Figure) {
            Figure figure = (Figure) o;
            return Objects.equals(width, figure.width) &&
                    Objects.equals(height, figure.height) &&
                    Objects.equals(color, figure.color);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, color);
    }

    @Override
    public String toString() {
        return "Figure{" +
                "width=" + width +
                ", height=" + height +
                ", color=" + color +
                '}';
    }
}
