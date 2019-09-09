package com.udacity.pricing.domain.price;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Implements testing of the PriceRepository class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PriceRepositoryTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Price> json;

    @MockBean
    private PriceRepository priceRepository;

    /**
     * Creates pre-requisites for testing, such as an example price.
     */
    @Before
    public void setup() {
        Price price = getPrice();
        price.setId(1L);
        given(priceService.save(any())).willReturn(price);
        given(priceService.findById(any())).willReturn(price);
        given(priceService.list()).willReturn(Collections.singletonList(price));
    }

    /**
     * Tests for successful creation of new price in the system
     * @throws Exception when price creation fails in the system
     */
    @Test
    public void createPrice() throws Exception {
        Price price = getPrice();
        mvc.perform(
                post(new URI("/prices"))
                        .content(json.write(price).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    /**
     * Tests if the read operation appropriately returns a list of vehicles.
     * @throws Exception if the read operation of the vehicle list fails
     */
    @Test
    public void listPrices() throws Exception {
        Price price = getPrice();

        mvc.perform(
                post(new URI("/prices"))
                        .content(json.write(price).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        mvc.perform(get(new URI("/prices")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.priceList", hasSize(1)))
                .andExpect(jsonPath("$._embedded.priceList[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.priceList[0].currency", is("CAD")))
                .andExpect(jsonPath("$._embedded.priceList[0].price", is("99999.99")));
    }

    /**
     * Tests the read operation for a single price by ID.
     * @throws Exception if the read operation for a single price fails
     */
    @Test
    public void findPrice() throws Exception {
        Price price = getPrice();

        mvc.perform(
                post(new URI("/prices"))
                        .content(json.write(price).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        mvc.perform(get(new URI("/prices/1")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.currency", is("CAD")))
                .andExpect(jsonPath("$.price", is("99999.99")));
    }

    /**
     * Tests the deletion of a single price by ID.
     * @throws Exception if the delete operation of a vehicle fails
     */
    @Test
    public void deletePrice() throws Exception {
        Price price = getPrice();

        mvc.perform(
                post(new URI("/prices"))
                        .content(json.write(price).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());

        mvc.perform(
                delete(new URI("/prices/1")))
                .andExpect(status().isNoContent());
    }

    /**
     * Creates an example Price object for use in testing.
     * @return an example Price object
     */
    private Price getPrice() {
        Price price = new Price();
        price.setCurrency("CAD");
        price.setPrice("99999.99");
        price.setVehicleId(1L);
        return price;
    }
}
