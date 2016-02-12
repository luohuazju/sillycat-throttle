package com.sillycat.throttle

import org.joda.time.DateTime

/**
  * Created by carl on 2/12/16.
  */
trait IncludeDateTimeUtil {

  case class DateTimeCalculationException(msg:String)  extends Exception(msg)

  def convertCurrentTime2Key(dwellInSeconds:Int): String = {

    if(60 % dwellInSeconds != 0){
      throw new DateTimeCalculationException("I can not caculate if it is 60 % x == 0")
    }

    val current = new DateTime()
    val year = current.year().get()
    val month = current.monthOfYear().get()
    val day = current.dayOfMonth().get()
    val hour = current.hourOfDay().get()
    val minute = current.minuteOfHour().get()
    val seconds = current.secondOfMinute().get / dwellInSeconds

    val key = year + "_" + month + "_" + day + "_" + hour + "_" + minute + "_" + seconds

    key
  }

  def calculateDelay(remainCalls:Int, limit:Int, dwellInSeconds:Int):Int = {
    val current = new DateTime()
    val offset = current.secondOfMinute().get % dwellInSeconds

    val random = scala.util.Random.nextInt(dwellInSeconds)
    val count1 = dwellInSeconds * ((remainCalls - limit) / limit)
    val result = random + count1 - offset
    result
  }

}
