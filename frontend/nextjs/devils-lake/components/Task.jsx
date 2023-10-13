import { Draggable } from 'react-beautiful-dnd';
import { useCollapse } from 'react-collapsed';
import styled from 'styled-components';
import { Avatar, Image } from 'antd';
import React, { useState, useEffect } from 'react';
import Link from 'next/link';

const CompanyHeader = styled.div`
    display: flex;
    justify-content: space-between;
    flex-direction: row;
`;

const Container = styled.div`
    border-radius: 10px;
  box-shadow: 5px 5px 5px 2px grey;
    padding: 8px;
    color: #000;
    margin-bottom: 8px;
    min-height: 90px;
    margin-left: 10px;
    margin-right: 10px;
    background-color: ${(props) => bgcolorChange(props)};
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`   ;

const TextContent = styled.div``;

const Icons = styled.div`
    display: flex;
    justify-content: end;
    padding: 2px;
`   ;
function bgcolorChange(props) {
  return props.isDragging
    ? 'lightgreen'
    : props.isDraggable
      ? props.isBacklog
        ? '#F2D7D5'
        : '#DCDCDC'
      : props.isBacklog
        ? '#F2D7D5'
        : '#EAF4FC';
}

export default function Task({ task, index }) {
  const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()
  const [subtasks, setSubtasks] = useState([]);

  useEffect(() => {
    console.log("useEffect");
    const url = "/api/subtask/task/" + task.id;
    console.log("url: " + url);
    fetch(url)
      .then((response) => {
        const json = response.json();
        console.log("json: " + json);
        const body = response.body;
        console.log("body: " + body);
        return json;
      })
      .then((data) => {
        console.log("start of the json that we read");
        console.log(data.length);
        console.log(data);
        console.log("end of the json that we read");
        // setCompanies(data.filter((company) => true));
        setSubtasks(data);
      });
  }, []);

  return (
    <Draggable draggableId={`${task.id}`} key={task.id} index={index}>
      {(provided, snapshot) => (
        <Container
          {...provided.draggableProps}
          {...provided.dragHandleProps}
          ref={provided.innerRef}
          isDragging={snapshot.isDragging}
        >
            <CompanyHeader {...getToggleProps()}>
                {/* <div> */}
                {/* <Button>{isExpanded ? '\u25B2' : '\u25BC'}</Button> */}
                {/* <Button>{isExpanded ? '-' : '+'}</Button> */}
                <small>{task.description}</small>
                {/* </div> */}
            </CompanyHeader>
            <section {...getCollapseProps()}>
                <div>
                    {/* <TextContent>{company.size + ' employees'}</TextContent> */}
                    {/* Number of jobs: {company.jobs.length} */}
                </div>
                {/* <button>{isExpanded ? 'Hide' : ('View' + company.jobs.length)}</button> */}
                {/* <button>{isExpanded ? '\u25B2' : ('View' + company.jobs.length +  ' \u25BC')}</button> */}
                {/* </CompanyHeader> */}
                <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}></div>
                <p></p>
                <ol>
                {subtasks.map((item, index) => (
                  <li key={index}>
                  <Link href={item.details}>{item.description}</Link>
                    {/* <Job key={index} job={item} index={index} /> */}
                    </li>
                ))}
                </ol>
            </section>
          {provided.placeholder}
        </Container>
      )}
    </Draggable >
  );
}
