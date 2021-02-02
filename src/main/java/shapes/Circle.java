package shapes;


public final class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0)
            throw new IllegalStateException("Invalid circle radius: " + radius);
        type = "circle";
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Circle && radius == ((Circle)other).radius;
    }

    public int hashCode() {
        return Double.valueOf(radius).hashCode();
    }

    public String toString() {
        return "Circle(radius=" + radius + ')';
    }

    public final double getRadius() {
        return radius;
    }

    public final void setRadius(double radius) {
        if (radius <= 0)
            throw new IllegalStateException("Invalid circle radius: " + radius);
        this.radius = radius;
    }
}
