package com.team1060.golf.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterProductImageRequest {
	private String uuid;
	private Long product_no;
	private String type;
	
	public String getPath() {
        return String.format("/img/product/%d/%s", this.product_no, this.uuid);
    }
	
	
}
