import React from "react";
import { Draggable } from "react-beautiful-dnd";
import "./KPI_Card.css";

const KPI_Card = (props) => {
  return (
    <Draggable
      key={props.id.toString()}
      draggableId={props.id.toString()}
      index={props.index}
    >
      {(provided) => (
        <div
          className={"kpi_custom__card"}
          ref={provided.innerRef}
          {...provided.draggableProps}
          {...provided.dragHandleProps}
        >
          <div className="kpi_card__content">
            <div className="kpi_card__left">
              <h4 className="kpi_title">{props.title}</h4>
              <div className="kpi_card__tags">
                {props.tags.map((tag, index) => (
                  <span
                    key={index}
                    style={{ backgroundColor: tag.color }}
                    className="kpi_tag"
                  >
                    {tag.tagName}
                  </span>
                ))}
              </div>
            </div>
            <div className="kpi_card__right">
              <p className="kpi_data">{props.data}</p>
            </div>
          </div>
        </div>
      )}
    </Draggable>
  );
};

export default KPI_Card;