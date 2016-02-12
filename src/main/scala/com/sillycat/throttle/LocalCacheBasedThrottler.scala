package com.sillycat.throttle

import java.util.concurrent.TimeUnit

import akka.actor.{ActorRef, Actor}
import com.google.common.cache.CacheBuilder
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by carl on 2/12/16.
  */
class LocalCacheBasedThrottler(var rate: Rate,var target: ActorRef) extends Actor with IncludeLogger with IncludeDateTimeUtil{

  val throttleKey = "THROTTLE_"

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
      val msgKey = msg.key
      val realMsg = msg.msg
      val limitCalls = rate.numberOfCalls
      val timeWindows = rate.duration

      val timeKey = convertCurrentTime2Key(timeWindows)
      val key = throttleKey + timeKey + msgKey

      LocalCache.throttleBucket.getIfPresent(key) match {
        case count:java.lang.Integer if count >= limitCalls => {
          //delay random and tick self
          LocalCache.throttleBucket.put(key, count + 1)
          val delay = calculateDelay(count + 1, limitCalls, timeWindows)
          //tick to self within the delay
          context.system.scheduler.scheduleOnce(delay second, self, msg)
        }
        case count:java.lang.Integer => {
          //count + 1
          LocalCache.throttleBucket.put(key, count + 1)
          //pass the ticket
          target ! realMsg
        }
        case _ => {
          //init the count
          LocalCache.throttleBucket.put(key, new Integer(1))
          //pass the ticket
          target ! realMsg
        }
      }

    }
    case _ => {
      logger.error("Received a message I don't understand.")
    }
  }


}
