package com.unla.grupo21.services;

import com.unla.grupo21.models.LugarModel;

import java.util.List;

public interface ILugarService {

    public LugarModel findById(int id);
    public List<LugarModel> getAll();
    public LugarModel insertOrUpdate(LugarModel lugarModel);
}
