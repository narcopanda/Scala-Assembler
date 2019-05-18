//package Test
//
//import java.util
//import java.util.ArrayList
//import Assembler.Assembler
//import org.junit.Assert._
//import org.scalatest.Assertions._
//import org.junit.Test
//
//class ThreeRTest {
//
//  val input: ArrayList[String] = new util.ArrayList[String]()
//
//
//  @Test
//  def addNormal():Unit = {
//   input.add("add r1 r2 r3")
//   val bt: Array[Byte] = Array(0x11.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//   assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def addTooMany():Unit = {
//    input.add("add r1 r2 r2 r3")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "too many or not enough registers")
//  }
//
//  @Test
//  def addTooFew():Unit = {
//    input.add("add r1 r2 ")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "too many or not enough registers")
//  }
//
//  @Test
//  def orNormal(): Unit = {
//    input.add("or r1 r2 r3")
//    val bt: Array[Byte] = Array(0x61.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def andNormal(): Unit = {
//    input.add("and r1 r2 r3")
//    val bt: Array[Byte] = Array(0x21.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def divideNormal(): Unit = {
//    input.add("divide r1 r2 r3")
//    val bt: Array[Byte] = Array(0x31.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
// @Test
//  def multiplyNormal(): Unit = {
//    input.add("multiply r1 r2 r3")
//    val bt: Array[Byte] = Array(0x41.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def subtractNormal(): Unit = {
//    input.add("subtract r1 r2 r3")
//    val bt: Array[Byte] = Array(0x51.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def haltNormal(): Unit = {
//    input.add("halt r1 r2 r3")
//    val bt: Array[Byte] = Array(0x01.asInstanceOf[Byte], 0x23.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def maxOverflow(): Unit = {
//    input.add("subtract r16 r2 r3")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def maxOverflowR2(): Unit = {
//    input.add("subtract r1 r20 r3")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def maxOverflowR3(): Unit = {
//    input.add("subtract r1 r2 r30")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def minOverflow(): Unit = {
//    input.add("subtract r1 r-2 r3")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "invalid register")
//  }
//}
