package com.team1060.golf.infra.crawl;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.team1060.golf.product.mapper.ProductMapper;

import lombok.extern.log4j.Log4j2;

@Deprecated
public class WebCrawlProduct {
	
	public static void main(String[] args) throws IOException {
		List<String> urlList = ProductUrlList.urlList();;
		int idx = 0;
		for (String url : urlList) {
			
			idx++;
			Connection connection = Jsoup.connect(url);
			Document doc = connection.get();
			String benefit = doc.select("div.category-view-board table tbody div.card-bene-mo p").text();
			String no_interest_installment = doc.select("div.category-view-board table tbody a.sep-pay").text();

			System.out.println("{");
			System.out.println("'benefit' :" + "'" +benefit+"'");
			System.out.println("'no_interest_installment' :" + "'" +no_interest_installment+"'");
			System.out.println("},");
		}
	}
	
}
