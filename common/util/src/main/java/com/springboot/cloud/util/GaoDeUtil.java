/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：GaoDeUtil.java
 * 创建人：刘兆明
 * 联系方式：guiji
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.springboot.cloud.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * The class Gao de util.
 *
 * @author guiji
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GaoDeUtil {

	/**
	 * Gets city by ip addr.
	 *
	 * @param ipAddr the ip addr
	 *
	 * @return the city by ip addr
	 */
	public static String getCityByIpAddr(String ipAddr,boolean isProxy) {
		// http://lbs.amap.com/api/webservice/guide/api/ipconfig/
		log.info("getCityByIpAddr - 根据IP定位. ipAddr={}", ipAddr);
		String temp = "127.0.";
		String temp2 = "192.168.";
		if (ipAddr.startsWith(temp) || ipAddr.startsWith(temp2)) {
			ipAddr = "116.228.143.58";
		}
		GaodeLocation location = null;
		String urlAddressIp = "http://restapi.amap.com/v3/ip?key=f8bdce6f882a98635bb0b7b897331327&ip=%s";
		String url = String.format(urlAddressIp, ipAddr);
		try {
			String str = HttpUtil.httpGet(url,isProxy);
			location = new ObjectMapper().readValue(str, GaodeLocation.class);
			return location.getProvince()+"-"+location.getCity();
		} catch (Exception e) {
			log.error("getCityByIpAddr={}", e.getMessage(), e);
		}
		log.info("getCityByIpAddr - 根据IP定位. ipAddr={}, location={}", ipAddr, location);
		return "";
	}

	public static void main(String[] args) {
		getCityByIpAddr("116.228.143.58",true);
	}
}
