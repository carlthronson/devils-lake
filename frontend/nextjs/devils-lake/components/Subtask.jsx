import styled from 'styled-components';
import { Avatar, Image } from 'antd';
import Link from 'antd/es/typography/Link';
import Select from 'react-select'
import React, { useState, useEffect } from 'react';
import { useCollapse } from 'react-collapsed'

const TextContent = styled.div``;

const ToggleDetailsButton = styled.div`
    display: 'flex';
    justifyContent: 'right';
    padding: 2;
`;

const JobHeader = styled.div`
    display: flex;
    justify-content: space-between;
    flex-direction: row;
`;

const JobTitle = styled.div`
    display: 'flex';
    justifyContent: 'center';
    padding: 2;
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

const JobBody = styled.div`
    // border-radius: 2px;
    // box-shadow: 1px 1px 1px 1px grey;
    // padding: 8px;
    color: black;
    // margin-bottom: 8px;
    // min-height: 25px;
    // margin-left: 10px;
    // margin-right: 10px;
    background-color: lightgrey;
    cursor: pointer;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
`;

const JobBodyLeft = styled.div`
    // border-radius: 2px;
    // box-shadow: 1px 1px 1px 1px grey;
    // padding: 8px;
    color: black;
    // margin-bottom: 8px;
    // min-height: 25px;
    // margin-left: 10px;
    // margin-right: 10px;
    background-color: lightgrey;
    cursor: pointer;
    display: flex;
    // justify-content: space-between;
    flex-direction: column;
`;


const colourOptions = [
  { value: 'ocean', label: 'Ocean', color: '#00B8D9', isFixed: true },
  { value: 'blue', label: 'Blue', color: '#0052CC', isDisabled: true },
  { value: 'purple', label: 'Purple', color: '#5243AA' },
  { value: 'red', label: 'Red', color: '#FF5630', isFixed: true },
  { value: 'orange', label: 'Orange', color: '#FF8B00' },
  { value: 'yellow', label: 'Yellow', color: '#FFC400' },
  { value: 'green', label: 'Green', color: '#36B37E' },
  { value: 'forest', label: 'Forest', color: '#00875A' },
  { value: 'slate', label: 'Slate', color: '#253858' },
  { value: 'silver', label: 'Silver', color: '#666666' },
];



const Icons = styled.div`
    display: flex;
    justify-content: end;
    padding: 2px;
`   ;
function bgcolorChange(props) {
  return 'lightgreen';
}

// const options = [
//   { value: 'new', label: 'New' },
//   { value: 'applied', label: 'Applied' },
//   { value: 'saved', label: 'Saved' },
//   { value: 'closed', label: 'Closed' },
// ]

export default function Subtask({ subtask, index }) {
  const { getCollapseProps, getToggleProps, isExpanded } = useCollapse()
  // const [selected, setSelected] = useState(job.status);

  const [substates, setSubstates] = useState([]);

  useEffect(() => {
    // console.log("useEffect");
    const url = "/api/substate";
    // console.log("url: " + url);
    fetch(url)
      .then((response) => response.json())
      .then((json) => {
        // console.log("this is the json");
        // console.log(json);
        setSubstates(json);
      });
  }, []);

  const handleChange = (selectedOption) => {
    // setSelected(selectedOption);
    subtask.substate = selectedOption.value;
    // console.log(`Option selected:`, selectedOption);
    // console.log(JSON.stringify(subtask));
    // console.log("POSTING DATA");
    fetch('/api/subtask', {
      method: "POST",
      body: JSON.stringify(subtask),
      headers: {
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      credentials: 'include', // Useful for including session ID (and, IIRC, authorization headers)
    })
      .then(response => response.json())
      .then(data => {
        // console.log("data returned");
        // console.log(data) // Prints result from `response.json()`
      })
      .catch(error => console.error(error));
  }

  return (
    <JobContainer>
      <JobHeader {...getToggleProps()}>
        <small>{subtask.label}</small>
        {/* <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}></div> */}
        {/* <button>{isExpanded ? '\u25B2' : '\u25BC'}</button> */}
      </JobHeader>
      <JobBody  {...getCollapseProps()}>
        <JobBodyLeft>
          {/* <section> */}
          {/* <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Job id: {job.jobId}</TextContent>
          </div> */}
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Location: Palo Alto, CA</TextContent>
          </div>
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Date: today</TextContent>
          </div>
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Salary: dollars</TextContent>
          </div>
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <Link href={subtask.link}>LinkedIn Job Post</Link>
          </div>
          {/* </section> */}
          {/* </JobBodyLeft>
        <JobBodyLeft> */}
          {/* <section> */}
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Substate:</TextContent>
            <Select
              className="basic-single"
              classNamePrefix="select"
              defaultValue={{value: subtask.substate.name, label: subtask.substate.label}}
              isDisabled={false}
              isLoading={false}
              isClearable={false}
              isRtl={false}
              isSearchable={false}
              name="Status"
              options=
              {substates.map((item, index) => (
                { value: item.name, label: item.label }
              ))}
              onChange={handleChange}
            />

          </div>
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Reason: the reason</TextContent>
          </div>
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Applications: how many</TextContent>
          </div>
          <div style={{ display: 'flex', justifyContent: 'start', padding: 2 }}>
            <TextContent>Sector: sector</TextContent>
          </div>
          {/* <Icons>
              <div>
                <Avatar
                  onClick={() => console.log(job)}
                  src={'https://joesch.moe/api/v1/random?key=' + job.jobId}
                />
              </div>
            </Icons> */}
          {/* </section> */}
        </JobBodyLeft>
      </JobBody>

      <div>
      </div>
    </JobContainer>
  );
}
