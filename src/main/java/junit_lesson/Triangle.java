package junit_lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Triangle {
    private int a;
    private int b;
    private int c;
    private Colour colour = Colour.WHITE;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public enum Colour {
        RED, WHITE, BLUE, GREEN
    }

    public boolean isValid() {
        int maxSide = Math.max(a, Math.max(b, c));
        return maxSide < (a + b + c - maxSide);
    }

    public boolean hasPositiveSides() {
        return a > 0 && b > 0 && c > 0;
    }


    public int countPerimeter() {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The triangle must be valid");
        }
        return a + b + c;
    }

    // Done --- формула расчета
    public double countArea() {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The triangle must be valid");
        }
        double sp = (a + b + c) * 0.5;
        double ar = (Math.sqrt(sp*(sp - a)*(sp - b)*(sp - c)));
        double area = DoubleRounder.round(ar, 2);
        return area;
    }
    // --done

    public void paint(Colour colour) {
        if (this.colour == colour) {
            throw new IllegalArgumentException("The new colour must be not equal to old colour");
        }
        this.setColour(colour);
    }

    public void paint(String colour) {
        this.paint(Colour.valueOf(colour));
    }


    public Triangle createSimilarTriangle(int coefficient) {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The triangle must be valid");
        }
        if (coefficient <= 0) {
            throw new IllegalArgumentException("The coefficient must be positive");
        }
        return new Triangle(a * coefficient, b * coefficient, c * coefficient, colour);
    }

    public List<Triangle> createSimilarTriangles(int sinceCoefficient, int toCoefficient) {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("The sides must be positive");
        }
        if (!isValid()) {
            throw new IllegalArgumentException("The triangle must be valid");
        }
        if (sinceCoefficient <= 0 || toCoefficient <= 0) {
            throw new IllegalArgumentException("The coefficient must be positive");
        }
        if (sinceCoefficient > toCoefficient) {
            throw new IllegalArgumentException("The start coefficient must be greater than end coefficient");
        }

        List<Triangle> triangles = new ArrayList<>();
        for (int i = sinceCoefficient; i < toCoefficient + 1; i++) {
            triangles.add(new Triangle(a * i, b * i, c * i, colour));
        }
        return triangles;
    }

}
