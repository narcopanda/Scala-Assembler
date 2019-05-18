//package Test
//
//import java.util.ArrayList
//import Assembler.Assembler
//import org.junit.Assert._
//import org.scalatest.Assertions._
//import org.junit.Test
//
//class InterruptTest {
//
//  val input: ArrayList[String] = new ArrayList[String]()
//
//
//  @Test
//  def interrupt1() = {
//    input.add("interrupt 1")
//    val bt: Array[Byte] = Array(0x80.asInstanceOf[Byte], 0x1.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def interrupt0() = {
//    input.add("interrupt 0")
//    val bt: Array[Byte] = Array(0x80.asInstanceOf[Byte], 0x0.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def interrupt100() = {
//    input.add("interrupt 100")
//    val bt: Array[Byte] = Array(0x80.asInstanceOf[Byte], 0x64.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//import org.scalatest.Assertions._
//  @Test
//  def interruptOverflow() = {
//    input.add("interrupt 5000")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "interrupt overfull")
//  }
//}
