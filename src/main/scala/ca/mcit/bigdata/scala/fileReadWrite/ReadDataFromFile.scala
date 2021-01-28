package ca.mcit.bigdata.scala.fileReadWrite

import ca.mcit.bigdata.scala.dataModel.{CalendarDate, Calender, Route, Stop, StopTime, Trips}
import scala.collection.mutable
import scala.io.{BufferedSource, Source}

object ReadDataFromFile {
  def getStopList(fileName: String): mutable.MutableList[Stop] = {
    val stopList = new scala.collection.mutable.MutableList[Stop]
    val (source, lineIterator) = getIteratorAndBufferedSource(fileName)
    while (lineIterator.hasNext) {
      stopList += List(lineIterator.next()).map(line => Stop(line)).head
    }
    source.close()
    stopList
  }

  def getCalendarDateList(fileName: String): List[CalendarDate] = {
    val (source, lineIterator) = getIteratorAndBufferedSource(fileName)
    val calendarDateList = lineIterator.map(line => CalendarDate(line)).toList
    source.close()
    calendarDateList
  }

  def getRouteList(fileName: String): List[Route] = {
    val (source, lineIterator) = getIteratorAndBufferedSource(fileName)
    val routeList = lineIterator.map(line => Route(line)).toList
    source.close()
    routeList
  }

  def getStopTimeList(fileName: String): List[StopTime] = {
    val (source, lineIterator) = getIteratorAndBufferedSource(fileName)
    val StopTimeList = lineIterator.map(line => StopTime(line)).toList
    source.close()
    StopTimeList
  }

  def getTripList(fileName: String): List[Trips] = {
    val (source, lineIterator) = getIteratorAndBufferedSource(fileName)
    val tripsList = lineIterator.map(line => Trips(line)).toList
    source.close()
    tripsList
  }

  def getCalenderList(fileName: String): List[Calender] = {
    val (source, lineIterator) = getIteratorAndBufferedSource(fileName)
    val calenderList = lineIterator.map(line => Calender(line)).toList
    source.close()
    calenderList
  }

  def getIteratorAndBufferedSource(fileName: String): (BufferedSource, Iterator[String]) = {
    val source: BufferedSource = Source.fromFile(fileName)
    val lineIterator: Iterator[String] = source.getLines().drop(1) // skip header by drop(1)
    (source, lineIterator)
  }
}
