package br.com.marineteapp.business;

import java.util.List;

import br.com.marineteapp.bean.Marinete;
import br.com.marineteapp.dao.MarineteDAO;
import br.com.marineteapp.provider.Secured;

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
