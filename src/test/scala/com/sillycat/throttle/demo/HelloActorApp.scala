package com.sillycat.throttle.demo

import akka.actor.{Actor, ActorSystem, Props}

/**
  * Created by carl on 2/11/16.
  */
class HelloActor extends Actor{

  def receive = {
    case "hello" => {
      println("hello!")
    }
    case _ => {
      println("huh?")
    }
  }

}

object HelloApp extends App {
  val system =  ActorSystem("HelloSystem")
  system.actorOf(Props[HelloActor], name="test1")
  system.actorOf(Props[HelloActor], name="test2")
  system.actorOf(Props[HelloActor], name="test3")

//  implicit val resolveTimeout = Timeout(5 seconds)
//  system.actorSelection("/user/test*").resolveOne().map { helloActor=>
//    println(helloActor)
//    helloActor ! "hello"
//    helloActor ! "bye"
//  }

  val helloActor = system.actorSelection("/user/test1")
      helloActor ! "hello"
      helloActor ! "bye"

  system.awaitTermination()
}
