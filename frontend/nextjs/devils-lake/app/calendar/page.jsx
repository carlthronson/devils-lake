// `app/task-board/page.js` is the UI for the `/task-board/` URL
'use client'
import React, { useState, useEffect } from 'react';
// import styled from 'styled-components';
import Calendar from 'react-calendar';

export default function Page() {
  // const [taskCount, setTaskCount] = useState(0);
  // const [mode, setMode] = useState(null);

  // useEffect(() => {
  //   const url = "/api/task/count";
  //   console.log("url: " + url);
  //   fetch(url)
  //     .then((response) => {
  //       const json = response.json();
  //       console.log("task count: " + JSON.stringify(json))
  //       return json;
  //     })
  //     .then((data) => {
  //       setTaskCount(data);
  //     });
  //   setMode(process.env.MODE);
  // });

  return <div>
    <Calendar />
  </div>
}
