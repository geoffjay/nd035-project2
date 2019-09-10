package com.udacity.pricing.domain.price;

import org.apache.commons.collections.IteratorUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Implements testing of the PriceRepository class.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;

    @Test
    public void whenSaved_thenFindsById() {
        BigDecimal value = new BigDecimal("99999.99");
        priceRepository.save(new Price("CAD", value, 1L));
        Price savedPrice = priceRepository.findById(1L).get();
        assertThat(savedPrice).isNotNull();
        assertThat(savedPrice.getPrice()).isEqualTo(value);
    }

    @Test
    public void whenFindAll_thenReturnProductList() {
        Iterable<Price> prices = priceRepository.findAll();
        Object[] o = IteratorUtils.toArray(prices.iterator());
        assertThat(o).hasSize(20);
    }
}
