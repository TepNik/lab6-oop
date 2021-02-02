package shapes;

import java.util.Objects;
public final class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0)
            throw new IllegalStateException("Invalid rectangle width: " + width);
        if (height <= 0)
            throw new IllegalStateException("Invalid rectangle height: " + height);
        this.width = width;
        this.height = height;
        type = "rectangle";
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Rectangle && width == ((Rectangle)other).width && height == ((Rectangle)other).height;
    }

    public int hashCode() {
        return Objects.hash(width, height);
    }

    public String toString() {
        return "Rectangle(width=" + width + ", height=" + height + ')';
    }

    public final double getWidth() {
        return width;
    }

    public final void setWidth(double width) {
        if (width <= 0)
            throw new IllegalStateException("Invalid rectangle width: " + width);
        this.width = width;
    }

    public final double getHeight() {
        return height;
    }

    public final void setHeight(double height) {
        if (height <= 0)
            throw new IllegalStateException("Invalid rectangle height: " + height);
        this.height = height;
    }
}