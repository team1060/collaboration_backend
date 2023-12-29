package com.team1060.golf.infra.crawl;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import lombok.Getter;

public class ProductDetailPageDownloadImage {
	public static void main(String[] args) throws IOException {
		ProductImageJsonData imageData = new ProductImageJsonData();

			List<ProductImage> productImages = imageData.getProductImages();
			
			for (ProductImage productImage : productImages) {
				downloadImage(productImage.getImageUrl(), "product/" + productImage.getProduct_no(), productImage.getImageName());
			}

	}
	
	private static void downloadImage(String imageUrl, String directory, String fileName) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(imageUrl);
			Path path = Path.of(directory, fileName);
			
			// 디렉토리가 존재하지 않으면 생성
            Files.createDirectories(Path.of(directory));
            
            // 이미지 다운로드
            Files.copy(url.openStream(), path);
            System.out.println("Downloading... : " + path.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Getter
	protected static class ProductImage {
		private int product_no;
		private String category;
		private String imageUrl;

		public String getImageName() {
			return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
		}
	}
}
