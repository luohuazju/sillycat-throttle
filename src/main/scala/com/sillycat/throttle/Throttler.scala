package com.sillycat.throttle

import java.util.concurrent.TimeUnit

import scala.concurrent.duration.Duration

/**
  * Created by carl on 2/11/16.
  */
object Throttler {

  implicit class RateInt(val numberOfCalls: Int) extends AnyVal {
    def msgsPerSeconds(duration: Int) = Rate(numberOfCalls, Duration(duration, TimeUnit.SECONDS))
  }
}
