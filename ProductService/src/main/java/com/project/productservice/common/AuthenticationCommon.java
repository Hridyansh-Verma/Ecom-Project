package com.project.productservice.common;

import com.project.productservice.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthenticationCommon{
    private RestTemplate restTemplate;
    public AuthenticationCommon(RestTemplate restTemplate)
    {
        this.restTemplate=restTemplate;
    }
    public UserDto validateToken(String token)
    {
        ResponseEntity<UserDto> responseEntity= restTemplate.postForEntity(
                "http://localhost:8081/users/validate/"+token,null,
                UserDto.class);
        return responseEntity.getBody();

    }
}
