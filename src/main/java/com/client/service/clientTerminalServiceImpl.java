package com.client.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.client.model.Terminal;


@Service
@Transactional
public class clientTerminalServiceImpl implements clientTerminalService {

	@Override
	public Terminal getTerminal() {
		// TODO Auto-generated method stub
		 FileReader reader = null;
		 Properties p=new Properties();
		try {
			reader = new FileReader(getClass().getClassLoader().getResource("application.properties").getFile());
			
			p.load(reader);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return null;
		}  
		   
		
		
		final String uri = p.getProperty("uri")+"Terminal/api/getTerminal";
		RestTemplate restTemplate = new RestTemplate();
		Terminal terminal;
		try {
			terminal = restTemplate.getForObject(uri, Terminal.class);
			return terminal;
		} catch (Exception e) {
			// TODO: handle exception
			//e.getMessage();
			if(e.getMessage().contains("503 Service Unavailable")){
				return null;
			}
		}
		return null;
	}

}
