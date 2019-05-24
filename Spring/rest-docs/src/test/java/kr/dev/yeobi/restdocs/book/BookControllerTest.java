package kr.dev.yeobi.restdocs.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static kr.dev.yeobi.restdocs.book.utils.ApiDocumentUtils.getDocumentRequest;
import static kr.dev.yeobi.restdocs.book.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link BookController}
 * @author yeobi 2019.05.24
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@AutoConfigureRestDocs
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void getABook() throws Exception {
        // given
        given(bookService.findById(1L))
            .willReturn(BookDto.Response.builder()
                    .id(1L)
                    .title("yeobi_book")
                    .author("yeobi")
                    .publishedAt(new Date())
                    .build()
        );

        // when
        ResultActions result = this.mockMvc.perform(
                get("/book/{id}", 1L)
                    .accept(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(document("get-a-book"
                        , getDocumentRequest()
                        , getDocumentResponse()
                        , pathParameters(
                                parameterWithName("id").description("아이디")
                        )
                        , responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디")
                                , fieldWithPath("title").type(JsonFieldType.STRING).description("책 제목")
                                , fieldWithPath("author").type(JsonFieldType.STRING).description("작가")
                                , fieldWithPath("publishedAt").type(JsonFieldType.STRING).description("출판일")
                        )
                ));
    }

}