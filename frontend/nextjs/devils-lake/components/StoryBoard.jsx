import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import PhaseColumn from './PhaseColumn.jsx';

const StoryArea = styled.div`
  display: flex;
  flex-direction: row;
`;

export default function StoryBoard() {
  { /* State */ }
  const [phases, setPhases] = useState([]);
  const [statuses, setStatuses] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const statusurl = "/api/status";
    fetch(statusurl)
      .then((response) => response.json())
      .then((json) => {
        setIsLoading(false);
        setStatuses(json);
      });

    const url = "/api/phase";
    fetch(url)
      .then((response) => response.json())
      .then((json) => {
        json.sort((a, b) => ((a.id > b.id) ? 1 : -1));
        setIsLoading(false);
        setPhases(json);
      });
  }, []);

  return (
    <StoryArea>
      {/* This is where we will have columns for workflow states */}
      {isLoading ? 'Loading...' : phases.map((phase, index) => (
        phase.name == 'done' ? null :
          <PhaseColumn key={index} id={index} statuses={statuses} phase={phase}></PhaseColumn>
      ))}
    </StoryArea>
  );
}

