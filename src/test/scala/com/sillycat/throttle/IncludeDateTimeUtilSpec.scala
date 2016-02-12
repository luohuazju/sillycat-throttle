package com.sillycat.throttle

import org.scalatest.{BeforeAndAfter, Matchers, FunSpec}

/**
  * Created by carl on 2/12/16.
  */
class IncludeDateTimeUtilSpec extends FunSpec with Matchers with BeforeAndAfter with IncludeLogger{

  class ClassDateTimeUtil extends IncludeDateTimeUtil
  var classDateTime : ClassDateTimeUtil = _

  before {
    classDateTime = new ClassDateTimeUtil
  }

  describe("IncludeDateTime"){
    describe("#convertCurrentTime2Key"){
      it("Convert the current time to key string"){
        val result = classDateTime.convertCurrentTime2Key(3)
        logger.trace("current time key for dwell 3 = " + result)
        result should not be empty
      }
    }

    describe("#calculateDelay"){
      it("Calculate the delay"){
        val result1 = classDateTime.calculateDelay(3, 3, 15)
        logger.debug("result 1 = " + result1)
        result1 should not be 0

        val result2 = classDateTime.calculateDelay(6, 3, 15)
        logger.debug("result 2 = " + result2)
        result2 should not be 0

        val result3 = classDateTime.calculateDelay(2, 3, 15)
        logger.debug("result 3 = " + result3)
        result3 should not be 0

        val result4 = classDateTime.calculateDelay(7, 3, 15)
        logger.debug("result 4 = " + result4)
        result4 should not be 0
      }
    }
  }

}
