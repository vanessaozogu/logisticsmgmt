package com.ironhack.logisticsmgmt.service;

import com.ironhack.logisticsmgmt.model.Route;
import com.ironhack.logisticsmgmt.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(long id) {
        return routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route with id " + id + " not found"));
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route routeDetail) {
        Route route = getRouteById(id);
        route.setRouteCode(routeDetail.getRouteCode());
        route.setWave(routeDetail.getWave());
        route.setExpectedArrival(routeDetail.getExpectedArrival());
        route.setStage(routeDetail.getStage());
        route.setDriver(routeDetail.getDriver());
        return routeRepository.save(route);
    }

    public void deleteRouteById(long id) {
        routeRepository.deleteById(id);
    }
}
