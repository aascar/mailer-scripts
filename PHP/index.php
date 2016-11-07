<?php
/**
 * Created by IntelliJ IDEA.
 * User: Araja Jyothi Babu
 * Date: 28-Oct-16
 * Time: 7:01 PM
 */

<?php
require("PHPMailer/PHPMailerAutoload.php");
$mail = new PHPMailer(); // create a new object
$mail->IsSMTP(); // Use SMTP
$mail->Host        = "smtp.gmail.com"; // Sets SMTP server
$mail->SMTPDebug   = 0; // 2 to enable SMTP debug information
$mail->SMTPAuth    = TRUE; // enable SMTP authentication //Secure conection
$mail->Port        = 465; // set the SMTP port
$mail->SMTPSecure="ssl";
$mail->Username = 'USERMAIL'; // Eg: example@gmail.com
$mail->Password = 'PASSWORD'; //
$mail->Subject     = 'Test Email Using Gmail';
$mail->ContentType = 'text/html; charset=utf-8\r\n';
$mail->From        = 'FROM MAIL';
$mail->FromName    = 'FROM NAME';
$mail->WordWrap = 50; // set word wrap to 50 characters

$mail->AddAddress("FROM Email ADDRESS");
$mail->Subject = "SUBJECT";



//EMBED IT

$mail->AddEmbeddedImage("css.jpg",1000,"image.gif");

//Message Html Body
$mail->Body    = '<p style="font-size:150%;">Congratulations You have been successfully registered for  <b>Aalok2k16</b> National Cultural Fest.<br><br>Please Remember your FestId<br><br><br><img style="border:1px solid red"src="cid:1000">';


if(!$mail->Send()) {
     echo "Mailer Error: " . $mail->ErrorInfo;
 }
?>
