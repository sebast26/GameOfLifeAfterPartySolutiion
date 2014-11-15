package pl.sgorecki;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

}
