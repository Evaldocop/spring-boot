package com.evaldo.testeajax.service;



import org.springframework.stereotype.Service;

import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evaldo.testeajax.domain.SocialMetaTag;

@Service
public class SocialMetaTagService {
	
	private  static Logger log=LoggerFactory.getLogger(SocialMetaTag.class);
	
	
	public SocialMetaTag getSocialMetaTagbyUrl(String url) {
		
		SocialMetaTag twiter = getTwitterByUrl(url);
		if(!isEmpty(twiter))
			return twiter;
		
		SocialMetaTag og = getOpenGraficByUrl(url);
		if(!isEmpty(og))
			return og;
		
		return null;
		
		
		
	}
	
private SocialMetaTag getTwitterByUrl(String url) {
		
		SocialMetaTag tag  = new SocialMetaTag();
		try {
			Document doc = Jsoup.connect(url).userAgent("Chrome/33.0.1750.152")
				     .get();
			
			 tag.setTitle(doc.head().select("meta[property=og:title]").attr("content"));
			 tag.setSite(doc.head().select("meta[property=og:site]").attr("content"));
			 tag.setImage(doc.head().select("meta[property=og:image]").attr("content"));
				tag.setUrl(doc.head().select("meta[property=og:url]").attr("content"));
		} catch (IOException e) {
			log.error(e.getMessage(),e.getCause());
		}
	return tag;
}

	private SocialMetaTag getOpenGraficByUrl(String url) {
		
		SocialMetaTag tag  = new SocialMetaTag();
		try {
			Document doc = Jsoup.connect(url).userAgent("Chrome/33.0.1750.152")
				     .get();
			
			 tag.setTitle(doc.head().select("meta[property=og:title]").attr("content"));
			 tag.setSite(doc.head().select("meta[property=og:site_name]").attr("content"));
			 tag.setImage(doc.head().select("meta[property=og:image]").attr("content"));
				tag.setUrl(doc.head().select("meta[property=og:url]").attr("content"));
		} catch (IOException e) {
			log.error(e.getMessage(),e.getCause());
		}
	return tag;
	}
	
	
	private boolean isEmpty(SocialMetaTag tag) {
		if(tag.getImage().isEmpty())return  true;
		if(tag.getSite().isEmpty())return  true;
		if(tag.getUrl().isEmpty())return  true;
		if(tag.getTitle().isEmpty())return  true;
		return false;
	}

}
