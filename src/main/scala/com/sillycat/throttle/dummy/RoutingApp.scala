package com.sillycat.throttle.dummy

import akka.actor.{ActorSystem, Props, ActorRef, Actor}
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object RoutingApp extends App{

  // A simple actor that prints whatever it receives
  class Printer extends Actor {
    def receive = {
      case x => println(self.path + " saying" + x)
    }
  }

  val system =  ActorSystem("RoutingSystem", ConfigFactory.load())
  val router1: ActorRef = system.actorOf(Props[Printer].withRouter(FromConfig()), name = "Router1")
  // These three messages will be sent to the printer immediately
  router1 ! "1"
  router1 ! "2"
  router1 ! "3"
  // These two will wait at least until 1 second has passed
  router1 ! "4"
  router1 ! "5"

  system.shutdown()

}
