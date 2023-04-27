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


    public List<DeptDto> getDeptsFromBt() {
        String url = btBaseUrl + "/deptList";
        ResponseEntity<List<DeptDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DeptDto>>() {});
        return response.getBody();
    }
    public List<HobbyDto> getHobbysFromBt() {
        String url = btBaseUrl + "/hobbyList";
        ResponseEntity<List<HobbyDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<HobbyDto>>() {});
        return response.getBody();
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
    	 ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Boolean.class);
    	    return responseEntity.getBody();
    }
    public List<UserDto> getUserList() {
    	String url = btBaseUrl + "/userList";
    	ResponseEntity<List<UserDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {});
        return response.getBody();
    }
    public List<UserDto> searchUserList(String searchKeyword) {
    	String url = btBaseUrl + "/userSearchList?searchKeyword=" +searchKeyword;
    	ResponseEntity<List<UserDto>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserDto>>() {});
        return response.getBody();
    }
    public UserDto userInfo2(String user_id) {
    	String url = btBaseUrl +  "/userInfo?user_id=" + user_id;
    	ResponseEntity<UserDto> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<UserDto>() {});
    	return response.getBody();
    }
    public List<UserDto> getHobbyList(String user_id){
    	String url = btBaseUrl + "/userHobbyInfo?user_id="+ user_id;
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
