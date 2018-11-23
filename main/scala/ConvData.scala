import java.io.{FileInputStream, FileOutputStream}
import java.nio.file.Paths
import java.util.Optional
import java.util.concurrent.TimeUnit

import org.dom4j.Element
import org.dom4j.io.SAXReader

import scala.io._

val isLazy = true
val needReWrite = false
printf("Config is: \nisLazy -> %s, if true, it means the app will use export.xml file in the root path. else, your need prove the xml file path.\n" +
  "needReWrite -> %s, if true, it means if the output file exists, the app will cover it, else the app will append stream to the exist file\n", isLazy, needReWrite)

println("\nThe app will run in 3 secs later..\n")
TimeUnit.SECONDS.sleep(3)

val fileName = if (!isLazy) StdIn.readLine("Input your Apple Health Data xml File Path: >> \n") else "export.xml"

println("Reading from file " + fileName)
val document = new SAXReader().read(getClass.getClassLoader.getResourceAsStream(fileName))

println("Parse with file " + fileName)
val root = document.getRootElement
val total = root.elements().size()
var current = 0
var lastProgress = 0.0

println("Iter with file " + fileName)
var currentFile: (FileOutputStream, String) = (null, "")

root.elements().stream().forEach(record => {
    val element = record.asInstanceOf[Element]
    if (element.getName.equalsIgnoreCase("record")) {
      val key = Some(element.attributeValue("type")).get
      if (currentFile._2 != key) {
        Optional.ofNullable(currentFile._1).ifPresent(os => {
          os.flush(); os.close()
        })
        currentFile = (getFile(key), key)
      }
      currentFile._1.write(covData(element).getBytes)
      currentFile._1.flush()
      current += 1
      printProcess()
    }}
)
currentFile._1.close()
print("Done!")

def covData(element: Element) = {
  val type_ = element.attributeValue("type")
  val unit = element.attributeValue("unit")
  val value = element.attributeValue("value")
  val sourceName = element.attributeValue("sourceName")
  val sourceVersion = element.attributeValue("sourceVersion")
  //val device = element.attributeValue("device")
  val device = "Apple Product"
  val creationDate = element.attributeValue("creationDate")
  val startDate = element.attributeValue("startDate")
  val endDate = element.attributeValue("endDate")
  String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
    type_, unit, value, sourceName, sourceVersion, device, creationDate, startDate, endDate)
}

def getFile(fName:String) = {
  val fileName = fName.replace("HKQuantityTypeIdentifier","") + ".csv"
  if (Paths.get(fileName).toFile.exists() && needReWrite) {
    println("File exist, reading and reWriting")
    val exists = new FileInputStream(fileName)
    val newOut = new FileOutputStream(fileName)
    newOut.write(exists.read()); newOut.flush()
    newOut
  } else {
    println("Creating new File: "+ fileName +" now...")
    new FileOutputStream(fileName)
  }
}

def printProcess(): Unit = {
  val currentProgress = current.toDouble/total.toDouble
  if (currentProgress - lastProgress > 0.1) {
    printf("Progress: %.4s\n",currentProgress)
    lastProgress = currentProgress
  }
}