package com.airline.challenge.domain

import com.airline.challenge.domain.Flights.{Arrival, FlightNode}

import scala.collection.immutable.HashMap

object Flights {
  case class Arrival(airport: String, duration: Int)
  case class FlightNode(from: String,
                        to: String,
                        duration: Int,
                        path: Seq[FlightNode] = Seq.empty,
                        totalDuration: Int = Int.MaxValue) {
    def newTotalDuration(totalDuration: Int): FlightNode = FlightNode(from, to, duration, path, totalDuration)
    def newPath(path: Seq[FlightNode]): FlightNode = FlightNode(from, to, duration, path, totalDuration)
  }

  implicit val graph: Map[String, Set[Arrival]] = Map (
    "DUB" -> Set(Arrival("LHR", 1), Arrival("CDG", 2), Arrival("ORD", 6)),
    "CDG" -> Set(Arrival("BOS", 6), Arrival("BKK", 9)),
    "ORD" -> Set(Arrival("LAS", 2)),
    "LHR" -> Set(Arrival("NYC", 5), Arrival("BKK", 9)),
    "NYC" -> Set(Arrival("LAS", 3)),
    "BOS" -> Set(Arrival("LAX", 4)),
    "BKK" -> Set(Arrival("SYD", 11)),
    "LAX" -> Set(Arrival("LAS", 2), Arrival("SYD", 13)),
    "LAS" -> Set(Arrival("SYD", 14))
  )

  def apply(): Flights = {
    new Flights(HashMap.empty)
  }

  def apply(flightNodes: Map[(String, String), FlightNode]): Flights = {
    new Flights(flightNodes)
  }
}

class Flights(private val flightNodes: Map[(String, String), FlightNode])(implicit private val graph: Map[String, Set[Arrival]]) {
  private def getArrivals(departure: String): Set[Arrival] = graph.getOrElse(departure, Set.empty)

  def getFlightNodes(to: String): Set[FlightNode] = getArrivals(to).map {
    arrival => flightNodes.getOrElse((to, arrival.airport), FlightNode(to, arrival.airport, arrival.duration, Seq.empty))
  }

  def getFlightNode(from: String, to: String): FlightNode = flightNodes.getOrElse((from, to), null)

  def add(nodes: Set[FlightNode]): Flights = Flights {
    flightNodes ++ nodes.map(n => (n.from, n.to) -> n)
  }

  def add(node: FlightNode): Flights = Flights(flightNodes + ((node.from, node.to) -> node))

}
