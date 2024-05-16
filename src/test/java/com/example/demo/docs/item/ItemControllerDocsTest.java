package com.example.demo.docs.item;

import com.example.demo.api.controller.ItemApiController;
import com.example.demo.api.request.ItemCreateRequest;
import com.example.demo.api.response.ItemResponse;
import com.example.demo.api.service.ItemApiService;
import com.example.demo.docs.RestDocsSupport;
import com.example.demo.domain.item.ItemSellingStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerDocsTest extends RestDocsSupport {
    private final ItemApiService itemApiService = mock(ItemApiService.class);

    @Override
    protected Object initController() {
        return new ItemApiController(itemApiService);
    }

    @DisplayName("신규 상품 등록")
    @Test
    void test() throws Exception{
        ItemCreateRequest request = ItemCreateRequest.builder()
                .name("좋은책")
                .price(5000)
                .stockQuantity(10)
                .sellingStatus(ItemSellingStatus.SELLING)
                .build();

        given(itemApiService.createItem(any(ItemCreateRequest.class)))
                .willReturn(ItemResponse.builder()
                        .itemId(3L)
                        .name("좋은책")
                        .price(5000)
                        .stockQuantity(10)
                        .sellingStatus(ItemSellingStatus.SELLING)
                        .build());

        mockMvc.perform(post("/api/item/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("item-create",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("상품이름"),
                                fieldWithPath("price").type(JsonFieldType.NUMBER)
                                        .description("상품가격"),
                                fieldWithPath("stockQuantity").type(JsonFieldType.NUMBER)
                                        .description("상품수량").optional(),
                                fieldWithPath("sellingStatus").type(JsonFieldType.STRING)
                                        .description("상품상태")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답데이터"),
                                fieldWithPath("data.itemId").type(JsonFieldType.NUMBER)
                                        .description("상품식별자"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING)
                                        .description("상품이름"),
                                fieldWithPath("data.price").type(JsonFieldType.NUMBER)
                                        .description("상품가격"),
                                fieldWithPath("data.stockQuantity").type(JsonFieldType.NUMBER)
                                        .description("상품수량"),
                                fieldWithPath("data.sellingStatus").type(JsonFieldType.STRING)
                                        .description("상품판매상태")
                        )
                ));
    }
}
