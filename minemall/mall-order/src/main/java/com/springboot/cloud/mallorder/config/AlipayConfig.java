package com.springboot.cloud.mallorder.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101900720429";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCBzbb8vQiQkRfvYyTCt4/cGVsqoW7ha4b2e7ZVraYJRMoRZElvpOrMNEh7MEWeruD407ye168oeHTMXz0iAVKCjE37HOkF07DvRhpmEmAQ4O2bzz8AowdH0NaPaPu1MZTek2YfntrPjFVFSQspv/NKtrTwkpMEPdxVwkNrMvois8rJGrOcOKB6r84IXVJ7mUD9hpNCbkgOBts+Vn+E6GII6xCJuTYZjEzQ/uB4Y8LAUpYB7L38+XUzZfrtUl06mTBU5TqG/jbMGXO/7P3PBkJ69jgNs9MmlHJOXEVHE1beqaTsoASb+PBoOVL097pDFzpdmegOtvEpts189RNA5kThAgMBAAECggEACsVFD8entEc10zlIzZdpqnu4IttuVeoUMtOlF4rnIhHBKpxFs6vdh8A223Hv9PDR3hPDrPJ8qEEGgAbPDclKTWbS+ak2dLNSFSid/CY+LmhbjvgtZCIi8oSTqSau7CQHFEjqymjmAx7D3JHpCTfSOjra3LNs24c+hyctbPG367Ucz90JouDSfSe9n/qSZcPtEsss0nvoKJH9tKTAVXY5ac79UzNKHfwwx0CU9+G6mDNqz+bdeW7I3jzAA6PO7GrZVJBZCVpz75ZpQVLuAOOkNpWAXB8qvCGO3sMLMU1g3CR0iDNtYwQWZ45irSJN83OdfoZ8RZxRYyHHg2m25OpW+QKBgQDWvNNDmzgq402hoSp13VBMqQBPIC7ToU97o9UtBrIpBD2ATuHVlo1vKynKvgxkwuEQHCxL9SzWW5kFe6l5wgXIuMHxXVY3l5SQY7jkiF2SekK80Gbqfe9+CtYpwdIVJxMoqG2tMAKkr2wNhuB3gX0HtaVdrMC/zGszELAMHRH2FwKBgQCavuSZ59dwUeNoZC0x54UsIOrWvrHRzR8FqIMoxgYR2XFw3kK+IhkxR/4f9/pqnBbmn7e3+LkFzCTKfgCEjg2gUTUxi2kK4M0oSsbR0q4YsH8uth674BYT+l8hb5Kw1uDtmEbKvMT8vrI9EreNDcq8ZOt243zImU8+qZlftSRvxwKBgQDCmK9i7sDBrObm9+RAl2RzdBuBmQqWYfJjex5uBUlifiM1qUZihmJ5QcZHol4c2bWXIdDKrM4LG0raeywj2L3hHf0zO4AsNMM+7f/ZqfISEZV1Ae6hxapIsI1PngNBg/2KtMdfUMi0cPwcbgPwiHnDiMrozuqFZTwoNJE1CQsD1QKBgEH7zexWcIXjVQ2JUcAq0Z1Md/aTn60vQuAdx7eWILZxuq5/B4Sdc5KQ+SpWif/1iMpUJnma5AI11yZE7iGXlPXcSstSf7r0umZI5FhJ9pRuhfy8CYTg+2kPZIsUUT94kLafLOh77586DTR/i6boz57fhYvPWsEKpkC4/r5RtjS3AoGBAIhMg1iHozxlXIx8WkbgThiL6qYKmxoOtrwTT1DWpJ22BX4AxvQtqcTFVzQi/G9WlezxP9qRcAq2OiflnxXclKVDS2obomYl3FfF+3H+nSPweL5Iz7EZdBl7pdfQtfeI1TfpKb1niRRz6iLjSRYQsmVSrpxRvttugS1gIYAZu1tP";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj9x42RPpr7j8uXsx4rH7ZNCVzC+R316v8/kckfPvA3WrjmMhqMojW9CpbClNCmaPNWr5OcLTJdaizhnVkdwxUQ3r+kWjtHYdN7IHHJE3p46n6JQjbGWkyPTigscA+u7IG8Zwra33nI6ocU3GsnH6IksENDAh/cKQfCKBKA/T0jpfVHMWP3Z2YQXzTYPpzXItXOQXoX2IbZm6F2mlupmuqHlLtCBz7UAnXz8cGwBXGw9v4F2FYyDhvH5oVFERqQG/DSWFLtN01AQ3MWcSJclVmdyycSKFQkx0l3SQxb5FyPClDzfprZgYZWr9Gw3kp828Jaw6gOXW6ruxO9UasxMT3wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1/mall-order/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1/mall-order/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "E:\\Alipay";


    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

