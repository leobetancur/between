package com.prices.demo.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.prices.demo.DemoApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DemoApplication.class })
@WebAppConfiguration
@Sql({"/schema.sql", "/data.sql"})
class PricesIntegrationTest {

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;
  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }
  @Test
  @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void test1() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/prices?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value("35455"))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(1))
        .andExpect(jsonPath("$.startDate").value("2020-06-14-00.00.00"))
        .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"))
        .andExpect(jsonPath("$.price").value(35.50))
        .andReturn();
  }

  @Test
  @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void test2() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/prices?applicationDate=2020-06-14-16.00.00&productId=35455&brandId=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value("35455"))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(2))
        .andExpect(jsonPath("$.startDate").value("2020-06-14-15.00.00"))
        .andExpect(jsonPath("$.endDate").value("2020-06-14-18.30.00"))
        .andExpect(jsonPath("$.price").value(25.45))
        .andReturn();
  }

  @Test
  @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
  void test3() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/prices?applicationDate=2020-06-14-21.00.00&productId=35455&brandId=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value("35455"))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(1))
        .andExpect(jsonPath("$.startDate").value("2020-06-14-00.00.00"))
        .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"))
        .andExpect(jsonPath("$.price").value(35.50))
        .andReturn();
  }

  @Test
  @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
  void test4() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/prices?applicationDate=2020-06-15-10.00.00&productId=35455&brandId=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value("35455"))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(3))
        .andExpect(jsonPath("$.startDate").value("2020-06-15-00.00.00"))
        .andExpect(jsonPath("$.endDate").value("2020-06-15-11.00.00"))
        .andExpect(jsonPath("$.price").value(30.50))
        .andReturn();
  }

  @Test
  @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
  void test5() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/prices?applicationDate=2020-06-16-21.00.00&productId=35455&brandId=1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value("35455"))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(4))
        .andExpect(jsonPath("$.startDate").value("2020-06-15-16.00.00"))
        .andExpect(jsonPath("$.endDate").value("2020-12-31-23.59.59"))
        .andExpect(jsonPath("$.price").value(38.95))
        .andReturn();
  }

  @Test
  @DisplayName("Test Not found")
  void testNotFound() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/prices?applicationDate=2019-06-16-21.00.00&productId=35455&brandId=1"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Price Not Found"))
        .andReturn();
  }
}
