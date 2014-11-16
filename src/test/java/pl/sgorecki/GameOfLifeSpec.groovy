package pl.sgorecki

import spock.lang.Specification

/**
 * @author Sebastian Górecki <gorecki.sebastian@gmail.com>
 */
class GameOfLifeSpec extends Specification {

    def "cell should return its neighbours positions"() {
        given:
        def cell = new Cell(new Point(0, 0))

        when:
        def neighboursPositions = cell.getNeighboursPositions()

        then:
        def expectedPositions = [new Point(-1, -1), new Point(-1, 0), new Point(-1, 1), new Point(0, -1), new Point(0, 1),
                                 new Point(1, -1), new Point(1, 0), new Point(1, 1)] as Set
        expectedPositions == neighboursPositions
    }

    def "cell should die from under-population"() {
        given:
        def board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)))
        def cell = new Cell(new Point(0, 1));

        when:
        def underPopulatedCell = board.cellIsUnderPopulated(cell);

        then:
        underPopulatedCell == true
    }

    def "board can determine whether cell shold live in next generation"() {
        given:
        def board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)));
        def cell = new Cell(new Point(1, 0));

        when:
        def shouldLiveInNextGeneration = board.cellShouldLiveInNextGeneration(cell);

        then:
        shouldLiveInNextGeneration == true
    }

    def "cell should die because of overcorwding"() {
        given:
        def board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(0, 2)), new Cell(new Point(1, 2)));
        def cell = new Cell(new Point(1, 1));

        when:
        def dieFromOvercrowding = board.cellIsOvercrowded(cell);

        then:
        dieFromOvercrowding == true
    }

    def "board should resurrect proper cells"() {
        given:
        def board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(0, 2)));
        def cell1 = new Cell(new Point(1, 1));
        def cell2 = new Cell(new Point(-1, 1));
        def expectedCells = [cell1, cell2] as Set

        when:
        def resurrectedCells = board.resurrectDeadCells();

        then:
        expectedCells == resurrectedCells
    }

    def "board should properly manage cells when creating next generation"() {
        given:
        def board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)), new Cell(new Point(1, 1)),
                new Cell(new Point(2, 2)), new Cell(new Point(3, 2)), new Cell(new Point(2, 3)), new Cell(new Point(3, 3)));
        def expectedCells = [new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)),
                             new Cell(new Point(3, 2)), new Cell(new Point(2, 3)), new Cell(new Point(3, 3))] as Set

        when:
        board.createNextGeneration()

        then:
        expectedCells == board.getCells()
    }

    def "board should remains this same after many ticks on 'still' cells config" () {
        given:
        def board = new Board(new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)), new Cell(new Point(1, 1)));
        def expectedCells = [new Cell(new Point(0, 0)), new Cell(new Point(0, 1)), new Cell(new Point(1, 0)), new Cell(new Point(1, 1))] as Set

        when:
        board.createNextGeneration();
        board.createNextGeneration();
        board.createNextGeneration();
        board.createNextGeneration();
        board.createNextGeneration();

        then:
        expectedCells == board.getCells()
    }
}
