package com.sillycat.throttle

import scala.concurrent.duration.FiniteDuration

/**
  * Created by carl on 2/11/16.
  */
case class Rate(val numberOfCalls: Int, val duration: FiniteDuration) {
  def durationInMillis(): Long = duration.toMillis
}
