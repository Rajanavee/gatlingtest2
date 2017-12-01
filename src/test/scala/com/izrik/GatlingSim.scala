package com.izrik // 1

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class GatlingSim extends Simulation { // 3

  val conf = ConfigFactory.load()

  val BASE_URL: String = conf.getString("base_url")
  val USERS_PER_SEC : Double =  conf.getDouble("users_per_sec")
  val MAX_DURATION_SECONDS: Int = conf.getInt("max_duration_seconds")

  val httpConf = http // 4
    .baseURL(BASE_URL) // 5
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn: ScenarioBuilder = scenario("BasicSimulation") // 7
    .exec(http("request_1").
        get("/")) // 9


  setUp( // 11
//    scn.inject(atOnceUsers(1)) // 12
    scn.inject(constantUsersPerSec(USERS_PER_SEC) during(MAX_DURATION_SECONDS seconds))
  ).protocols(httpConf) // 13
}
