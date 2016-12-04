package com.parknshop.service.baseImpl;

/**
 * Created by weina on 2016/12/1.
 */
public interface IHtmlContext {
    String HTMLEMAIL = "<!DOCTYPE html>\n" +
            "<html>\n"+
            "<head>\n"+
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"+
            "<title>Demystifying Email Design</title>\n"+
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"+
            "\t<style>\n"+
            "\t\t#activate{\n"+
            "\t\t\tfont-family: 微软雅黑;\n"+
            "\t\t\tfont-size: 24px;\n"+
            "\t\t\ttext-decoration: solid;\n"+
            "\t\t}\n"+
            "\t</style>\n"+
            "</head>\n"+
            "<body style=\"margin: 0; padding: 0;\">\n"+
            "\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\t\n"+
            "\t\t<tr>\n"+
            "\t\t\t<td style=\"padding: 10px 0 30px 0;\">\n"+
            "\t\t\t\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\">\n"+
            "\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t<td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n"+
            "\t\t\t\t\t\t\t<img src=\"http://miss-rabbit.cc/h1.gif\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\" />\n"+
            "\t\t\t\t\t\t</td>\n"+
            "\t\t\t\t\t</tr>\n"+
            "\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n"+
            "\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"+
            "\t\t\t\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t\t\t\t<td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n"+
            "\t\t\t\t\t\t\t\t\t\t<b>Hello! Welcome To PARKnSHOP!</b>\n"+
            "\t\t\t\t\t\t\t\t\t</td>\n"+
            "\t\t\t\t\t\t\t\t</tr>\n"+
            "\t\t\t\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t\t\t\t<td style=\"padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n"+
            "\t\t\t\t\t\t\t\t\t\tWelcome to register PARKnSHOP's account. <br> This is an activate email.Please click the link below to activate your account.\n"+
            "\t\t\t\t\t\t\t\t\t</td>\n"+
            "\t\t\t\t\t\t\t\t</tr>\n"+
            "\t\t\t\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t\t\t\t<td style=\"padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n"+
            "\t\t\t\t\t\t\t\t\t\t<a href=\"";
    String LINK_HTMLEMAIL ="\" id=\"activate\">Now!Please click this link to activate your account:";
    String HTMLEMAIL_NAME ="</a>\n" +
            "\t\t\t\t\t\t\t\t\t</td>\n" +
            "\t\t\t\t\t\t\t\t</tr>\n"+
            "\t\t\t\t\t\t\t</table>\n"+
            "\t\t\t\t\t\t</td>\n"+
            "\t\t\t\t\t</tr>\n"+
            "\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t<td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\n"+
            "\t\t\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"+
            "\t\t\t\t\t\t\t\t<tr>\n"+
            "\t\t\t\t\t\t\t\t\t<td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;left: 33%;position: relative \" width=\"75%\">\n"+
            "\t\t\t\t\t\t\t\t\t\t&reg; XDU-PM9 PARKnSHOP 2016<br/>\n"+
            "\t\t\t\t\t\t\t\t\t</td>\n"+
            "\t\t\t\t\t\t\t\t</tr>\n"+
            "\t\t\t\t\t\t\t</table>\n"+
            "\t\t\t\t\t\t</td>\n"+
            "\t\t\t\t\t</tr>\n"+
            "\t\t\t\t</table>\n"+
            "\t\t\t</td>";
}
