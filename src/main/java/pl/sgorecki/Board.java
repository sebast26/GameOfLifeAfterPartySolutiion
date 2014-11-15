package pl.sgorecki;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    public boolean cellShouldLiveInNextGeneration(Cell cell) {
        final int cellNeighbours = countCellNeighbours(cell);
        return cellNeighbours >= 2 || cellNeighbours <= 3;
    }

    public boolean cellIsOvercrowded(Cell cell) {
        return countCellNeighbours(cell) > 3;
    }

    public Collection<Cell> resurrectDeadCells() {
        Collection<Cell> resurrectedCells = new HashSet<>();
        for (Cell cell : cells) {
            final Collection<Point> neighboursPositions = cell.getNeighboursPositions();
            for (Point neighbour : neighboursPositions) {
                if (cell.equals(new Cell(neighbour))) continue;
                if (countCellNeighbours(new Cell(neighbour)) == 3) {
                    resurrectedCells.add(new Cell(neighbour));
                }
            }
        }
        return resurrectedCells;
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
