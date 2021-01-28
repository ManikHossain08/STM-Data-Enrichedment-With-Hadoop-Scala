package ca.mcit.bigdata.client.scala

import ca.mcit.bigdata.file.writing.scala.{DataWriteToCSVFile, IOFileLinks}

/**
 * @author Manik Hossain
 * @version 1.0.0
 * @since 23-01-2021
 */

object AppClient extends App {
  /**
   * Note: I Take only 100 records for printing onto the console because it will not show all the data onto the console.
   * However, it converted all the record to JVM object really to list.
   */

  val enrichedTrips = new DataWriteToCSVFile()
  enrichedTrips.writeFile(IOFileLinks.enrichedTripOutput)

}
