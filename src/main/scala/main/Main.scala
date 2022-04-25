package main
import processing.core.PApplet

class Main extends PApplet {
  val LifeAlg = new CGOL(100, 100, this)
  type CellGrid = LifeAlg.CellGrid
  val cellSize = 5
  var grid: CellGrid = LifeAlg.newGrid

  override def settings(): Unit =
    size(LifeAlg.rows * cellSize, LifeAlg.columns * cellSize)

  override def setup(): Unit = {
    fill(0, 0, 0)
    stroke(0, 255, 0)
  }

  override def draw(): Unit = {
    background(0)
    LifeAlg.draw(grid)
    grid = LifeAlg.nextItt(grid)
  }

  override def keyPressed(): Unit =
    grid = LifeAlg.newGrid
}

object Main extends App { PApplet.main("main.Main") }