package scalatags.rx

import rx._
import scalatags.JsDom.all._
import scalatags.rx.TestUtils._
import scalatags.rx.all._
import utest._

object RxAttrInstancesSuite extends TestSuite {

  val tests = Tests {
    "string attribute" - {
      val c = Var("10px")
      "Var" - {
        val node = div(widthA := c).render
        testRx(c, node.getAttribute("width"), "10px", "20px")
      }
      "Rx" - {
        val node = div(widthA := c.rx).render
        testRx(c, node.getAttribute("width"), "10px", "20px")
      }
      "Rx.Dynamic" - {
        val direction = Var("up")
        val node = div(cls := Rx("icon-chevron-" + direction())).render
        testRx(direction, node.getAttribute("class"), "icon-chevron-up", "down" -> "icon-chevron-down")
      }
    }
    "int attribute" - {
      val c = Var(10)
      "Var" - {
        val node = div(widthA := c).render
        testRx(c, node.getAttribute("width"), "10", 20 -> "20")
      }
      "Rx" - {
        val node = div(widthA := c.rx).render
        testRx(c, node.getAttribute("width"), "10", 20 -> "20")
      }
      "Rx.Dynamic" - {
        val node = div(widthA := Rx(c())).render
        testRx(c, node.getAttribute("width"), "10", 20 -> "20")
      }
    }
  }

}
