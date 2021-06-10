package com.jcp.service.DaoService.impl;

import com.jcp.dao.CirculationDao;
import com.jcp.service.DaoService.CirculationService;

import java.util.List;

public class circulationServiceImpl implements CirculationService {
    private CirculationDao circulationDao;
    public void setCirculationDao(CirculationDao circulationDao){
        this.circulationDao=circulationDao;
    }

    @Override
    public int addCirculation(com.jcp.domain.Circulation circulation) {
        int num=circulationDao.insertCirculation(circulation);
        return num;
    }

    @Override
    public List<com.jcp.domain.Circulation> queryCirculation() {
        List<com.jcp.domain.Circulation> circulations=circulationDao.selectCirculation();
        return circulations;
    }
}
