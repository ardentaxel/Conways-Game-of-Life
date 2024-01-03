package game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class GameCellPlotter {
	
	private boolean showHoverCursor;
	
	public GameCellPlotter(boolean showHoverCursor) {
		this.showHoverCursor = showHoverCursor;
	}
	
	public void makePaintable( Node node) {


        // that's all there is needed for hovering, the other code is just for painting
        if( showHoverCursor) {
            node.hoverProperty().addListener(new ChangeListener<Boolean>(){

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                    if( newValue) {
                        ((GameCell) node).hoverHighlight();
                    } else {
                        ((GameCell) node).hoverUnhighlight();
                    }
                }

            });
        }

        node.setOnMousePressed( onMousePressedEventHandler);
        node.setOnDragDetected( onDragDetectedEventHandler);
        node.setOnMouseDragEntered(onMouseDragEnteredEventHandler);

    }

    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
    	
    	
        GameCell cell = (GameCell) event.getSource();

        if( event.isPrimaryButtonDown()) {
            cell.highlight();
            
        } else if( event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {

        PickResult pickResult = event.getPickResult();
        Node node = pickResult.getIntersectedNode();

        if( node instanceof GameCell) {

        	GameCell cell = (GameCell) node;

            if( event.isPrimaryButtonDown()) {
                cell.highlight();
            } else if( event.isSecondaryButtonDown()) {
                cell.unhighlight();
            }

        }

    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
    };

    EventHandler<MouseEvent> onDragDetectedEventHandler = event -> {

        GameCell cell = (GameCell) event.getSource();
        cell.startFullDrag();

    };

    EventHandler<MouseEvent> onMouseDragEnteredEventHandler = event -> {

    	GameCell cell = (GameCell) event.getSource();

        if( event.isPrimaryButtonDown()) {
            cell.highlight();
        } else if( event.isSecondaryButtonDown()) {
            cell.unhighlight();
        }

    };

}
