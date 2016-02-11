package com.sillycat.throttle

import org.slf4j.LoggerFactory

/**
  * Created by carl on 2/11/16.
  */
trait IncludeLogger {

  val loggerName = this.getClass.getName

  protected val logger = LoggerFactory.getLogger(loggerName)

}
