package com.nc.conversion;

import org.springframework.core.convert.converter.Converter;

import com.nc.entity.Admin;

/**
 * 自定义转换器
 * @author super_ni
 *
 */
public class DataConversion implements Converter<String, Admin>{

	@Override
	public Admin convert(String arg0) {
		System.out.println(arg0);
		String[] ads = arg0.split("-");
		if (ads.length ==4) {
			return new Admin(Integer.parseInt(ads[0]),ads[1],ads[2],ads[3]);
		}
		return null;
	}

}
