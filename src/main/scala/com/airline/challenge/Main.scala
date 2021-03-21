package com.airline.challenge

import com.airline.challenge.domain.Flights.FlightNode

object Main extends App{

  if (args.length != 2) {
    System.err.println("Incorrect argument order.\n\n Correct usage:\n" +
      " >airline-challenge $FROM_AIRPORT $TO_AIRPORT\n")
    System.exit(1)
  }

  val from: String = args(0)
  val to: String = args(1)
  val solution = Solution()
  val path = solution.getShortestPath(from, to)

  if (path.isEmpty) {
    println(s"There is route available between $from and $to.")
  }

  var total = 0

  for (node <- path) {
    if (!node.from.equals(node.to)) {
      println(s"${node.from} -- ${node.to} (${node.duration})")
      total += node.duration
    }
  }

  println(s"time: $total")
}
