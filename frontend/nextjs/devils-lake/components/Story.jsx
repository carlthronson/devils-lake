import { useCollapse } from 'react-collapsed';
import styled from 'styled-components';
import { Avatar, Image } from 'antd';
import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import Task from './Task';

const StoryArea = styled.div`
    border-radius: 10px;
  box-shadow: 5px 5px 5px 2px grey;
    padding: 8px;
    color: #000;
    margin-bottom: 8px;
    min-height: 90px;
    margin-left: 10px;
    margin-right: 10px;
    background-color: #DCDCDC;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`   ;

export default function Story({ story, statuses, index }) {
  const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()

  return (
    <StoryArea>
      <div>
      <span href='' style={{ float: 'left' }}>{story.label}</span>
      <Link href='' style={{ float: 'right' }} {...getToggleProps()}>{isExpanded ? 'Collapse' : 'Expand Story'}</Link>
      </div>
      <section {...getCollapseProps()}>
        {story.tasks.map((item, index) => (
            <Task key={index} task={item} story={story} statuses={statuses} index={index} />
        ))}
      </section>
    </StoryArea>
  );
}
