package com.team1060.golf.infra.crawl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDetailPageOptionCrawling {
    public static void main(String[] args) {
        // WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

        // 크롤링 대상 URL 리스트
        List<String> urlList = ProductUrlList.urlList();

        int idx = 0;
        try (FileWriter writer = new FileWriter("output.json")) {
        for (String url : urlList) {
            idx++;

            driver.get(url);

            // 페이지 로딩 대기
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (driver.findElements(By.cssSelector("select[name='optSelect1']")).size() > 0) {
                // 옵션 로딩 대기
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name='optSelect1']")));

                WebElement optionSelect = driver.findElement(By.cssSelector("select[name='optSelect1']"));
                List<WebElement> options = optionSelect.findElements(By.tagName("option"));
                for (WebElement option : options) {
                    if (!option.getText().equals("선택하세요")) {
                        String productNo = String.valueOf(idx);
                        String name = option.getText().replaceAll("\\[품절\\]", "").trim();
                        String optionNo = option.getAttribute("value");
                        String count = option.getAttribute("stockqty");

                        // 옵션 정보를 Map에 저장
                        Map<String, String> optionMap = new HashMap<>();
                        optionMap.put("product_no", productNo);
                        optionMap.put("name", name);
                        optionMap.put("option_no", optionNo);
                        optionMap.put("count", count);

                        // Map을 출력 (혹은 저장)할 수 있는 형태로 변환
                        String jsonOption = convertMapToJson(optionMap);
                        System.out.print(jsonOption + ",\n");
                        writer.write(jsonOption + ",\n");
                    }
                }
            }
        }
    }
        catch (IOException e) {
        	e.printStackTrace();
		} finally {
			driver.quit();
		}
    }
    
    // Map을 JSON 형식의 문자열로 변환하는 메서드
    private static String convertMapToJson(Map<String, String> map) {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            json.append(" \"").append(entry.getKey()).append("\" : \"").append(entry.getValue()).append("\",");
        }
        // 마지막 쉼표 제거
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        return json.toString();
    }
}
