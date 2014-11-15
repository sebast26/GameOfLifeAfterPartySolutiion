package pl.sgorecki;

import java.util.Collection;
import java.util.HashSet;

/**
 * Cell represents ONLY alive cells in Board/Grid.
 * @author Sebastian Górecki <gorecki.sebastian@gmail.com>
 */
public class Cell {

    private final Point point;

    public Cell(Point point) {
        this.point = point;
    }

    public Collection<Point> getNeighboursPositions() {
        Collection<Point> neighbourPositions = new HashSet<>();
        for (int i = point.getX() - 1; i <= point.getX() + 1; i++) {
            for (int j = point.getY() - 1; j <= point.getY() + 1; j++) {
                if (i == point.getX() && j == point.getY()) continue;
                neighbourPositions.add(new Point(i, j));
            }
        }
        return neighbourPositions;
    }
}
