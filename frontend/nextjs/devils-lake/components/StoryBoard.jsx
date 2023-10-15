import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { DragDropContext } from 'react-beautiful-dnd';
import StoryColumn from './StoryColumn.jsx';

const StoryArea = styled.div`
  display: flex;
  flex-direction: row;
`;

const handleDragEnd = (result) => {
  const { destination, source, draggableId } = result;

  if (destination === null) return;
  if (source.droppableId == destination.droppableId) return;

}

export default function StoryBoard() {
  { /* State */ }
  const [phases, setPhases] = useState([]);
  const [statuses, setStatuses] = useState([]);

  useEffect(() => {
    const statusurl = "/api/status";
    fetch(statusurl)
      .then((response) => response.json())
      .then((json) => {
        setStatuses(json);
      });

    const url = "/api/phase";
    fetch(url)
      .then((response) => response.json())
      .then((json) => {
        json.sort((a, b) => ((a.id > b.id) ? 1 : -1));
        setPhases(json);
      });
  }, []);

  return (
    <DragDropContext onDragEnd={handleDragEnd}>
      <StoryArea>
        {/* This is where we will have columns for workflow states */}
        {phases.map((phase, index) => (
          <StoryColumn key={index} id={index} statuses={statuses} phase={phase}></StoryColumn>
        ))}
      </StoryArea>
    </DragDropContext>
  );
}

