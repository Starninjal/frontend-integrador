package com.wmw.frontend.integrador.components;



import totalcross.ui.ComboBox;

public class ComboBoxTipoPessoa extends ComboBox {

	public static Object[] itemTipoPessoa = new Object[2];

	static {
		itemTipoPessoa[0] = "FISICA";
		itemTipoPessoa[1] = "JURIDICA";
	}
	
	

	public ComboBoxTipoPessoa() {
		super(itemTipoPessoa);
	}

}
