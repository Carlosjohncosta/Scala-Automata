package main

object Grid {
  type CellGrid = List[List[Boolean]]
  val columns: Int = 120
  val rows: Int = 120

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

    def getRange(center: Int, max: Int): Range =
      center match {
        case 0 => 0 to 1
        case x if x == max - 1 => -1 to 0
        case _ => -1 to 1
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
}