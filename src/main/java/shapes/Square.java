package shapes;


public final class Square extends Shape {
    private double height;

    public Square(double height) {
        if (height <= 0)
            throw new IllegalStateException("Invalid square height: " + height);
        this.height = height;
        type = "square";
    }

    public double getArea() {
        return height * height;
    }

    public double getPerimeter() {
        return 4 * height;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Square && height == ((Square)other).height;
    }

    public int hashCode() {
        return Double.valueOf(height).hashCode();
    }

    public String toString() {
        return "Square(height=" + height + ')';
    }

    public final double getHeight() {
        return height;
    }

    public final void setHeight(double height) {
        if (height <= 0)
            throw new IllegalStateException("Invalid square height: " + height);
        this.height = height;
    }
}