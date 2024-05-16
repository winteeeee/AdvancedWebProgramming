package com.example.demo.api.controller;

import com.example.demo.domain.Member;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class MemberApiControllerTest {
    @Test
    void 사용자이름검색() {
        TestRestTemplate rest = new TestRestTemplate();
        String searchName = "kim";
        ResponseEntity<Member[]> res = rest.getForEntity("http://localhost:8080/api/member/search?name={name}",
                Member[].class,
                searchName);

        List<Member> body = Arrays.asList(res.getBody());
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.get(0).getName()).contains(searchName);
    }

    @Test
    void 사용자이름검색2() {
        String searchName = "kim";
        TestRestTemplate rest = new TestRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/member/search")
                .queryParam("name", searchName)
                .encode()
                .build()
                .toUri();

        ResponseEntity<Member[]> res = rest.getForEntity(uri, Member[].class);
        List<Member> body = Arrays.asList(res.getBody());
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(body.get(0).getName()).contains(searchName);
    }
}