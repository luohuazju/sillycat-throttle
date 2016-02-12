package com.sillycat.throttle.demo

import akka.actor.{Actor, ActorSystem, Props}
import akka.contrib.throttle.Throttler.{SetTarget, _}
import akka.contrib.throttle.TimerBasedThrottler

import scala.concurrent.duration._

/**
  * Created by carl on 2/11/16.
  */
object TimerBasedThrottlerApp extends App{

  // A simple actor that prints whatever it receives
  class Printer extends Actor {
    def receive = {
      case x => println(x)
    }
  }

  val system =  ActorSystem("HelloSystem")

  val printer = system.actorOf(Props[Printer], "printer")

  // The throttler for this example, setting the rate
  val throttler = system.actorOf(Props(classOf[TimerBasedThrottler], 3 msgsPer 3.second))

  // Set the target
  throttler ! SetTarget(Some(printer))

  // These three messages will be sent to the printer immediately
  throttler ! "1"
  throttler ! "2"
  throttler ! "3"
  // These two will wait at least until 1 second has passed
  throttler ! "4"
  throttler ! "5"

}
