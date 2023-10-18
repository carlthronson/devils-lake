import { Draggable } from 'react-beautiful-dnd';
import { useCollapse } from 'react-collapsed';
import styled from 'styled-components';
import { Avatar, Image } from 'antd';
import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import Task from './Task';

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

export default function Story({ story, statuses, index }) {
  const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()

  return (
    <Draggable draggableId={`${story.id}`} key={story.id} index={index}>
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
                <small>{story.label}</small>
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
                {story.tasks.map((item, index) => (
                  <div key={index}>
                  {/* <Link href={item.link}>{item.id}</Link> */}
                    <Task key={index} task={item} story={story} statuses={statuses} index={index} />
                    </div>
                ))}
            </section>
          {provided.placeholder}
        </Container>
      )}
    </Draggable >
  );
}
