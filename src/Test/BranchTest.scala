//package Test
//
//import java.util
//import java.util.ArrayList
//import Assembler.Assembler
//import org.junit.Assert._
//import org.scalatest.Assertions._
//import org.junit.Test
//
//class BranchTest {
//
//  val input: ArrayList[String] = new util.ArrayList[String]()
//
//  @Test
//  def BranchMaxOverFlow(): Unit = {
//    input.add("branchifequal R1 R2 524287")
//    val ex = intercept[AssertionError]{Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "Offset is too big")
//  }
//
//  @Test
//  def BranchMinOverFlow(): Unit = {
//    input.add("branchifequal R1 R2 -524287")
//    val ex = intercept[AssertionError]{Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "Offset is too big")
//  }
//
//  @Test
//  def BranchNormalPos(): Unit = {
//    input.add("branchifequal R1 R2 5")
//    val bt: Array[Byte] = Array(0xa1.asInstanceOf[Byte], 0x20.asInstanceOf[Byte], 0x0.asInstanceOf[Byte], 0x05.asInstanceOf[Byte])
//    println(Assembler.bytes2hex(bt))
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def BranchNormalNeg(): Unit = {
//    input.add("branchifequal R1 R2 -1")
//    val bt: Array[Byte] = Array(0xa1.asInstanceOf[Byte], 0x2f.asInstanceOf[Byte], 0xff.asInstanceOf[Byte], 0xff.asInstanceOf[Byte])
//    println(Assembler.bytes2hex(bt))
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//}
