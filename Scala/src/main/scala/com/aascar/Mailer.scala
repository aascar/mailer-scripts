package com.aascar

import models.{Configuration, Mail}

/**
  * Created by jyothi on 7/2/17.
  */
class Mailer(configuration: Configuration) {

  implicit def stringToSeq(single: String): Seq[String] = Seq(single)
  implicit def liftToOption[T](t: T): Option[T] = Some(t)

  sealed abstract class MailType
  case object Plain extends MailType
  case object Rich extends MailType
  case object MultiPart extends MailType

  /**
    *
    * @param mail Mail type
    */
  def send(mail: Mail) {
    import org.apache.commons.mail._

    val format =
      if (mail.attachment.isDefined) MultiPart
      else if (mail.richMessage.isDefined) Rich
      else Plain

    /**
      *
      */
    val commonsMail: Email = format match {
      case Plain => new SimpleEmail().setMsg(mail.message)
      case Rich => new HtmlEmail().setHtmlMsg(mail.richMessage.get).setTextMsg(mail.message)
      case MultiPart => {
        val attachment = new EmailAttachment()
        attachment.setPath(mail.attachment.get.getAbsolutePath)
        attachment.setDisposition(EmailAttachment.ATTACHMENT)
        attachment.setName(mail.attachment.get.getName)
        new MultiPartEmail().attach(attachment).setMsg(mail.message)
      }
    }

    /**
      * Set SMTP configuration
      */
    commonsMail.setHostName(configuration.host) //smtp.gmail.com
    commonsMail.setSmtpPort(configuration.port) //587
    commonsMail.setAuthentication(configuration.userName, configuration.password) //"username@gmail.com", "password"
    commonsMail.setStartTLSEnabled(configuration.startTLS) //default true

    mail.to foreach (commonsMail.addTo(_))
    mail.cc foreach (commonsMail.addCc(_))
    mail.bcc foreach (commonsMail.addBcc(_))

    commonsMail.
      setFrom(mail.from._1, mail.from._2).
      setSubject(mail.subject).
      send()
  }

}
