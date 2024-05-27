package com.example.demo.web.validation;

import com.example.demo.api.response.ApiResponse;
import com.example.demo.api.response.ResponseCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionController {
    @PostMapping("/api/members")
    public ApiResponse<MemberCreateResponse> createMember(@Valid @RequestBody MemberCreateRequest member){ //bindingResult는 일반적으로 복합룰 에러를 검증할 때 넣음
        if(member.memberId.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }

        if (member.memberId.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력");
        }

        if (member.memberId.equals("other")) {
            throw new IllegalArgumentException("내부 에러");
        }

        return ApiResponse.response(ResponseCode.OK, MemberCreateResponse.of(member));

    }
    @Data
    @AllArgsConstructor
    static class MemberCreateRequest{
        @NotBlank(message = "회원 ID는 필수")
        private String memberId;
        @NotEmpty(message = "회원 이름은 공백일 수 없음")
        private String name;
        @Range(min = 20, max = 40)
        private int age;

    }
    @Data
    @AllArgsConstructor
    @Builder
    static class MemberCreateResponse{
        private long id;
        private String memberId;
        private String name;
        private int age;
        public static MemberCreateResponse of(MemberCreateRequest request){
            return MemberCreateResponse.builder()
                    .id(1)
                    .memberId(request.getMemberId())
                    .name(request.getName())
                    .age(request.getAge())
                    .build();

        }

    }
}
