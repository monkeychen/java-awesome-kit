package chapter02

import scala.io.StdIn

/**
 * <p>Title: Test05_StdIn</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2022</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2022/9/10 00:19</p>
 */
object Test05_StdIn {
  def main(args: Array[String]): Unit = {
    println("请输入您的大名：")
    val name: String = StdIn.readLine()
    println("请输入年龄：")
    val age: Int = StdIn.readInt()
    println(s"欢迎${age}的${name}来学习Scala!!!")
    // At P18 0分25秒
  }
}
