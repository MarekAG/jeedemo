package com.example.jeedemo.api;

import com.example.jeedemo.domain.House;
import com.example.jeedemo.service.HouseManager;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("houses")
@Produces("application/json")
public class HousesResource {

    @EJB
    HouseManager hm;

    @GET
    public List<House> getAllHouses() {
        return hm.getAllHouses();
    }

    @GET
    public House get(@PathParam("id") Long id) {
        return hm.getById(id);
    }

    public List<House> getNewestHouses(@PathParam("quantity") Integer quantity) {
        return hm.getLastAddedHouses(quantity);

    }
}
