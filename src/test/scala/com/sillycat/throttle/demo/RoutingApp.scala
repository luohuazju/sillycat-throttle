package com.sillycat.throttle.demo

import akka.actor._
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object RoutingApp extends App{

  // A simple actor that prints whatever it receives
  class Printer extends Actor {
    def receive = {
      case x => println(self.path + " saying " + x)
    }
  }

  class Shooter extends Actor {
    def receive = {
      case x => println(self.path + " shouting " + x)
    }
  }

  val system =  ActorSystem("RoutingSystem", ConfigFactory.load())
  val router1: ActorRef = system.actorOf(Props[Printer].withRouter(FromConfig()), name = "Router1")
  // These three messages will be sent to the printer immediately
  router1 ! "11"
  router1 ! "12"
  router1 ! "13"
  // These two will wait at least until 1 second has passed
  router1 ! "14"
  router1 ! "15"
  println(" Router 1 " + router1.path)

  val router2: ActorRef = system.actorOf(Props[Shooter].withRouter(FromConfig()), name = "Router2")
  router2 ! "21"
  router2 ! "22"

  val router3: ActorSelection = system.actorSelection("/user/Router2")
  router3 ! "23"

  println(" Router 2 " + router2.path)

  system.shutdown()

}
