import React from "react";
import Board from "../Board/Board";
import Editable from "../Editable/Editable";

export default function Taskboards({ data, setName, addCard, removeCard, removeBoard, updateCard, addBoard, activeBoard, setActiveBoard }) {
  return (
    <div>
      <h1>Taskboards</h1>
      <div className="app_boards">
        {data.map((item) => (
          <Board
            key={item.id}
            id={item.id}
            name={item.boardName}
            card={item.card}
            setName={setName}
            addCard={addCard}
            removeCard={removeCard}
            removeBoard={removeBoard}
            updateCard={updateCard}
            isActive={activeBoard === item.id}
            setActiveBoard={setActiveBoard}
          />
        ))}
        <Editable
          class={"add__board"}
          name={"Add Board"}
          btnName={"Add Board"}
          onSubmit={addBoard}
          placeholder={"Enter Board Title"}
        />
      </div>
    </div>
  );
}