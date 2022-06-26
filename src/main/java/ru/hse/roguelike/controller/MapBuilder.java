package ru.hse.roguelike.controller;

/**
 * Interface for map builder
 **/
public interface MapBuilder {
    /**
     * Builds MapGenerator
     * @return new mapGenerator
     **/
    MapGeneration build();
}
