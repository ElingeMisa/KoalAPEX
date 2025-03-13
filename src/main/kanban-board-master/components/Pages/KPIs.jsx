import { React, useEffect, useState } from "react";
import API from '../API/API';
import KPI_Board from "../KPI/KPI_Board";
import { DragDropContext } from "react-beautiful-dnd";
import { v4 as uuidv4 } from "uuid";
import dayjs from "dayjs";

export default function KPIs() {
  const [isLoading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const [TAREAS, setTAREAS] = useState([]);
  const [SPRINTS, setSPRINTS] = useState([]);
  const [DESARROLLADORES, setDESARROLLADORES] = useState([]);

  // testing mode
  const testing_mode = false;
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
  useEffect(() => {
    setLoading(true);
    fetch(API.TAREAS)
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
          setTAREAS(result);
        },
        (error) => {
          setLoading(false);
          setError(error);
        });
    
    setLoading(true);
    fetch(API.SPRINTS)
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
          setSPRINTS(result);
        },
        (error) => {
          setLoading(false);
          setError(error);
        });
    
    setLoading(true);
    fetch(API.DESARROLLADORES)
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
          setDESARROLLADORES(result);
        },
        (error) => {
          setLoading(false);
          setError(error);
        });
  }, [API, setLoading, setError, setTAREAS, setSPRINTS, setDESARROLLADORES]);


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

  const addCard = (title, bid, tags, info = 0) => {
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
    setData(tempBoards);
  };

  // KPIs calculation
  const current_sprint = SPRINTS.filter((item) => dayjs().isAfter(item.fechaInicio) && dayjs().isBefore(item.fechaFin))[0];
  const previous_sprints = SPRINTS.filter((item) => dayjs().isAfter(item.fechaFin));

  const tareas_activas = () => {
    return TAREAS.filter((item) => 
      item.estado === "Activa" && 
      item.sprint.idSprint === current_sprint.idSprint
    ).length;
  };
  const tareas_activas_prev = () => { // Cálculo incorrecto, es temporal. Debe considerar todas las tareas que estuvieron activas
    return TAREAS.filter((item) =>
      item.estado === "Activa" &&
      previous_sprints.map(
        (sprint) => item.sprint.idSprint === sprint.idSprint
      )
    ).length;
  };

  const tareas_desarrollador = () => {
    const divison_tareas = DESARROLLADORES.map((desarrollador) =>
      TAREAS.filter((item) => 
        item.desarrollador.idDesarrollador === desarrollador.idDesarrollador && 
        item.sprint.idSprint === current_sprint.idSprint
      ).length
    );
    return (divison_tareas.reduce((a, b) => a + b, 0) / divison_tareas.length).toFixed(2);
  };
  const tareas_desarrollador_prev  = () => {
    const divison_tareas = DESARROLLADORES.map((desarrollador) =>
      TAREAS.filter((item) => 
        item.desarrollador.idDesarrollador === desarrollador.idDesarrollador && 
        previous_sprints.map(
          (sprint) => item.sprint.idSprint === sprint.idSprint
        )
      ).length
    );
    return (divison_tareas.reduce((a, b) => a + b, 0) / divison_tareas.length).toFixed(2);
  };

  // Save data to local storage
  useEffect(() => {
    localStorage.setItem("kpi-cards", JSON.stringify(data));
  }, [data]);

  const [activeBoard, setActiveBoard] = useState(null);

  useEffect(() => {
    if(data.length === 0) {
      addBoard("KPI Board") 
    }
    else
    {
      if(data[0].card.length === 0) {
        if(testing_mode)
        {
          test_items.forEach((item) => {
            addCard(item.title, data[0].id, item.tags, item.data);
          })
        }
        else
        {
          addCard(
            "Tareas Activas", 
            data[0].id,
            [{ tagName: "+0%", color: "#7F7F7F" }],
          );
          addCard(
            "Tareas por Desarrollador", 
            data[0].id,
            [{ tagName: "+0%", color: "#7F7F7F" }],
          );
        }
      }
      else
      {
        if(!testing_mode)
        {
          data[0].card.forEach((item) => {
            updateCard(data[0].id, item.id, {
              id: item.id,
              title: item.title,
              tags:
                item.title === "Tareas Activas" ?
                  [{
                    tagName:
                      (
                        tareas_activas() < tareas_activas_prev() ? "-" : "+") 
                      + Math.abs(100 - (tareas_activas() * 100 / tareas_activas_prev())).toFixed(2)
                      + "%", 
                    color:
                      tareas_activas() > tareas_activas_prev() ? 
                      "#C74634" //Rojo en +x%
                      : tareas_activas() < tareas_activas_prev() ? 
                      "#33553C" //Verde en -x%
                      : 
                      "#7F7F7F" //Gris en +0%
                  }]
                : item.title === "Tareas por Desarrollador" ?
                  [{
                    tagName:
                      (
                        tareas_desarrollador() < tareas_desarrollador_prev() ? "-" : "+") 
                      + Math.abs(100 - (tareas_desarrollador() * 100 / tareas_desarrollador_prev())).toFixed(2)
                      + "%", 
                    color:
                      tareas_desarrollador() > tareas_desarrollador_prev() ? 
                      "#C74634" //Rojo en +x%
                      : tareas_desarrollador() < tareas_desarrollador_prev() ? 
                      "#33553C" //Verde en -x%
                      : 
                      "#7F7F7F" //Gris en +0%
                  }]
                :
                  item.tags,
              data:
                item.title === "Tareas Activas" ?
                  tareas_activas()
                : item.title === "Tareas por Desarrollador" ?
                  tareas_desarrollador()
                :
                  item.data
            });
          });
        }
      }
    }
  }, [data, addBoard, addCard, updateCard, test_items, testing_mode, 
    tareas_activas
  ]);

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
              setName={setName}
            />
          ))
        }
      </DragDropContext>
    </div>
  );
}