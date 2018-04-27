package scalatags.rx

import rx._
import scalatags.JsDom.all._
import scalatags.rx.TestUtils._
import scalatags.rx.all._
import utest._

object RxStyleInstancesSuite extends TestSuite {

  val tests = Tests {
    "string style" - {
      val c = Var("blue")
      "Var" - {
        val node = div(color := c).render
        testRx(c, node.style.getPropertyValue("color"), "blue", "green")
      }
      "Rx" - {
        val node = div(color := c.rx).render
        testRx(c, node.style.getPropertyValue("color"), "blue", "green")
      }
      "Rx.Dynamic" - {
        val node = div(color := Rx(c())).render
        testRx(c, node.style.getPropertyValue("color"), "blue", "green")
      }
    }
    "string pixel style" - {
      val c = Var("10px")
      "Var" - {
        val node = div(width := c).render
        testRx(c, node.style.getPropertyValue("width"), "10px", "20px")
      }
      "Rx" - {
        val node = div(width := c.rx).render
        testRx(c, node.style.getPropertyValue("width"), "10px", "20px")
      }
      "Rx.Dynamic" - {
        val node = div(width := Rx(c())).render
        testRx(c, node.style.getPropertyValue("width"), "10px", "20px")
      }
    }
    "int pixel style" - {
      val c = Var(10)
      "Var" - {
        val node = div(width := c).render
        testRx(c, node.style.getPropertyValue("width"), "10px", 20 -> "20px")
      }
      "Rx" - {
        val node = div(width := c.rx).render
        testRx(c, node.style.getPropertyValue("width"), "10px", 20 -> "20px")
      }
      "Rx.Dynamic" - {
        val node = div(width := Rx(c())).render
        testRx(c, node.style.getPropertyValue("width"), "10px", 20 -> "20px")
      }
    }
  }
}
