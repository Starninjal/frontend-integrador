package com.wmw.frontend.integrador.util;

import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.google.gson.Gson;
import com.wmw.frontend.integrador.dto.ClienteDTO;

import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;

public class UtilHTTPCliente {
	private static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
	private static HttpStream httpStream;

	public static boolean conexaoComInternet() {
		try {
			InetAddress endereco = InetAddress.getByName("www.google.com");
			return !endereco.equals("");
		} catch (Exception e) {
			return false;
		}
	}
	
	public static <T> Response<T> GETClientesRemovidos(String url, Class<T> clazz) {
		HttpStream.Options optionss = new HttpStream.Options();
		try {
			optionss.httpType = HttpStream.GET;
			httpStream = new HttpStream(new URI(url), optionss);
			validarExcecoes(httpStream);
			Response<T> responseData = getResponseData(clazz, httpStream);
			return responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static <T> Response<T> POSTClienteSincronizacao(String url, Class<T> clazz, Object object) {
		HttpStream.Options options = new HttpStream.Options();
		try {
			options.httpType = HttpStream.POST;
			options.setContentType(CONTENT_TYPE_JSON);
			Gson gson = new Gson();
			String json = gson.toJson(object);
			options.data = json;
			httpStream = new HttpStream(new URI(url), options);
			validarExcecoes(httpStream);
			Response<T> responseData = getResponseData(clazz, httpStream);
			return responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static <T> Response<T> GETClienteApi(String url, Class<T> clazz) {
		HttpStream.Options optionss = new HttpStream.Options();
		try {
			optionss.httpType = HttpStream.GET;
			httpStream = new HttpStream(new URI(url), optionss);
			validarExcecoes(httpStream);
			Response<T> responseData = getResponseData(clazz, httpStream);
			return responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static <T> Response<T> POSTClienteApi(String url, Class<T> clazz, Object object) {
		HttpStream.Options options = new HttpStream.Options();
		try {
			options.httpType = HttpStream.POST;
			options.setContentType(CONTENT_TYPE_JSON);
			Gson gson = new Gson();
			String json = gson.toJson(object);
			options.data = json;
			httpStream = new HttpStream(new URI(url), options);
			validarExcecoes(httpStream);
			Response<T> responseData = getResponseData(clazz, httpStream);
			return responseData;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static <T> Response<T> getResponseData(Class<T> clazz, HttpStream httpStream) {
 		ByteArrayStream bas = new ByteArrayStream(4096);
		try {
			bas.readFully(httpStream, 10, 2048);
			String data = new String(bas.getBuffer(), 0, bas.available());
			Response<T> response = new Response<>();
			response.listData = null;
			response.data = null;
			T responseObject = null;
			if(!data.equals("[]") && !data.equals("{}") && !data.equals("")) {
				if(data.contains("[")) {
					List<T> responseList = JSONFactory.asList(data, clazz);
					response.listData = responseList;
				} else {
					responseObject = JSONFactory.parse(data, clazz);
					response.data = responseObject;
				}	
			}

			response.responseCode = httpStream.responseCode;
			return response;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		} finally {
			bas.close();
		}
	}

	public static class Response<T> {
		public T data;
		public List<T> listData;
		public int responseCode;
	}

	public static void validarExcecoes(HttpStream httpStream) throws IOException {

		switch (httpStream.responseCode) {
		case 409: {
			throw new RuntimeException("Documento já existente, tente novamente!");
		}
		
		case 500: {
			throw new RuntimeException("");
		}
		
		case 400: {
			ByteArrayStream bas = new ByteArrayStream(4096);
			bas.readFully(httpStream, 10, 2048);
			String data = new String(bas.getBuffer(), 0, bas.available());
			throw new RuntimeException(data);
		}
		
		}
	}
}
