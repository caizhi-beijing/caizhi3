package com.caizhi.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caizhi.mail.service.MailService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/common/mail")
public class MailController {
	
	 @Autowired
	 private MailService mailService;
	 
	 /**
	  * 
	  * @param to 发送给谁
	  * @param subject  主题
	  * @param content  邮件内容
	  */
	 @RequestMapping("/sendSimpleMail")
	 @ApiOperation("发送普通邮件")
	 public void sendSimpleMail(String to, String subject, String content,String cc) {
		 to = "1005350256@qq.com";
		 mailService.sendSimpleMail(to, subject, content,cc);
	 }

	 /**
	  * 
	  * @param to 发送给谁
	  * @param subject  主题
	  * @param content  邮件内容
	  */
	 @RequestMapping("/sendHtmlMail")
	 @ApiOperation("发送html邮件")
	 public void sendHtmlMail(String to, String subject, String content,String cc) {
		 mailService.sendHtmlMail(to, subject, content,cc);
	 }

	 /**
	  * 
	  * @param to 发送给谁
	  * @param subject  主题
	  * @param content  邮件内容
	  * @param filePath 附件的地址
	  */
	 @RequestMapping("/sendAttachmentsMail")
	 @ApiOperation("发送带附件的邮件")
	 public void sendAttachmentsMail(String to, String subject, String content, String filePath,String cc) {
		 mailService.sendAttachmentsMail(to, subject, content, filePath,cc);
	 }
	 
	 /**
	  * 
	  * @param to 发送给谁
	  * @param subject  主题
	  * @param content  邮件内容
	  * @param rscPath
	  * @param rscId
	  */
	 @ApiOperation("发送带静态资源文件（图片）的邮件")
	 @RequestMapping("/sendInlineResourceMail")
	 public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId,String cc) {
		 mailService.sendInlineResourceMail(to, subject, content, rscPath, rscId,cc);
	 }
	
}
