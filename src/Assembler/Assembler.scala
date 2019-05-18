package Assembler

import java.util.StringTokenizer

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

//change object to class in order to call it in java
object Assembler {

  def assembler(input: ArrayBuffer[String]): ArrayBuffer[Array[Byte]]= {
    @tailrec
    def looper(input: ArrayBuffer[String], counter: Int, output: ArrayBuffer[Array[Byte]]): ArrayBuffer[Array[Byte]] = {
      if(input.length == counter) output
      else{
        val newInput = stringToken(input, counter)
//        val buffer = new ArrayBuffer[String]()
//        val newInput = {
//          input(counter).split(" ").copyToBuffer(buffer)
//          buffer
//        }
        output.updated(counter, instructionParser(newInput))
        looper(input, counter+1, output)
      }
    }
    val output = ArrayBuffer[Array[Byte]]()
    looper(input, 0, output)
  }

  def instructionParser(input: ArrayBuffer[String]): Array[Byte] = {
    input.apply(0) match {
      case "addimmediate" => {
        val num: Int = Integer.parseInt(input.apply(2))
        if (num > 127 || num < -127) throw new AssertionError("overflow error")
        else if(input.apply(1).substring(0,1) != "r") throw new AssertionError("invalid register letter")
        else {
          try{
            val reg: Int = checkReg(Integer.parseInt(input.apply(1).substring(1)))
            twoByteBuilder(9, reg, num)
          }catch {case _: Throwable => throw new AssertionError("invalid register")}
        }
      }

      // R type fns
      case "halt" => getRType(input, 0) //can prob just return Array[Byte] = Array(0x0.asInstance[Byte]
      case "add" => getRType(input, 1)
      case "and" => getRType(input, 2)
      case "divide" => getRType(input, 3)
      case "multiply" => getRType(input, 4)
      case "subtract" => getRType(input, 5)
      case "or" => getRType(input, 6)

      // Branch
      case "branchifequal" => getBranch(input, 10)
      case "branchifless" => getBranch(input, 11)

      case "interrupt" => interrupt(input)

      case "iterateover" => iter(input, 13)
      case "jump" => jmp(input, 12)
      case "load" => ls(input, 14)
      case "store" => ls(input, 15)
      case "shiftleft" => shift(input, "l")
      case "shiftright" => shift(input, "r")

      case _ => throw new AssertionError("invalid instruction")
    }
  }

  // generate a string token
  def stringToken(array: ArrayBuffer[String], counter: Int): ArrayBuffer[String] = {
    val outputArray = ArrayBuffer[String]()
    val str = new StringTokenizer(array.apply(counter))

    @tailrec
    def looper(str: StringTokenizer, output: ArrayBuffer[String]): ArrayBuffer[String] = {
      if (str.hasMoreTokens) {
        output += str.nextToken()
        looper(str, output)
      } else output
    }
    looper(str, outputArray)
  }


//  use for addi
  def twoByteBuilder(op: Int, reg: Int, third: Int): Array[Byte] = {
    val array: Array[Byte] = new Array[Byte](2)
    val getHex: Byte = {(op.asInstanceOf[Byte] << 4) | reg.asInstanceOf[Byte]}.asInstanceOf[Byte]
    array(0) = getHex
    array(1) = third.asInstanceOf[Byte]
    array
  }

  def checkReg(num: Int): Int = {
    if (num > 15 || num < 0) throw new AssertionError("invalid register")
    else num
  }

  def checkInstruction(input: ArrayBuffer[String]): ArrayBuffer[String] = {
    if (input.length != 4) throw new AssertionError("too many or not enough registers")
    else {
      if(input.apply(1).substring(0,1).equals("r") && input.apply(2).substring(0,1).equals("r") && input.apply(3).substring(0,1).equals("r")) input
      else throw new AssertionError("invalid register letter")
    }
  }

  def checkIter(input: ArrayBuffer[String]): ArrayBuffer[String] = {
    if (input.length != 4) throw new AssertionError("too many or not enough registers")
    else {
      if(input.apply(1).substring(0,1).equals("r")) input
      else throw new AssertionError("invalid register letter")
    }
  }

  def getRType(input: ArrayBuffer[String], op: Int): Array[Byte] = {
    checkInstruction(input)
    val array = new Array[Byte](2)
    val opByte: Byte = op.asInstanceOf[Byte]
    val shiftFirstTwo: Byte = { (opByte << 4) | checkReg(Integer.parseInt(input.apply(1).substring(1))).asInstanceOf[Byte]}
      .asInstanceOf[Byte]
    val shiftLastTwo: Byte = { (checkReg(Integer.parseInt(input.apply(2).substring(1))).asInstanceOf[Byte] << 4)|
      checkReg(Integer.parseInt(input.apply(3).substring(1))).asInstanceOf[Byte]}.asInstanceOf[Byte]
    array(0) = shiftFirstTwo
    array(1) = shiftLastTwo
    array
  }

  //need to fix this
  def getBranch(input: ArrayBuffer[String], op: Int): Array[Byte] = {
//    checkInstruction(input)
    val arr = new Array[Byte](4)
    val shiftOpAndReg: Byte = { (op.asInstanceOf[Byte] << 4) | Integer.parseInt(input.apply(1).substring(1)).asInstanceOf[Byte]}.asInstanceOf[Byte]
    val shiftLastReg: Byte = { Integer.parseInt(input.apply(2).substring(1)).asInstanceOf[Byte] << 4}.asInstanceOf[Byte]
    val offset = Integer.parseInt(input.apply(3)).asInstanceOf[Byte]

    arr(3) = checkBranchOffset(Integer.parseInt(input.apply(3))).asInstanceOf[Byte]
    arr(2) = (Integer.parseInt(input.apply(3)).asInstanceOf[Byte] >> 8).asInstanceOf[Byte]
    arr(1) = (shiftLastReg | ((offset >> 16).asInstanceOf[Byte]) & 0x0f.asInstanceOf[Byte]).asInstanceOf[Byte]
    arr(0) = shiftOpAndReg
    arr
 }

  def checkBranchOffset(input: Int):Int = {
    if (input > 524286 || input < -524286) throw new AssertionError("Offset is too big")
    else input
  }

  def interrupt(input: ArrayBuffer[String]): Array[Byte] = {
        val array: Array[Byte] = new Array[Byte](2)
        array(1) = {
          if(Integer.parseInt(input.apply(1)) > 4095) throw new AssertionError("interrupt overfull")
          Integer.parseInt(input.apply(1)).asInstanceOf[Byte]
        }
        array(0) = (8.asInstanceOf[Byte] << 4).asInstanceOf[Byte]
        array
  }

  def iter(input: ArrayBuffer[String], op: Int):Array[Byte] = {
    checkIter(input)
    val arr = new Array[Byte](4)
    val shiftOpAndReg: Byte = {(op.asInstanceOf[Byte] << 4) | checkReg(Integer.parseInt(input.apply(1).substring(1)))
      .asInstanceOf[Byte]}.asInstanceOf[Byte]
    val pointerOffset: Byte = {
      if(Integer.parseInt(input.apply(2)) > 255) throw new AssertionError("pointer offset is too big")
      else Integer.parseInt(input.apply(2)).asInstanceOf[Byte]
    }
    arr(3) = {
      if(Integer.parseInt(input.apply(3)) > 65535) throw new AssertionError("jump offset is too big")
      else Integer.parseInt(input.apply(3)).asInstanceOf[Byte]
    }
    arr(2) = (Integer.parseInt(input.apply(3)) >> 8).asInstanceOf[Byte]
    arr(1) = pointerOffset
    arr(0) = shiftOpAndReg
    arr
  }

  def jmp(input: ArrayBuffer[String], op: Int): Array[Byte] = {
    val arr = new Array[Byte](4)
    arr(3) = {
      if(Integer.parseInt(input.apply(1)) > 536870911 || Integer.parseInt(input.apply(1)) < 0) throw new AssertionError("jump number is too big")
      else Integer.parseInt(input.apply(1)).asInstanceOf[Byte]
    }
    arr(2) = (Integer.parseInt(input.apply(1)) >> 8).asInstanceOf[Byte]
    arr(1) = (Integer.parseInt(input.apply(1)) >> 2).asInstanceOf[Byte]
    arr(0) = (op.asInstanceOf[Byte] << 4).asInstanceOf[Byte]
    arr
  }

  def ls(input: ArrayBuffer[String], op: Int): Array[Byte] = {
    checkInstruction(input)
    val arr = new Array[Byte](2)
    val getHex: Byte = {(op.asInstanceOf[Byte] << 4) | Integer.parseInt(input.apply(1).substring(1))
      .asInstanceOf[Byte]}.asInstanceOf[Byte]
    val getLast = {(Integer.parseInt(input.apply(1).substring(1)).asInstanceOf[Byte] << 4)|
      Integer.parseInt(input.apply(1)).asInstanceOf[Byte]}.asInstanceOf[Byte]
    arr(1) = getLast
    arr(0) = getHex
    arr
  }

  def shift(input: ArrayBuffer[String], ty: String) = {
    if(input.length != 3) throw new AssertionError("too many or not enough registers")
    val arr = new Array[Byte](2)
    ty match {
      case "l" =>
        arr(1) = {
          if(Integer.parseInt(input.apply(2)) > 31) throw new AssertionError("shift amount is too big")
          else (Integer.parseInt(input.apply(2)).asInstanceOf[Byte] & 0x1f.asInstanceOf[Byte]).asInstanceOf[Byte]
        }
        arr(0) = (7.asInstanceOf[Byte] << 4 | checkReg(Integer.parseInt(input.apply(1).substring(1)))
          .asInstanceOf[Byte]).asInstanceOf[Byte]
        arr
      case "r" =>
        arr(1) = {
          if(Integer.parseInt(input.apply(2)) > 31) throw new AssertionError("shift amount is too big")
          else ((Integer.parseInt(input.apply(2)).asInstanceOf[Byte] & 0x1f.asInstanceOf[Byte]).asInstanceOf[Byte] |
            0x20.asInstanceOf[Byte]).asInstanceOf[Byte]
        }
        arr(0) = (7.asInstanceOf[Byte] << 4 | checkReg(Integer.parseInt(input.apply(1).substring(1)))
          .asInstanceOf[Byte]).asInstanceOf[Byte]
        arr
    }

  }

  // helper
  def bytes2hex(bytes: Array[Byte], sep: Option[String] = None): String = {
    sep match {
      case None => bytes.map("%02x".format(_)).mkString
      case _ => bytes.map("%02x".format(_)).mkString(sep.get)
    }
  }

  def main(args: Array[String]): Unit = {
    val input = ArrayBuffer[String]()
//    input += "iterateover r1 100 100"
    input += "jump 20"
//    val newArray = stringToken(input,0)
    println(bytes2hex(instructionParser(stringToken(input,0))))
//    println(newArray.apply(1).substring(1))

//    val vectest = Vector("add r1 r2 r3")
//    println(vectest.apply(0).substring(3,4))
//    println(bytes2hex(instructionParser(stringToken(input,0))))
  }

}
