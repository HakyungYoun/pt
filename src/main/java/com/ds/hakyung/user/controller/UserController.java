package com.ds.hakyung.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.hakyung.user.ApiService;
import com.ds.hakyung.user.domain.DeptDto;
import com.ds.hakyung.user.domain.HobbyDataDto;
import com.ds.hakyung.user.domain.HobbyDto;
import com.ds.hakyung.user.domain.UserDto;



@Controller
public class UserController {
	
	@Resource(name="ApiService")
	ApiService service4;
	
	@GetMapping("user/reg")
	public String goUserReg(DeptDto dto2,Model model,HobbyDto dto3) {
		
		List<DeptDto> deptList=service4.getDeptsFromBt();
		List<HobbyDto> HobbyList=service4.getHobbysFromBt();
		model.addAttribute("deptList", deptList);
		model.addAttribute("hobbyList", HobbyList);
		return "user/userWrite";
	}
	@ResponseBody
	@RequestMapping("/user/request")
	public Map<String,Object> userRequest(UserDto dto,HobbyDataDto dto4){
		service4.insertUser(dto);
		String hobby_cd=dto4.getHobby_cd();
		String[] values = hobby_cd.split(",");
		for (String value : values) {
			dto4.setHobby_cd(value);
			service4.insertHobby(dto4);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("result", "success");
		return map;
	}
	@ResponseBody
	@GetMapping(value="/user/idcheck")
	public Map<String,String> userIdCheck(UserDto dto){
		Map<String,String> map=new HashMap<String,String>();
		if(service4.userIdCheck(dto)) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
//	
	@GetMapping("user/manager")
	public String goManagerPage(Model model,String searchKeyword) {
		List<UserDto> userList=service4.getUserList();
		List<DeptDto> deptList=service4.getDeptsFromBt();
		List<HobbyDto> HobbyList=service4.getHobbysFromBt();
		List<UserDto> searchList=service4.searchUserList(searchKeyword);
		if(searchKeyword==null ||searchKeyword=="") {
			model.addAttribute("userList", userList);
		}else {
			model.addAttribute("userList",searchList);
		}
		
		
		model.addAttribute("hobbyList", HobbyList);
		model.addAttribute("deptList", deptList);
		return "user/manager";
	}
	@ResponseBody
	@GetMapping("user/info")
	public Map<String,String> userInfo(@RequestParam("user_id") String user_id){
		Map<String,String> map=new HashMap<String,String>();
		UserDto userInfo=service4.userInfo2(user_id);
		List<UserDto> userHbInfo=service4.getHobbyList(user_id);
		if(userHbInfo!=null) {
		for (int i = 0; i < userHbInfo.size(); i++) {
			UserDto uhi=userHbInfo.get(i);
			map.put("user_hobby_info"+i, uhi.getHobby_cd());
		}
		
		}
		map.put("user_id", userInfo.getUser_id());
		map.put("user_nm", userInfo.getUser_nm());
		map.put("user_eml", userInfo.getUser_eml());
		map.put("user_dept_no", userInfo.getUser_dept_no());
		map.put("user_telno", userInfo.getUser_telno());
		map.put("user_addr", userInfo.getUser_addr());
		map.put("user_aprv_yn", userInfo.getUser_aprv_yn());
		
	
		return map;
	}
	@PostMapping("user/update")
	public String userUpdate(@RequestParam("user_id") String user_id,UserDto dto,HobbyDataDto dto2) {
		service4.updateUser(dto, user_id);
		service4.deleteHobbyData(dto2,user_id);
		String hobby_cd=dto2.getHobby_cd();
		String[] values = hobby_cd.split(",");
		for (String value : values) {
			dto2.setHobby_cd(value);
			service4.insertHobby(dto2);
		}
		return "redirect:/user/manager";
		
	}
	@PostMapping("user/delete")
	public String userDelete(@RequestParam("user_id") String user_id,UserDto dto) {
		service4.userDelete(dto,user_id);
		return "redirect:/user/manager";
		
	}
	
	}

