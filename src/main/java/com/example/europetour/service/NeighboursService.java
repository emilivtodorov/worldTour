package com.example.europetour.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface NeighboursService {
    void seedNeighbours() throws FileNotFoundException;

}
