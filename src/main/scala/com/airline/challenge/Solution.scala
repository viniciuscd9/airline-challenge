package com.airline.challenge

import com.airline.challenge.domain.Flights
import com.airline.challenge.domain.Flights.FlightNode

import scala.collection.mutable

object Solution {
  def apply(): Solution = new Solution
}

class Solution {

  def getShortestPath(from: String, to: String): Seq[FlightNode] = {
    //using djikstra algorithm to find the shortest path
    var flights = Flights()
    implicit val ordering: Ordering[FlightNode] = (node1, node2) => node2.totalDuration.compareTo(node1.totalDuration)
    val queue = mutable.PriorityQueue(FlightNode(from = from, to = from, 0, totalDuration = 0))(ordering)
    val visited = mutable.Set.empty[(String, String)]

    while (queue.nonEmpty) {
      val currentNode = queue.dequeue()
      val flightNodes = flights.getFlightNodes(currentNode.to)

      for (flightNode <- flightNodes) {
        if (!visited.contains((flightNode.from, flightNode.to))) {
          val evaluatedNode: FlightNode = evaluate(currentNode, nextNode = flightNode)
          flights = flights.add(evaluatedNode)
          queue.enqueue(evaluatedNode)
        }
      }

      visited += ((currentNode.from, currentNode.to))

      if (to.equals(currentNode.to)) {
        return currentNode.path :+ currentNode
      }
    }

    Seq.empty
  }

  private def evaluate(currentNode: FlightNode, nextNode: FlightNode): FlightNode = {
    val newDuration: Int = currentNode.totalDuration + nextNode.duration

    if (newDuration < nextNode.totalDuration) {
      nextNode.newTotalDuration(newDuration).newPath(currentNode.path :+ currentNode)
    } else {
      nextNode
    }

  }

}
