package com.ds.hakyung.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ds.hakyung.user.domain.DeptDto;
import com.ds.hakyung.user.domain.HobbyDataDto;
import com.ds.hakyung.user.domain.HobbyDto;
import com.ds.hakyung.user.domain.UserDto;

@Service("ApiService")
public class ApiService {

    private final RestTemplate restTemplate=new RestTemplate();
    private final String btBaseUrl="http://localhost:8081";


    @SuppressWarnings("unchecked")
    public List<DeptDto> getDeptsFromBt() {
        String url = btBaseUrl + "/deptList";
		List <DeptDto> deptList = restTemplate.getForObject(url, List.class);
        return deptList;
    }
    @SuppressWarnings("unchecked")
    public List<HobbyDto> getHobbysFromBt() {
        String url = btBaseUrl + "/hobbyList";
		List<HobbyDto> hobbyList = restTemplate.getForObject(url, List.class);
        return hobbyList;
}
    public void insertUser(UserDto user) {
        String url = btBaseUrl + "/userInsert";
        restTemplate.postForObject(url, user, UserDto.class);
    }
    public void insertHobby(HobbyDataDto hobby) {
    	String url = btBaseUrl + "/hobbyDataInsert";
    	restTemplate.postForObject(url, hobby, HobbyDataDto.class);
    }
    public boolean userIdCheck(UserDto user) {
    	 String url = btBaseUrl + "/userIdCheck";
    	 Boolean userIdCheck = restTemplate.getForObject(url, Boolean.class);
    	    return userIdCheck;
    }
    @SuppressWarnings("unchecked")
    public List<UserDto> getUserList() {
    	String url = btBaseUrl + "/userList";
		List<UserDto> userList = restTemplate.getForObject(url, List.class);
        return userList;
    }
    @SuppressWarnings("unchecked")
    public List<UserDto> searchUserList(String searchKeyword) {
    	String url = btBaseUrl + "/userSearchList?searchKeyword=" +searchKeyword;
		List <UserDto> searchList = restTemplate.getForObject(url, List.class);
        return searchList;
    }
    public UserDto userInfo(String user_id) {
    	String url = btBaseUrl +  "/userInfo?user_id=" + user_id;
         return restTemplate.getForObject(url,UserDto.class);
     }
    public List<UserDto> getHobbyList(String user_id) {
        String url = btBaseUrl + "/userHobbyInfo?user_id=" + user_id;
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
        		new ParameterizedTypeReference<List<UserDto>>() {});
        return response.getBody();
    }
    public void updateUser(UserDto user,String user_id) {
    	String url = btBaseUrl + "/userUpdate?user_id="+ user_id;
        restTemplate.postForObject(url, user, UserDto.class);
    }
    public void deleteHobbyData(HobbyDataDto hb,String user_id) {
    	String url = btBaseUrl + "/userHobbyDelete?user_id="+ user_id;
    	restTemplate.postForObject(url, hb, HobbyDataDto.class);
    }
    public void userDelete(UserDto user,String user_id) {
    	String url = btBaseUrl + "/userDelete?user_id="+ user_id;
    	restTemplate.postForObject(url, user, UserDto.class);
    }
}
