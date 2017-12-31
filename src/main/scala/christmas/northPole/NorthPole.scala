package christmas.northPole

import scala.io.Source

trait Presents {
  def getResourceLines(filename: String): Iterator[String] = {
    Source.fromInputStream(getClass.getResourceAsStream(s"/${getClass.getPackage.getName}/$filename")).getLines
  }
}

