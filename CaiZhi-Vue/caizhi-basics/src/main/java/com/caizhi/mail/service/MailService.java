package com.caizhi.mail.service;

public interface MailService {

	public void sendSimpleMail(String to, String subject, String content,String cc);//简单邮件

    public void sendHtmlMail(String to, String subject, String content,String cc);//html邮件

    public void sendAttachmentsMail(String to, String subject, String content, String filePath,String cc);//带附件邮件

    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId,String cc);//带静态资源文件（图片）的邮件

}
