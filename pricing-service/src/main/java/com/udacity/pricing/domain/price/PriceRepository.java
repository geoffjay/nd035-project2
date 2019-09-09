package com.udacity.pricing.domain.price;

import com.udacity.pricing.service.PriceException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Long> {

    /**
     * If a valid vehicle ID, gets the price of the vehicle from the stored array.
     * @param vehicleId ID number of the vehicle the price is requested for.
     * @return price of the requested vehicle
     * @throws PriceException vehicleID was not found
     */
    @Query("select d.id, d.currency, d.price from #{#entityName} d where d.vehicleId=:vehicleId")
    public Price getPrice(Long vehicleId) throws PriceException;
}
