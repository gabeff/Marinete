package com.marinete.business;

import java.util.List;

import com.marinete.bean.Marinete;
import com.marinete.dao.MarineteDAO;

public class MarineteBusiness {
	
	private MarineteDAO mDao;
	
	public List<Marinete> listarMarinetes() {
		mDao = new MarineteDAO();
		List<Marinete> marinetes = mDao.listarMarinetes();
		if (marinetes.isEmpty()) {
			return null;
		}
		return marinetes;
	}

}
