import styled from 'styled-components';
import { Avatar, Col, Image } from 'antd';
import Link from 'antd/es/typography/Link';
import Select from 'react-select'
import React, { useState, useEffect } from 'react';
import { useCollapse } from 'react-collapsed'
// import { stat } from 'fs';
import moment from 'moment';

const customStyles = {
  container: (provided) => ({
    ...provided,
    display: 'inline-block',
    // width: '250px',
    minHeight: '1px',
    textAlign: 'left',
    border: 'none',
    // backgroundColor: 'pink',
    paddingLeft: '10px',
  }),
  control: (provided) => ({
    ...provided,
    // border: '2px solid #757575',
    borderRadius: '0',
    minHeight: '1px',
    // height: '42px',
    // backgroundColor: 'pink',
  }),
  input: (provided) => ({
    ...provided,
    minHeight: '1px',
  }),
  dropdownIndicator: (provided) => ({
    ...provided,
    minHeight: '1px',
    paddingTop: '0',
    paddingBottom: '0',
    color: '#757575',
  }),
  indicatorSeparator: (provided) => ({
    ...provided,
    minHeight: '1px',
    // height: '24px',
  }),
  clearIndicator: (provided) => ({
    ...provided,
    minHeight: '1px',
  }),
  valueContainer: (provided) => ({
    ...provided,
    minHeight: '1px',
    // height: '40px',
    paddingTop: '0',
    paddingBottom: '0',
    paddingLeft: '0',
    paddingRight: '0',
    // backgroundColor: 'pink',
  }),
  singleValue: (provided) => ({
    ...provided,
    minHeight: '1px',
    paddingBottom: '2px',
  }),
};

const TaskArea = styled.div`
    border-radius: 2px;
  box-shadow: 1px 1px 1px 1px grey;
    padding: 8px;
    color: black;
    margin-top: 8px;
    // margin-bottom: 4px;
    min-height: 25px;
    margin-left: 10px;
    margin-right: 10px;
    background-color: cyan;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
`   ;

const Row = styled.div`
  flex: 1;
  // background-color: pink;
  padding: 3px
`;

function bgcolorChange(props) {
  return 'lightgreen';
}

export default function Task({ task, story, statuses, index }) {
  const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()

  const handleChange = (selectedOption) => {
    console.log('selected choice: ' + JSON.stringify(selectedOption));
    task.status.name = selectedOption.value;
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

  // console.log("Story: " + JSON.stringify(story));

  const isDisabled = (process.env.MODE == 'LIVE') ? false : true;

  return (
    <TaskArea>
      <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        gap: 8
      }}>
        <Link style={{  }} href={task.job.linkedinurl} target='_blank'>{task.job.name}</Link>
        {/* <p style={{float: 'right'}}>Right-aligned text</p> */}
        <Link style={{ flexShrink: 0 }} {...getToggleProps()}>{isExpanded ? 'Collapse' : 'Expand'}</Link>
      </div>
      <div style={{ display: 'flex' }} {...getCollapseProps()}>
        <div style={{ padding: '3px' }}>Status:
          <Select
            styles={customStyles}
            className="basic-single"
            classNamePrefix="select"
            defaultValue={{ value: task.status.name, label: task.status.label }}
            isDisabled={isDisabled}
            isLoading={false}
            isClearable={false}
            isRtl={false}
            isSearchable={false}
            name="Status"
            options=
            {statuses
              .filter((item) => item.name != 'invalid')
              .map((item, index) => (
                { index: index, value: item.name, label: item.label }
              ))}
            onChange={handleChange}
          />
        </div>

        <Row>Location: {task.job.location || story.location}</Row>
        <Row>Posted: {moment.utc(task.job.publishedAt).fromNow()}</Row>
        <Row>Salary: {task.job.salary}</Row>
        <Row>Contract type: {task.job.contracttype}</Row>
        <Row>Experience Level: {task.job.experiencelevel}</Row>
        <Row>Sector: {task.job.sector}</Row>

      </div>
    </TaskArea>
  );
}
