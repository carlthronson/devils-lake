// `app/task-board/page.js` is the UI for the `/task-board/` URL
'use client'
import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import StoryBoard from '../../components/StoryBoard';

const Title = styled.h1`
    text-align: center;
`   ;

const SubTitle = styled.h2`
    text-align: center;
`   ;

export default function Page() {
  const [taskCount, setTaskCount] = useState(0);

  useEffect(() => {
    const url = "/api/task/count";
    console.log("url: " + url);
    fetch(url)
      .then((response) => {
        const json = response.json();
        console.log("task count: " + JSON.stringify(json))
        return json;
      })
      .then((data) => {
        setTaskCount(data);
      });
  });

  return <div>
    <Title>After processing {taskCount} job openings...</Title>
    <SubTitle>These jobs remain as possibilities.</SubTitle>
    <div>
      {/* Coming soon... */}
      <StoryBoard></StoryBoard>
    </div>
  </div>
}
