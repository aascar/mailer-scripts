package models

/**
  * Created by jyothi on 8/2/17.
  */
case class Configuration(
                        host: String,
                        port: Int,
                        userName: String,
                        password: String,
                        startTLS: Boolean = true
                        )
