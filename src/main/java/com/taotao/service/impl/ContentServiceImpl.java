package com.taotao.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.comm.util.JsonUtils;
import com.taotao.comm.util.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Value("${ftp_userName}")
	private String ftp_userName;

	@Override
	public String getContentList() {
		HttpClient httpClient = HttpClients.createDefault();
		List list = new ArrayList();
		String stt = REST_BASE_URL+REST_INDEX_AD_URL+ftp_userName;
		HttpGet get = new HttpGet(REST_BASE_URL+REST_INDEX_AD_URL);
		try {
			HttpResponse execute = httpClient.execute(get);
			HttpEntity entity = execute.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			List<TbContent> jsonToList = JsonUtils.jsonToList(string, TbContent.class);
			for(TbContent j : jsonToList) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("srcB", j.getPic());
				map.put("height", 240);
				map.put("alt", "");
				map.put("width", 670);
				map.put("src", j.getPic());
				map.put("widthB", 550);
				map.put("href", j.getPic());
				map.put("heightB", 240);
				list.add(map);
			}
			return JsonUtils.objectToJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
