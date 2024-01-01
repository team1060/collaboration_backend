package com.team1060.golf.infra.crawl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailPagePrdtImageCrawling  {
    public static void main(String[] args) {
    	// WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        
        // 크롤링 대상 URL 리스트
		List<String> urlList = ProductUrlList.urlList();

		int idx = 0;
        for (String url : urlList) {
//            System.out.println("--------------");
            idx++;

           
//            System.out.println(url);

            driver.get(url);

            // 페이지 로딩 대기
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
            if (driver.findElements(By.cssSelector("div.swiper-wrapper:nth-child(2)")).size() > 0) {
            // 이미지 로딩 대기
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.swiper-wrapper:nth-child(2) > div"), 0));

            List<WebElement> swiperSlideList = driver.findElements(By.cssSelector("div.swiper-wrapper:nth-child(2) > div"));
            int idxx = 1;
            for (int i = 0; i < swiperSlideList.size(); i++) {
            	
            	 WebElement swiperSlide = swiperSlideList.get(i);
            	    WebElement imgElement = swiperSlide.findElement(By.tagName("img"));
            	    String imageUrl = imgElement.getAttribute("src");

            	    System.out.println("{");
            	    System.out.println(" \"product_no\" : " + idx + ",");
            	    System.out.println(" \"category\" : \"image_600_" + idxx++ + "\",");
            	    System.out.println(" \"imageUrl\" : \"" + imageUrl + "\",");
            	    System.out.println("},");

                if (i < swiperSlideList.size() - 1) {
                    // 이동할 이미지가 남아 있다면 다음 이미지로 이동
                    WebElement nextImageElement = swiperSlideList.get(i + 1);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextImageElement);

                    // 이미지가 화면에 나타날 때까지 대기
                    wait.until(ExpectedConditions.visibilityOf(nextImageElement));
                }
            }
            } else {
            	
            }
        }
        driver.quit();
    }
}
