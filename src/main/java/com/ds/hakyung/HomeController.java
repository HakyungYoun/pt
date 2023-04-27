package com.ds.hakyung;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



//return 값 이용해 필요한 mustache 파일을 찾는다
//src/main/resource/templates 폴더 아래에 html 파일을 두어야 한다.
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String hello() {
		
		return"/user";
	}
}
