package com.jcp.service.DaoService;

import com.jcp.domain.Circulation;

import java.util.List;

public interface CirculationService {
    int addCirculation(Circulation circulation);
    List<Circulation> queryCirculation();
}
