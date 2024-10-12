package com.isol.usuarios.mapper;

public interface IMapper <In, Out>{
    public Out map(In in);
}
