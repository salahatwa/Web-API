package org.currency.starter.dto;

import org.currency.starter.exception.GeneralException;

public class ObjectUtil {

	public static <T> void isNull(T t) throws GeneralException {
		if (t == null)
			throw new GeneralException(t.getClass() + " Is Empty");
	}
}
