package ca.mcit.bigdata.scala.dataModel

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

case class Route(routeId: Int,
                 longRouteName: String,
                 routeColor: String)

object Route {
  def apply(fileName: String): List[Route] = {
    val schemaObjList = ObjectCollection.getDataFromSource(fileName, convertStringDataToRouteObject)
    schemaObjList
  }

  def convertStringDataToRouteObject(line: String): Route = {
    val fields: Array[String] = line.split(",", -1)
    Route(fields(0).toInt, fields(3), fields(6))
  }
}

