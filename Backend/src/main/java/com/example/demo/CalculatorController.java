package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@CrossOrigin("http://localhost:8080/")
public class CalculatorController {
	
  @GetMapping(path="{num1}/{op}/{num2}")
  public String result(@PathVariable("num1")double num1,@PathVariable("op")String op,@PathVariable("num2")double num2) {
			double res=0;
			  switch(op) {
			  case("addition"):
				  res=num1+num2;
			     break;
			  case("sub"):
				  res=num1-num2;
			     break;
			  case("mult"):
				  res=num1*num2;
			     break;
			  case("div"):
				  if(num2!=0)
				  res=num1/num2;
				  else
					  return "E";
			     break;
			  }
			  return String.valueOf(res);
  }
  @GetMapping(path="{op}/{num}")
  public String result1(@PathVariable("op")String op,@PathVariable("num")double num) {
		double res=0;
		  switch(op) {
		  case("root"):
			  if(num<0)
				  return "E";
			  res=Math.sqrt(num);
		     break;
		  case("square"):
			  res=Math.pow(num, 2);
		     break;
		  case("division"):
			  if(num!=0)
			  res=1/num;
			  else
				 return "E";
		     break;
		  case("percentage"):
			  res=num/100;
		     break;
		  case("changeSign"):
			  res=num*-1;
		  break;
		  }
		  return String.valueOf(res);
	  
  }
  
 
  
   
}
