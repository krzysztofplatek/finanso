package com.springboot.finanso.controller;

import com.springboot.finanso.entity.Finance;
import com.springboot.finanso.service.FinanceService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FinanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinanceService financeService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(FinanceController.class).build();
    }

    @Test
    public void testController() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSearchFinancesByKeyword() throws Exception {
        Finance finance1 = new Finance(1, "test", 11.98, "category1", "comment1", Date.valueOf("2019-12-12"));
        Finance finance2 = new Finance(2, "title2", -41.98, "category2", "comment2", Date.valueOf("2022-12-12"));
        List<Finance> finances = Arrays.asList(finance1, finance2);

        when(financeService.findByKeyword("test")).thenReturn(finances);
        mockMvc.perform(get("/finances/search?keyword=test"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("finances", finances))
                .andExpect(model().attribute("financesSize", 2))
                .andExpect(view().name("finances/list-search-results"));
    }

    @Test
    public void shouldAddNewFinance() throws Exception {
        Finance finance = new Finance(1, "title1", 11.98, "category1", "comment1", Date.valueOf("2019-12-12"));

        //when(financeService.saveFinance(finance)).thenReturn(finance);
        mockMvc.perform(post("/finances/save")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", String.valueOf(finance.getId()))
                        .param("title", finance.getTitle())
                        .param("amount", String.valueOf(finance.getAmount()))
                        .param("category", finance.getCategory())
                        .param("note", finance.getNote())
                        .param("date", String.valueOf(finance.getDate()))
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/finances/list"));
        verify(financeService).save(ArgumentMatchers.refEq(finance));
    }

    @Test
    public void shouldDeleteFinance() throws Exception {
        int id = 1;
        doNothing().when(financeService).deleteById(id);

        mockMvc.perform(get("/finances/delete")
                        .param("financeId", String.valueOf(id)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/finances/list"));

        verify(financeService, times(1)).deleteById(id);
    }


}
