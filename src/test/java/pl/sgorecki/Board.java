package pl.sgorecki;

import java.util.*;

/**
 * @author Sebastian Górecki <gorecki.sebastian@gmail.com>
 */
public class Board {
    private final Set<Cell> cells;

    public Board(Cell... cells) {
        this.cells = new HashSet<>(Arrays.asList(cells));
    }

    public boolean cellIsUnderPopulated(Cell cell) {
        return countCellNeighbours(cell) < 2;
    }

    private int countCellNeighbours(Cell cell) {
        int neighbourCount = 0;
        final Collection<Point> neighboursPositions = cell.getNeighboursPositions();
        for (Point point : neighboursPositions) {
            if (cells.contains(new Cell(new Point(point.getX(), point.getY())))) {
                neighbourCount++;
            }
        }
        return neighbourCount;
    }
}
