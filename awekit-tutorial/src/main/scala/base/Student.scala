package base

/**
 * <p>Title: Student</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2022</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2022/9/12 19:37</p>
 */
class Student(name: String, age: Int) {
  def printInfo(): Unit = {
    println(this.name + " " + age + " " + Student.school)
  }
}

// 引入伴生对象
object Student {
  val school: String = "xmu"

  def main(args: Array[String]): Unit = {
    val cza = new Student("cza", 33)
    val lyf = new Student("lyf", 33)
    cza.printInfo()
    lyf.printInfo()
  }
}
