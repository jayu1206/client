package com.client.restController;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.client.model.Terminal;
import com.client.service.clientTerminalService;





@RestController
@RequestMapping("/api") 
public class clientRestController {
	
	private static final Logger logger = Logger
			.getLogger(clientRestController.class);
	
	@Autowired
	private clientTerminalService service;
	
	@RequestMapping(value="/getTerminal",
            method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Object>  getTerminal()
    {
		Terminal terminal = service.getTerminal(); 
		
		if(terminal==null){
			String str = "{msg : Marks the terminal as being locked, Makes a PUT request to the terminal server to unlock the terminal }";
			 return new ResponseEntity<Object>(str,HttpStatus.OK);
		 }else{
			 
			 if(terminal.getSequenceNo()>7){
				 return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			 }
			 return new ResponseEntity<Object>(terminal,HttpStatus.OK);
		 }
		
		
	  
    }

}
