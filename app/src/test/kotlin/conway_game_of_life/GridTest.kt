package conway_game_of_life

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class GridTest {

    @Test
    fun `Should update state`() {
        val initialState = arrayOf(
            arrayOf(true, false, false),
            arrayOf(false, false, true),
            arrayOf(true, false, true),
        )
        val grid = Grid(3, 3, initialState)
        val finalState = arrayOf(
            arrayOf(false, false, false),
            arrayOf(false, false, false),
            arrayOf(false, true, false),
        )

        grid.updateGridState()

        assertArrayEquals(finalState, grid.getState())
    }

    @Test
    fun `Should any live cell with more than three live neighbours dies, as if by overpopulation`() {
        val initialState = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true),
        )
        val grid = Grid(3, 3, initialState)
        val finalState = arrayOf(
            arrayOf(true, false, true),
            arrayOf(false, false, false),
            arrayOf(true, false, true),
        )

        grid.updateGridState()

        assertArrayEquals(finalState, grid.getState())
    }

    @Test
    fun `Should satisfy line pattern`() {
        val initialState = arrayOf(
            arrayOf(false, false, false),
            arrayOf(true, true, true),
            arrayOf(false, false, false),
        )
        val grid = Grid(3, 3, initialState)
        val finalState = arrayOf(
            arrayOf(false, true, false),
            arrayOf(false, true, false),
            arrayOf(false, true, false),
        )

        grid.updateGridState()

        assertArrayEquals(finalState, grid.getState())
    }

    @Test
    fun `Should satisfy still life pattern`() {
        val initialState = arrayOf(
            arrayOf(false, false, false, false),
            arrayOf(false, true, true, false),
            arrayOf(false, true, true, false),
        )
        val grid = Grid(3, 3, initialState)
        val finalState = arrayOf(
            arrayOf(false, false, false, false),
            arrayOf(false, true, true, false),
            arrayOf(false, true, true, false),
        )

        grid.updateGridState()

        assertArrayEquals(finalState, grid.getState())
    }
}