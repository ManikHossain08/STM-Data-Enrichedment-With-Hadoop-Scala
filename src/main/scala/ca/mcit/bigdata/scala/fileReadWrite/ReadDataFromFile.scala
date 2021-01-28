package ca.mcit.bigdata.scala.fileReadWrite

import ca.mcit.bigdata.scala.dataModel.{CalendarDate, Calender, Route, Stop, StopTime, Trips}
import scala.collection.mutable
import scala.io.{BufferedSource, Source}

object ReadDataFromFile {
  def getStopList(fileName: String): mutable.MutableList[Stop] = {
    val stopList = new scala.collection.mutable.MutableList[Stop]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      stopList += List(lineIterator.next()).map(line => Stop(line)).head
    }
    source.close()
    stopList
  }

  def getCalendarDateList(fileName: String): mutable.MutableList[CalendarDate] = {
    val calendarDateList = new scala.collection.mutable.MutableList[CalendarDate]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      calendarDateList += List(lineIterator.next()).map(line => CalendarDate(line)).head
    }
    source.close()
    calendarDateList
  }

  def getRouteList(fileName: String): mutable.MutableList[Route] = {
    val routeList = new scala.collection.mutable.MutableList[Route]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      routeList += List(lineIterator.next()).map(line => Route(line)).head
    }
    source.close()
    routeList
  }

  def getStopTimeList(fileName: String): mutable.MutableList[StopTime] = {
    val StopTimeList = new scala.collection.mutable.MutableList[StopTime]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      StopTimeList += List(lineIterator.next()).map(line => StopTime(line)).head
    }
    source.close()
    StopTimeList
  }

  def getTripList(fileName: String): mutable.MutableList[Trips] = {
    val tripsList = new scala.collection.mutable.MutableList[Trips]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      tripsList += List(lineIterator.next()).map(line => Trips(line)).head
    }
    source.close()
    tripsList
  }

  def getCalenderList(fileName: String): mutable.MutableList[Calender] = {
    val calenderList = new scala.collection.mutable.MutableList[Calender]
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines()
    lineIterator.next() // skip header
    while (lineIterator.hasNext) {
      calenderList += List(lineIterator.next()).map(line => Calender(line)).head
    }
    source.close()
    calenderList
  }
}
