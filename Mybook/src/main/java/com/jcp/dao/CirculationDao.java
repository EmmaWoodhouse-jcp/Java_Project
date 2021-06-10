package com.jcp.dao;

import com.jcp.domain.Circulation;

import java.util.List;

public interface CirculationDao {
    int insertCirculation(Circulation circulation);
    List<Circulation> selectCirculation();
}
