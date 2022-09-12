package chapter02

import base.Student

/**
 * <p>Title: Test07_DataType</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2022</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2022/9/12 18:57</p>
 */
object Test07_DataType {
  def main(args: Array[String]): Unit = {
    // 整数类型
    val a1: Byte = 127
    val a2: Byte = -128
//    val a2: Byte = 128 // 编译阶段就会报数值溢出错误
    val a3 = 12 // 默认类型为Int
    val a4 = 3000000000L // 强制指定为Long类型，Int类型的最大值大约为20多亿，所以需要显式初始化为Long

    val b1: Byte = 10  // 10虽然为Int，但没超过Byte的取值范围，所以可以直接赋值给Byte类型的变量b1，不会报错。
    val b2: Byte = (10 + 20)  // 不会报错，因为计算结果在Byte取值范围
//    val b3: Byte = b1 + 20 // 报错，涉及到变量，比较特殊。
    val b3: Byte = (b1 + 20).toByte
    println((b1 + 20).getClass)
    println(b3)

    // 浮点类型
    val f1: Float = 3.14159
    val d1: Double = 3.14159
    val d2 = 3.14159

    // 字符类型
    val c1: Char = 'a'
    val c2: Char = '9'
    val i1: Int = c1
    val i2: Int = c2
    println(i1 + ", " + i2)
    println((i1 + 1).toChar)
    println((i2 - 1).toChar)

    // 布尔类型
    val boolVal: Boolean = true
    println(boolVal)

    // 空类型
    // 空值Unit
    def m1(): Unit = {
      println("m1被调用")
    }
    val a: Unit = m1()
    println(a)

    // 空引用Null
//    val n: Int = null // error
    var student = new Student("cza", 33)
    student.printInfo()
    student = null
    println(student)

    def m2(n: Int): Nothing = {
      throw new NullPointerException()
    }

    def m3(n: Int): Int = {
      if (n == 0) {
        throw new NullPointerException()
      } else {
        n
      }
    }
    println(m3(22222))
    val b = m2(0)
    println("This line not execute!!!=========>" + b)

    // P32
  }
}
