import React, { useEffect, useState } from "react";
import KPI_Card from "./KPI_Card";
import "./KPI_Board.css";
import { Droppable } from "react-beautiful-dnd";

export default function KPI_Board(props) {
  return (
    <div>
      {props.error && <p className="ErrorMessage">Error: {props.error.message}</p>}
      {props.isLoading && <p>Loading...</p>}
      {!props.isLoading && (
        <div className="kpi_board-container">
          <Droppable droppableId={props.id.toString()} direction="horizontal">
            {(provided) => (
              <div
                className="kpi_board__cards"
                ref={provided.innerRef}
                {...provided.droppableProps}
              >
                {props.card?.map((item, index) => (
                  <KPI_Card
                    bid={props.id}
                    key={item.id} // Ensure the key is unique
                    id={item.id}
                    index={index}
                    title={item.title}
                    data={item.data}
                    tags={item.tags}
                  />
                ))}
                {provided.placeholder}
              </div>
            )}
          </Droppable>
        </div>
      )}
    </div>
  );
}