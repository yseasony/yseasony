package org.yseasony.acg.utils.ext;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExtUtil {

	@Autowired
	private DozerBeanMapper dozer;

	public <T, K> List<K> checkboxConvert(List<T> list, Class<K> c) {
		List<K> d = new ArrayList<K>();
		for (T t : list) {
			d.add(dozer.map(t, c));
		}
		return d;
	}

}
