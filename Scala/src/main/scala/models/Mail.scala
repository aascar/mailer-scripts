package models

/**
  * Created by jyothi on 7/2/17.
  */
case class Mail(
                 from: (String, String), // (email -> name)
                 to: Seq[String],
                 cc: Seq[String] = Seq.empty,
                 bcc: Seq[String] = Seq.empty,
                 subject: String,
                 message: String,
                 richMessage: Option[String] = None,
                 attachment: Option[(java.io.File)] = None
               )
