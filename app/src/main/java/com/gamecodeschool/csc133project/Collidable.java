package com.gamecodeschool.csc133project;
import android.graphics.Point;

/**
 * The Collidable interface defines a contract for objects that can be checked for collisions.
 * Classes that implement this interface must provide an implementation for the checkCollision method.
 */
public interface Collidable {
    /**
     * Checks whether a collision occurred at the given location.
     *
     * @param location The location to check for a collision.
     * @return true if a collision occurred at the location, false otherwise.
     */
    boolean checkCollide(Wall mWall);
}