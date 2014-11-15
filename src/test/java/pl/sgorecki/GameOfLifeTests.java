package pl.sgorecki;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Sebastian Górecki <gorecki.sebastian@gmail.com>
 */
public class GameOfLifeTests {

    @Test
    public void cellShouldReturnItsNeighboursPositions() {
        // given
        Cell cell = new Cell(new Point(0, 0));

        // when
        Collection<Point> points = cell.getNeighboursPositions();

        // then
        Collection<Point> expectedPoints = new HashSet<>();
        expectedPoints.add(new Point(-1, -1));
        expectedPoints.add(new Point(-1, 0));
        expectedPoints.add(new Point(-1, 1));
        expectedPoints.add(new Point(0, -1));
        expectedPoints.add(new Point(0, 1));
        expectedPoints.add(new Point(1, -1));
        expectedPoints.add(new Point(1, 0));
        expectedPoints.add(new Point(1, 1));
        assertEquals(expectedPoints, points);
    }

    @Test
    public void cellShouldDieFromUnderPopulation() {
        // given
        Board board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)));
        Cell cell = new Cell(new Point(0, 1));

        // when
        boolean underPopulatedCell = board.cellIsUnderPopulated(cell);

        // then
        assertTrue(underPopulatedCell);
    }

    @Test
    public void cellShouldLiveInNextGeneration() {
        // given
        Board board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)));
        Cell cell = new Cell(new Point(1, 0));

        // when
        boolean shouldLiveInNextGeneration = board.cellShouldLiveInNextGeneration(cell);

        // then
        assertTrue(shouldLiveInNextGeneration);
    }

    @Test
    public void cellShouldDieFromOvercrowding() {
        // given
        Board board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(0, 2)), new Cell(new Point(1, 2)));
        Cell cell = new Cell(new Point(1, 1));

        // when
        boolean dieFromOvercrowding = board.cellIsOvercrowded(cell);

        // then
        assertTrue(dieFromOvercrowding);
    }

    @Test
    public void deadCellShouldAlive() {
        // given
        Board board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(0, 2)));
        Cell cell1 = new Cell(new Point(1, 1));
        Cell cell2 = new Cell(new Point(-1, 1));

        // when
        Collection<Cell> resurrectedCells = board.resurrectDeadCells();

        // then
        Collection<Cell> expectedCells = new HashSet<>();
        expectedCells.add(cell1);
        expectedCells.add(cell2);
        assertEquals(expectedCells, resurrectedCells);
    }

    @Test
    public void boardShouldProperlyManageCellsWhenCreatingNextGeneration() {
        // given
        Board board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)), new Cell(new Point(1, 1)),
                new Cell(new Point(2, 2)), new Cell(new Point(3, 2)), new Cell(new Point(2, 3)), new Cell(new Point(3, 3)));

        // when
        board.createNextGeneration();

        // then
        final Collection<Cell> expectedCells = new HashSet<>(Arrays.asList(new Cell[]{new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)),
                new Cell(new Point(3, 2)), new Cell(new Point(2, 3)), new Cell(new Point(3, 3))}));
        assertEquals(expectedCells, board.getCells());
    }

    @Test
    public void boardShouldRemainsThisSameAfterManyTicksOnStillLifeConfig() {
        // given
        Board board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)), new Cell(new Point(1, 1)));

        // when
        board.createNextGeneration();
        board.createNextGeneration();
        board.createNextGeneration();
        board.createNextGeneration();
        board.createNextGeneration();

        // then
        final Collection<Cell> expectedCells = new HashSet<>(Arrays.asList(new Cell[]{new Cell(new Point(0, 0)),
                new Cell(new Point(0, 1)), new Cell(new Point(1, 0)), new Cell(new Point(1, 1))}));
        assertEquals(expectedCells, board.getCells());
    }
}
