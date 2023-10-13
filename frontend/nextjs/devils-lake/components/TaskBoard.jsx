import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { DragDropContext } from 'react-beautiful-dnd';
import TaskColumn from './TaskColumn.jsx';

const TaskArea=styled.div`
  display: flex;
  flex-direction: row;
`;

const handleDragEnd = (result) => {
  const { destination, source, draggableId } = result;

  if (destination === null) return;
  if (source.droppableId == destination.droppableId) return;

}

export default function TaskBoard() {
  { /* State */ }
  const [states, setStates] = useState([]);

  useEffect(() => {
    // console.log("useEffect");
    const url = "/api/state";
    // console.log("url: " + url);
    fetch(url)
  .then((response) => response.json())
      .then((json) => {
        // console.log("this is the json");
        // console.log(json);
        setStates(json);
      });
  }, []);

  return (
    <DragDropContext onDragEnd={handleDragEnd}>
      <TaskArea>
        {/* This is where we will have columns for workflow states */}
      {states.map((state, index) => (
        <TaskColumn key={index} id={index} workflowState={state}></TaskColumn>
      ))}
      </TaskArea>
    </DragDropContext>
  );
}

