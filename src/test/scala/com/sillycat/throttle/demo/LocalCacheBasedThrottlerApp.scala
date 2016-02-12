package com.sillycat.throttle.demo

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, Props, ActorSystem, Actor}
import akka.routing.FromConfig
import com.sillycat.throttle.{Rate, MessageTick, LocalCacheBasedThrottler}
import com.typesafe.config.ConfigFactory
import org.joda.time.DateTime

/**
  * Created by carl on 2/12/16.
  */
object LocalCacheBasedThrottlerApp extends App {

  // A simple actor that prints whatever it receives
  class Printer extends Actor {
    def receive = {
      case x => {
        println(new DateTime().toString("yyyy-mm-dd HH:mm:ss") + " value = " + x)
      }
    }
  }

  val system =  ActorSystem("LocalCacheBasedThrottler", ConfigFactory.load())
  val router1: ActorRef = system.actorOf(Props[Printer].withRouter(FromConfig()), name = "Router1")

  // The throttler for this example, setting the rate
  val throttler = system.actorOf(Props(classOf[LocalCacheBasedThrottler], Rate(3, 15), router1))

  // These three messages will be sent to the printer immediately
  throttler ! MessageTick("1", "1")
  throttler ! MessageTick("1", "2")
  throttler ! MessageTick("1", "3")
  // These two will wait at least until 1 second has passed
  throttler ! MessageTick("1", "4")
  throttler ! MessageTick("1", "5")
  throttler ! MessageTick("1", "6")
  //
  throttler ! MessageTick("1", "7")
  throttler ! MessageTick("1", "8")
  throttler ! MessageTick("1", "9")
  throttler ! MessageTick("1", "10")
  throttler ! MessageTick("1", "11")
  throttler ! MessageTick("1", "12")

}
