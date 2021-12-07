package com.evaldo.testeajax.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SocialMetaTag implements Serializable{



       private String site;
       private String title;
       private String url;
       private String image;
	public String getSite() {
		return site;
	}
	public void setSite(String name) {
		this.site = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "SocialMetaTag [site=" + site + ", title=" + title + ", url=" + url + ", image=" + image + "]";
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
       
       
	

}
