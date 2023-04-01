package com.payment.response;

import com.payment.request.MerchantRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantResponse {
	
	private long id;
	private String name;
	private boolean isActive;

}
