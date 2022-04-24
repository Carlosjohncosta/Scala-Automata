package main
import processing.core.PApplet

class Main extends PApplet {
  type CellGrid = Grid.CellGrid
  val cellSize = 5
  var grid: CellGrid = Grid.newGrid

  @Override
  override def settings(): Unit =
    size(Grid.rows * cellSize, Grid.columns * cellSize)

  @Override
  override def setup(): Unit = {
    fill(0, 0, 0)
    stroke(0, 255, 0)
  }

  @Override
  override def draw(): Unit = {
    background(0)
    grid.zipWithIndex foreach {
      case(row, xIndex) =>
        row.zipWithIndex foreach {
          case(cell, yIndex) =>
            if (cell) square(xIndex * cellSize, yIndex * cellSize, cellSize)
        }
    }
    grid = Grid.nextItt(grid)
  }
}

object Main extends App { PApplet.main("main.Main") }