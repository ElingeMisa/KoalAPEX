import { React, useEffect, useState } from "react";
import API_LIST from '../API/API';
import KPI_Board from "../KPI/KPI_Board";
import { DragDropContext } from "react-beautiful-dnd";
import { v4 as uuidv4 } from "uuid";

export default function KPIs() {
  const [isLoading, setLoading] = useState(false);
  const [items, setItems] = useState([]);
  const [error, setError] = useState(null);

  //const API_LIST = API_LIST.TODOLIST;

  // testing mode
  const testing_mode = true;
  const test_items = [{
    title: "Solicitudes Emergentes",
    tags: [{ tagName: "+5.2%", color: "#C74634" }],
    data: "8",
  },
  {
    title: "Tareas Activas",
    tags: [{ tagName: "+0%", color: "#7F7F7F" }],
    data: "17"
  },
  {
    title: "Tareas por Desarrollador",
    tags: [{ tagName: "-1.2%", color: "#C74634" }],
    data: "3.5"
  },
  {
    title: "Desfase Promedio",
    tags: [{ tagName: "+11%", color: "#33553C" }],
    data: "-1.5H"
  }];

  // Fetch data from API
  function reloadOneIteam(id) {
    fetch(API_LIST + "/" + id)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Something went wrong ...');
        }
      })
      .then(
        (result) => {
          const items2 = items.map(
            x => (x.id === id ? {
              ...x,
              'description': result.description,
              'done': result.done
            } : x));
          setItems(items2);
        },
        (error) => {
          setError(error);
        });
  }

  useEffect(() => {
    setLoading(true);
    fetch(API_LIST)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Something went wrong ...');
        }
      })
      .then(
        (result) => {
          setLoading(false);
          setItems(result);
        },
        (error) => {
          setLoading(false);
          setError(error);
        });
  }, []);

  // Board and Cards setup
  const [data, setData] = useState(
    localStorage.getItem("kpi-cards")
      ? JSON.parse(localStorage.getItem("kpi-cards"))
      : []
  );

  const setName = (title, bid) => {
    const index = data.findIndex((item) => item.id === bid);
    const tempData = [...data];
    tempData[index].boardName = title;
    setData(tempData);
  };
  
  const dragCardInBoard = (source, destination) => {
    let tempData = [...data];
    const sourceBoardIdx = tempData.findIndex(
      (item) => item.id.toString() === source.droppableId
    );
    const destinationBoardIdx = tempData.findIndex(
      (item) => item.id.toString() === destination.droppableId
    );
  
    const [movedCard] = tempData[sourceBoardIdx].card.splice(source.index, 1);
  
    if (sourceBoardIdx === destinationBoardIdx) {
      tempData[sourceBoardIdx].card.splice(destination.index, 0, movedCard);
    } else {
      tempData[destinationBoardIdx].card.splice(destination.index, 0, movedCard);
    }
  
    return tempData;
  };

  const addCard = (title, bid, tags, info) => {
    const index = data.findIndex((item) => item.id === bid);
    const tempData = [...data];
    tempData[index].card.push({
      id: uuidv4(),
      title: title,
      tags: tags,
      data: info,
    });
    setData(tempData);
  };

  const removeCard = (boardId, cardId) => {
    const index = data.findIndex((item) => item.id === boardId);
    const tempData = [...data];
    const cardIndex = data[index].card.findIndex((item) => item.id === cardId);
    tempData[index].card.splice(cardIndex, 1);
    setData(tempData);
  };

  const addBoard = (title) => {
    const tempData = [...data];
    tempData.push({
      id: uuidv4(),
      boardName: title,
      card: [],
    });
    setData(tempData);
  };

  const onDragStart = (start) => {
    setActiveBoard(start.source.droppableId); // Set the active board when dragging starts
  };

  const onDragEnd = (result) => {
    const { source, destination } = result;
    if (!destination) return;
    
    setData(dragCardInBoard(source, destination));
    setActiveBoard(destination.droppableId); // Set the active board when dragging ends
  };

  const updateCard = (bid, cid, card) => {
    const index = data.findIndex((item) => item.id === bid);
    if (index < 0) return;

    const tempBoards = [...data];
    const cards = tempBoards[index].card;

    const cardIndex = cards.findIndex((item) => item.id === cid);
    if (cardIndex < 0) return;

    tempBoards[index].card[cardIndex] = card;
    console.log(tempBoards);
    setData(tempBoards);
  };

  useEffect(() => {
    localStorage.setItem("kpi-cards", JSON.stringify(data));
  }, [data]);

  const [activeBoard, setActiveBoard] = useState(null);

  useEffect(() => {
    data.length === 0
    ?
      addBoard("KPI Board") 
    :
      data[0].card.length === 0 
      &&
        testing_mode 
        ?
          test_items.forEach((item) => {
            addCard(item.title, data[0].id, item.tags, item.data);
          })
        :
          items.forEach((item) => {
            addCard(item.title, data[0].id, item.tags, item.data);
          })
  }, [data, addBoard, addCard, items, testing_mode]);

  return (
    <div>
      <h1>KPIs</h1>
      {
        testing_mode && <h4>Testing Mode is Active</h4>
      }
      <DragDropContext onDragStart={onDragStart} onDragEnd={onDragEnd}>
        {
          data.map((item) => (
            <KPI_Board
              key={item.id}
              id={item.id}
              card={item.card}
              isLoading={isLoading}
              error={testing_mode ? null : error}
              isActive={activeBoard === item.id}
              setActiveBoard={setActiveBoard}
            />
          ))
        }
      </DragDropContext>
    </div>
  );
}