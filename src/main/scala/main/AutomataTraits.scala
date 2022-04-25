package main

trait AutomataTraits {
  def getRange(center: Int, max: Int): Range =
    center match {
      case 0 => 0 to 1
      case x if x == max - 1 => -1 to 0
      case _ => -1 to 1
    }
}
