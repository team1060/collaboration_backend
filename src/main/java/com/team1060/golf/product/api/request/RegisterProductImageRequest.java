package com.team1060.golf.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class RegisterProductImageRequest {
	private String uuid;
	private Long product_no;
	private String type;
	private String path;
	
	public String getPath() {
        return String.format("/img/product/%d/%s", this.product_no, this.uuid);
    }
	
	
}
