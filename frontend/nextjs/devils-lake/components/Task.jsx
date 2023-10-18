import styled from 'styled-components';
import { Avatar, Image } from 'antd';
import Link from 'antd/es/typography/Link';
import Select from 'react-select'
import React, { useState, useEffect } from 'react';
import { useCollapse } from 'react-collapsed'
// import { stat } from 'fs';

const TextContent = styled.div``;

const JobHeader = styled.div`
    display: flex;
    justify-content: space-between;
    flex-direction: row;
`;

const JobContainer = styled.div`
    border-radius: 2px;
  box-shadow: 1px 1px 1px 1px grey;
    padding: 8px;
    color: black;
    margin-bottom: 8px;
    min-height: 25px;
    margin-left: 10px;
    margin-right: 10px;
    background-color: lightgrey;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`   ;

const JobBodyLeft = styled.div`
    color: black;
    background-color: lightgrey;
    cursor: pointer;
    display: flex;
    flex-direction: column;
`;

function bgcolorChange(props) {
  return 'lightgreen';
}


export default function Task({ task, story, statuses, index }) {
  const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()

  const handleChange = (selectedOption) => {
    console.log('selected choice: ' + JSON.stringify(selectedOption));
    task.status.id = selectedOption.index + 1;
    task['story'] = { id: story.id };
    fetch('/api/task', {
      method: "POST",
      body: JSON.stringify(task),
      headers: {
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      credentials: 'include', // Useful for including session ID (and, IIRC, authorization headers)
    })
      .then(response => response.json())
      .then(data => {
      })
      .catch(error => console.error(error));
  }


  return (
    <JobContainer>
      <JobHeader {...getToggleProps()}>
        <Link href={task.job.linkedinurl}>{task.job.title}</Link>
      </JobHeader>
      <JobBodyLeft {...getCollapseProps()}>
        <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
          <TextContent>Status:</TextContent>
          <Select
            className="basic-single"
            classNamePrefix="select"
            defaultValue={{ value: task.status.name, label: task.status.label }}
            isDisabled={false}
            isLoading={false}
            isClearable={false}
            isRtl={false}
            isSearchable={false}
            name="Status"
            options=
            {statuses.map((item, index) => (
              { index: index, value: item.name, label: item.label }
            ))}
            onChange={handleChange}
          />

        </div>
        <TextContent>{story.location}</TextContent>
        <TextContent>today</TextContent>
        <TextContent>$$: {task.job.salary}</TextContent>
        <TextContent>Contract type: {task.job.contracttype}</TextContent>
        <TextContent>Experience Level: {task.job.experiencelevel}</TextContent>
        <TextContent>Sector: {task.job.sector}</TextContent>
      </JobBodyLeft>

      <div>
      </div>
    </JobContainer>
  );
}
