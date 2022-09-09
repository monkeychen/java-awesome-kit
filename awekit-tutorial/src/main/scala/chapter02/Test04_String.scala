package chapter02

/**
 * <p>Title: Test04_String</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2022</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2022/9/10 00:25</p>
 */
object Test04_String {
  def main(args: Array[String]): Unit = {
    val name: String = "simiam"
    val age: Int = 33
    println(age + "岁的" + name + "在学习scala!!!")

    println(name * 5)

    printf("%d岁的%s在学习Scala!!\n", age, name)

    // 字符串模板（插值字符串）
    println(s"${age}岁的${name}在学习Scala!!!")

    // 格式化字符串模板
    val num: Double = 4.3467
    println(f"${age}岁的${name}在学习Scala1,num1=${num}%2.3f!!!")
    println(f"$age 岁的${name}在学习Scala2,num2=${num}%8.2f!!!")
    println(raw"$age 岁的${name}在学习Scala3,num3=${num}%8.2f!!!")

    // 三引号多行输出
    val sql1 = s"""
        |select
        |  *
        |from
        |  student
        |where
        |  name = $name and age > ${age}
        |""".stripMargin
    val sql2 = s"""
        |select
        |  *
        |from
        |  student
        |where
          name = $name and age > ${age}
        |""".stripMargin

      println(sql1)
      println(sql2)
  }
}
