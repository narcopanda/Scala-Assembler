package Test

import Assembler.Assembler
import org.junit.Assert._
import org.scalatest.Assertions._
import org.junit.Test

import scala.collection.mutable.ArrayBuffer

//class IterateoverTest {
//
//  val input: ArrayBuffer[String] = ArrayBuffer[String]()
//
//  @Test
//  def iterateoverNormal(): Unit = {
//    input += ("iterateover r1 1 1")
//    val bt: Array[Byte] = Array(0xd1.asInstanceOf[Byte], 0x01.asInstanceOf[Byte], 0x00, 0x01.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def iterateoverHighJump(): Unit = {
//    input += ("iterateover r1 1 1000")
//    val bt: Array[Byte] = Array(0xd1.asInstanceOf[Byte], 0x01.asInstanceOf[Byte], 0x03.asInstanceOf[Byte], 0xe8.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input,0)))
//  }
//
//  @Test
//  def iterateoverMaxJump(): Unit = {
//    input += ("iterateover r1 1 65535")
//    val bt: Array[Byte] = Array(0xd1.asInstanceOf[Byte], 0x01.asInstanceOf[Byte], 0xff.asInstanceOf[Byte], 0xff.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def iterateoverMaxJumpOverflow(): Unit = {
//    input += ("iterateover r1 1 65536")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "jump offset is too big")
//  }
//
//  @Test
//  def iterateoverMaxPointer(): Unit = {
//    input += ("iterateover r1 255 65535")
//    val bt: Array[Byte] = Array(0xd1.asInstanceOf[Byte], 0xff.asInstanceOf[Byte], 0xff.asInstanceOf[Byte], 0xff.asInstanceOf[Byte])
//    assertArrayEquals(bt, Assembler.instructionParser(Assembler.stringToken(input, 0)))
//  }
//
//  @Test
//  def iterateoverMaxPointerOverflow(): Unit = {
//    input += ("iterateover r1 300 65535")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "pointer offset is too big")
//  }
//
//  @Test
//  def iterateoverRegMaxOverflow(): Unit = {
//    input += ("iterateover r16 1 65535")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//  @Test
//  def iterateoverRegMinOverflow(): Unit = {
//    input += ("iterateover r-1 1 65535")
//    val ex = intercept[AssertionError] {Assembler.instructionParser(Assembler.stringToken(input,0))}
//    assert(ex.getMessage == "invalid register")
//  }
//
//}
