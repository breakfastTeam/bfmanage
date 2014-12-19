package com.bean.breakfast.basic.dto;

import com.bean.breakfast.basic.model.TBfProvider;
import com.bean.breakfast.basic.model.TBfRawMaterial;
import com.bean.breakfast.basic.model.TBfUser;
import com.bean.breakfast.basic.model.TBfUserCourier;

import java.util.List;

/**
 * TBfFood entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class RawMaterialDTO implements java.io.Serializable {
	private TBfRawMaterial rawMaterial;
	private TBfProvider provider;

	public TBfRawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(TBfRawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public TBfProvider getProvider() {
		return provider;
	}

	public void setProvider(TBfProvider provider) {
		this.provider = provider;
	}
}