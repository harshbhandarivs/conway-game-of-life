package conway_game_of_life

class Grid(private val numberOfRows: Int, private val numberOfCols: Int, private var grid: Array<Array<Boolean>>) {

    fun updateGridState() {
        val oldGrid = getClone()
        for (rowIndex in 0 until numberOfRows) {
            for (colIndex in 0 until numberOfCols) {
                val neighbours = getNeighbours(rowIndex, colIndex, oldGrid)
                val groupNeighbours = neighbours.groupingBy { it }.eachCount()
                val populatedNeighboursCount = groupNeighbours[true] ?: 0
                updateCellState(oldGrid, rowIndex, colIndex, populatedNeighboursCount)
            }
        }
    }

    private fun getClone(): Array<Array<Boolean>> {
        val oldGrid = Array(numberOfRows) { Array(numberOfCols) { false } }
        for (rowIndex in 0 until numberOfRows) {
            for (colIndex in 0 until numberOfCols) {
                oldGrid[rowIndex][colIndex] = grid[rowIndex][colIndex]
            }
        }
        return oldGrid
    }

    private fun updateCellState(
        oldGrid: Array<Array<Boolean>>,
        rowIndex: Int,
        colIndex: Int,
        populatedNeighboursCount: Int
    ) {
        when {
            oldGrid[rowIndex][colIndex] && populatedNeighboursCount < 2 ->
                grid[rowIndex][colIndex] = false

            oldGrid[rowIndex][colIndex] && populatedNeighboursCount > 3 ->
                grid[rowIndex][colIndex] = false

            !oldGrid[rowIndex][colIndex] && populatedNeighboursCount == 3 ->
                grid[rowIndex][colIndex] = true
        }
    }

    private fun getNeighbours(rowIndex: Int, colIndex: Int, oldGrid: Array<Array<Boolean>>): List<Boolean> {
        val neighbours = mutableListOf<Boolean>()
        val neighboursDelta =
            arrayOf(
                arrayOf(-1, -1),
                arrayOf(-1, 0),
                arrayOf(-1, 1),
                arrayOf(0, -1),
                arrayOf(0, 1),
                arrayOf(1, -1),
                arrayOf(1, 0),
                arrayOf(1, 1)
            )
        for (neighbourDelta in neighboursDelta) {
            val neighbourRowIndex = rowIndex + neighbourDelta[0]
            val neighbourColIndex = colIndex + neighbourDelta[1]
            if (isBoundedNeighbour(neighbourRowIndex, neighbourColIndex)) {
                neighbours.add(oldGrid[neighbourRowIndex][neighbourColIndex])
            }
        }
        return neighbours.toList()
    }

    private fun isBoundedNeighbour(row: Int, col: Int): Boolean {
        return row in 0 until numberOfRows && col in 0 until numberOfCols
    }

    fun getState(): Array<Array<Boolean>> {
        return grid.clone()
    }
}
