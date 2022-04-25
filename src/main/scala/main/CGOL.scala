 package main

class CGOL(val columns: Int, val rows: Int, main: Main) extends AutomataTraits {
  type CellGrid = List[List[Boolean]]
  def newGrid: CellGrid =
    List.fill[List[Boolean]](rows) {
      List.fill[Boolean](columns)(Math.floor(Math.random() * 4) == 0)
    }

  //Returns next iteration of grid
  def nextItt(grid: CellGrid): CellGrid  = {

    def checkAdjacent(xIndex: Int, yIndex: Int): Int =
      getRange(xIndex, rows).fold(0) {
        (prev: Int, rowOff: Int) =>
          prev + getRange(yIndex, columns).fold(0) {
            (prev: Int, columnOff: Int) =>
              prev + (if((rowOff == 0) && (columnOff == 0)) 0 else
                if(grid(rowOff + xIndex)(columnOff + yIndex)) 1 else 0)
          }
      }

    grid.zipWithIndex map {
      case(row, xIndex) =>
        row.zipWithIndex map {
          case (cell, yIndex) =>
            val numAdjacent = checkAdjacent(xIndex, yIndex)
            if(cell){
              if(numAdjacent > 1 && numAdjacent < 4)
                true
              else
                false
            } else {
              if(numAdjacent == 3)
                true
              else
                false
            }
        }
    }
  }

  def draw(grid: CellGrid): Unit = {
    main.background(0)
    grid.zipWithIndex foreach {
      case(row, xIndex) =>
        row.zipWithIndex foreach {
          case(cell, yIndex) =>
            if (cell) main.square(xIndex * main.cellSize, yIndex * main.cellSize, main.cellSize)
        }
    }
  }

}