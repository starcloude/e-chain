package com.echain.web.beans;

import lombok.Data;

@Data
public class AdminWalletVo {
	
	//收币钱包-ERC20
	private String erc20;
	
	//收币钱包-TRC20
	private String trc20;
	
	//收币钱包-OMNI
	private String omni;
		
}