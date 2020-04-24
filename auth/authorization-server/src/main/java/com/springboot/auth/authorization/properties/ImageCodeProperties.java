package com.springboot.auth.authorization.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片验证码配置项
 *
 * @author guiji
 * https://github.com/Young-Guiji/MineCloud.git
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends SmsCodeProperties {

	/**
	 * Instantiates a new Image code properties.
	 */
	ImageCodeProperties() {
		super.setLength(4);
	}

}
