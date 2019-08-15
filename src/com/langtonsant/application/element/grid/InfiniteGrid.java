package com.langtonsant.application.element.grid;

import com.langtonsant.math.Rectangle;
import com.langtonsant.math.Vector2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class InfiniteGrid implements IGrid, Iterable<Vector2>
{
    private Vector2 minCoordinates;
    private Vector2 maxCoordinates;
    private Set<Vector2> blackCells;

    public InfiniteGrid(){
        reset();
    }

    @Override
    public int getWidth() {
        return maxCoordinates.x - minCoordinates.x;
    }

    @Override
    public int getHeight() {
        return maxCoordinates.y - minCoordinates.y;
    }

    @Override
    public boolean hasCellAt(Vector2 position) {
        return blackCells.contains(position);
    }

    @Override
    public Vector2 computeNewHeadingFrom(Vector2 positionInGrid, Vector2 headingVector) {
        if(hasCellAt(positionInGrid))
            return new Vector2(-headingVector.y, +headingVector.x);
        return new Vector2(+headingVector.y, -headingVector.x);
    }

    @Override
    public void cycleCellAt(Vector2 positionInGrid) {
        if(hasCellAt(positionInGrid)) {
            blackCells.remove(positionInGrid);
        }else {
            blackCells.add(new Vector2(positionInGrid));
        }
        updateBorderArea(positionInGrid);
    }

    @Override
    public void reset() {
        blackCells = new HashSet<>();
        maxCoordinates = Vector2.minAvailable();
        minCoordinates = Vector2.maxAvailable();
    }

    @Override
    public Iterator<Vector2> iterator() {
        return blackCells.iterator();
    }

    @Override
    public Rectangle GetArea(){
        return new Rectangle(minCoordinates.x,minCoordinates.y, getWidth(), getHeight());
    }

    /**
     * Update the borders of the grid.
     * It the given position is outside the current defined border, the border is "extended" to match the new area
     *
     * @param position the position to check
     */
    private void updateBorderArea(Vector2 position){
        if(position.x > maxCoordinates.x)
            maxCoordinates.x = position.x;
        if(position.y > maxCoordinates.y)
            maxCoordinates.y = position.y;

        if(position.x < minCoordinates.x)
            minCoordinates.x = position.x;
        if(position.y < minCoordinates.y)
            minCoordinates.y = position.y;
    }
}
