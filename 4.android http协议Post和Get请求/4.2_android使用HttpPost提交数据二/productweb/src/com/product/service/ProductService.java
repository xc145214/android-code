package com.product.service;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public boolean addProduct(List<Object> params);
	
	public void releaseConn();

	public List<Map<String, Object>> listProduct();
	
	public boolean delProduct(List<Object> params);
	
	public Map<String,Object> viewProduct(List<Object> params);
}
