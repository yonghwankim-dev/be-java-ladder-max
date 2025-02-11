package kr.codesquad.ladder.domain;

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

public class Line implements Iterable<Point> {

    private final Points points;

    public Line(Points points) {
        this.points = points;
    }

    public void addPoint(int col, Random random) {
        if (isBarColumn(col)) {
            points.add(buildBar());
            return;
        }
        points.add(buildRandomBridge(col, random));
    }

    private boolean isBarColumn(int col) {
        return col % 2 == 0;
    }

    private Point buildBar() {
        return Point.ofBar();
    }

    private Point buildRandomBridge(int col, Random random) {
        if (!random.nextBoolean()) {
            return Point.ofEmpty();
        }
        if (!existBridgeOnLeft(col)) {
            return Point.ofBridge();
        }
        return Point.ofEmpty();
    }

    private boolean existBridgeOnLeft(int col) {
        return points.existBridgeOnLeft(col);
    }

    public int climb(int col) {
        return points.climb(col);
    }

    @Override
    public Iterator<Point> iterator() {
        return points.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Line)) {
            return false;
        }
        Line line = (Line) o;
        return Objects.equals(points, line.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        for (Point point : points) {
            line.append(point);
        }
        return line.toString();
    }
}
