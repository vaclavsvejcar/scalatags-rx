package scalatags.rx

import org.scalajs.dom._
import rx._
import scalatags.JsDom
import scalatags.JsDom.all._
import scalatags.generic.{Style, StylePair}

import scala.language.implicitConversions

trait RxStyleInstances {

  implicit def varIsRxForStyleValue[T](implicit x: StyleValue[Rx[T]],
                                       ctx: Ctx.Owner): StyleValue[Var[T]] =
    (t: Element, s: Style, v: Var[T]) => x.apply(t, s, v)

  implicit def varIsRxForPixelStyleValue[T](implicit x: PixelStyleValue[Rx[T]],
                                            ctx: Ctx.Owner): PixelStyleValue[Var[T]] =
    (s: Style, v: Var[T]) => x.apply(s, v)

  implicit def rxDynIsRxForStyleValue[T](implicit x: StyleValue[Rx[T]],
                                         ctx: Ctx.Owner): StyleValue[Rx.Dynamic[T]] =
    (t: Element, s: Style, v: Rx.Dynamic[T]) => x.apply(t, s, v)

  implicit def rxDynIsRxForPixelStyleValue[T](implicit x: PixelStyleValue[Rx[T]],
                                              ctx: Ctx.Owner): PixelStyleValue[Rx.Dynamic[T]] =
    (s: Style, v: Rx.Dynamic[T]) => x.apply(s, v)

  implicit def rxStyleValue[T: StyleValue](implicit ctx: Ctx.Owner): StyleValue[Rx[T]] =
    new RxStyleValue[T]

  def rxPixelStyleValue[T: StyleValue](implicit ctx: Ctx.Owner): PixelStyleValue[Rx[T]] =
    genericPixelStyle[Rx[T]]

  def rxPixelStyleValuePx[T](implicit ev: StyleValue[Rx[String]], ctx: Ctx.Owner): PixelStyleValue[Rx[T]] =
    new RxGenericPixelStylePx[T](ev)

  implicit def rxStringPixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[String]] =
    rxPixelStyleValue[String]

  implicit def rxBooleanPixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Boolean]] =
    rxPixelStyleValue[Boolean]

  implicit def rxBytePixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Byte]] =
    rxPixelStyleValuePx[Byte]

  implicit def rxShortPixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Short]] =
    rxPixelStyleValuePx[Short]

  implicit def rxIntPixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Int]] =
    rxPixelStyleValuePx[Int]

  implicit def rxLongPixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Long]] =
    rxPixelStyleValuePx[Long]

  implicit def rxFloatPixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Float]] =
    rxPixelStyleValuePx[Float]

  implicit def rxDoublePixelStyle(implicit ctx: Ctx.Owner): JsDom.all.PixelStyleValue[Rx[Double]] =
    rxPixelStyleValuePx[Double]

  class RxStyleValue[T](implicit sv: StyleValue[T], ctx: Ctx.Owner) extends StyleValue[Rx[T]] {
    override def apply(t: Element, s: Style, rv: Rx[T]): Unit =
      rv foreach { v => sv.apply(t, s, v) }
  }

  class RxGenericPixelStylePx[T](ev: StyleValue[Rx[String]])
                                (implicit ctx: Ctx.Owner) extends PixelStyleValue[Rx[T]] {
    def apply(s: Style, v: Rx[T]) = StylePair(s, v.map(_ + "px"), ev)
  }

}
