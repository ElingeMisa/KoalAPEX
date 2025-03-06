import { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "./App.css";
import Navbar from "../components/Navbar/Navbar";
import Home from "../components/Pages/Home";
import Taskboards from "../components/Pages/Taskboards";
import KPIs from "../components/Pages/KPIs";
import { DragDropContext } from "react-beautiful-dnd";
import { v4 as uuidv4 } from "uuid";
import useLocalStorage from "use-local-storage";
import "../bootstrap.css";

function App() {
  const [data, setData] = useState(
    localStorage.getItem("kanban-board")
      ? JSON.parse(localStorage.getItem("kanban-board"))
      : []
  );

  const defaultDark = window.matchMedia(
    "(prefers-colors-scheme: dark)"
  ).matches;
  const [theme, setTheme] = useLocalStorage(
    "theme",
    defaultDark ? "dark" : "light"
  );

  const switchTheme = () => {
    setTheme(theme === "light" ? "dark" : "light");
  };

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

  const addCard = (title, bid) => {
    const index = data.findIndex((item) => item.id === bid);
    const tempData = [...data];
    tempData[index].card.push({
      id: uuidv4(),
      title: title,
      tags: [],
      task: [],
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

  const removeBoard = (bid) => {
    const tempData = [...data];
    const index = data.findIndex((item) => item.id === bid);
    tempData.splice(index, 1);
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
    localStorage.setItem("kanban-board", JSON.stringify(data));
  }, [data]);

  useEffect(() => {
    document.body.setAttribute("data-theme", theme);
  }, [theme]);

  const [activeBoard, setActiveBoard] = useState(null);

  return (
    <Router>
      <DragDropContext onDragStart={onDragStart} onDragEnd={onDragEnd}>
        <div className="App" data-theme={theme}>
          <Navbar switchTheme={switchTheme} theme={theme} />
          <div className="app_outer" style={{ marginLeft: "200px" }}>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route
                path="/taskboards"
                element={
                  <Taskboards
                    data={data}
                    setName={setName}
                    addCard={addCard}
                    removeCard={removeCard}
                    removeBoard={removeBoard}
                    updateCard={updateCard}
                    addBoard={addBoard}
                    activeBoard={activeBoard}
                    setActiveBoard={setActiveBoard}
                  />
                }
              />
              <Route path="/kpis" element={<KPIs />} />
            </Routes>
          </div>
        </div>
      </DragDropContext>
    </Router>
  );
}

export default App;