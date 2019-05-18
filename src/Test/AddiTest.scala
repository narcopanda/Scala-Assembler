//package Test
//
//import java.util
//import java.util.ArrayList
//import Assembler.Assembler
//import org.junit.Assert._
//import org.scalatest.Assertions._
//import org.junit.Test
//
//class AddiTest  {
//
//  val input = Vector[String]()
//
//
//  @Test
//  def Addi(): Unit = {
//    input.updated(0,"addimmediate R5 15")
//    val bt: Array[Byte] = Array(0x95.asInstanceOf[Byte], 0x0f.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def AddiNeg() = {
//    input.updated(0, "addimmediate R5 -1")
//    val bt2: Array[Byte] = Array(0x95.asInstanceOf[Byte], 0xff.asInstanceOf[Byte])
//    assertArrayEquals(bt2, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def AddiNegMax() = {
//    input.updated(0, "addimmediate R5 -127")
//    val bt: Array[Byte] = Array(0x95.asInstanceOf[Byte] , 0x81.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def AddiPosMax() = {
//    input.updated(0, "addimmediate R5 127")
//    val bt: Array[Byte] = Array(0x95.asInstanceOf[Byte] , 0x7f.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def AddiNegOverflow(): Unit = {
//    input.updated(0, "addimmediate R5 -200")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "overflow error")
//  }
//
//  @Test
//  def AddiPosOverflow(): Unit = {
//    input.updated(0 ,"addimmediate R5 200")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input, 0))}
//    assert(ex.getMessage == "overflow error")
//  }
//
//  @Test
//  def AddiRegMaxBounds(): Unit = {
//    input.updated(0, "addimmediate R16 20")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input, 0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def AddiRegLowerBound(): Unit = {
//    input.updated(0, "addimmediate R-1 20")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input, 0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def AddiRegNotInt(): Unit = {
//    input.updated(0, "addimmediate R1.5 20")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input, 0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def AddiRegNotInt2(): Unit = {
//    input.updated(0, "addimmediate R.5 20")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input, 0))}
//    assert(ex.getMessage == "invalid register")
//  }
//}
