package com.sillycat.throttle

import akka.actor.Actor

/**
  * Created by carl on 2/11/16.
  */
class RedisBasedThrottler(var rate: Rate) extends Actor with IncludeLogger{


//FUNCTION LIMIT_API_CALL(ip)
//  ts = CURRENT_UNIX_TIME()
//  keyname = ip+":"+ts
//  current = GET(keyname)
//  IF current != NULL AND current > 10 THEN
//    ERROR "too many requests per second"
//  ELSE
//    MULTI
//      INCR(keyname,1)
//      EXPIRE(keyname,10)
//    EXEC
//      PERFORM_API_CALL()
//END
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
