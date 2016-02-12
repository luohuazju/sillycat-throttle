package com.sillycat.throttle

import java.util.concurrent.TimeUnit

import com.google.common.cache.CacheBuilder

/**
  * Created by carl on 2/12/16.
  */
object LocalCache {

  val builder = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS)

  val throttleBucket = builder.build[java.lang.String, java.lang.Integer]()

}
