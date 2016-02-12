package com.sillycat.throttle

import akka.actor.Actor

/**
  * Created by carl on 2/12/16.
  */
class LocalCacheBasedThrottler(var rate: Rate) extends Actor with IncludeLogger{

  def receive = {
    case msg: MessageTick => {
      val key = msg.key
      val real_msg = msg.msg

    }
    case _ => {
      logger.error("Received a message I don't understand.")
    }
  }

}
