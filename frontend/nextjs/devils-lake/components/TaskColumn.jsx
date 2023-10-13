import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
// import Task from './Task';
// import './scroll.css';
import { Droppable } from 'react-beautiful-dnd';
import Task from './Task';

const Container = styled.div`
    background-color: #f4f5f7;
    border-radius: 2.5px;
    width: 300px;
    height: 475px;
    overflow-y: scroll;
    -ms-overflow-style: none;
    scrollbar-width: none;
    border: 1px solid gray;
`   ;

const Title = styled.h3`
    padding: 8px;
    background-color: pink;
    text-align: center;
`   ;

const TaskList = styled.div`
    padding: 3px;
  transistion: background-color 0.2s ease;
  background-color: #f4f5f7;
    flex-grow: 1;
    min-height: 100px;
`   ;

export default function TaskColumn({ title, workflowState, id }) {
    const [tasks, setTasks] = useState([]);

    useEffect(() => {
        console.log("useEffect");
        const url = "/api/task/status/" + workflowState.status;
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
                setTasks(data);
            });
    }, []);

    return (
        <Container className='column'>
            <Title
                style={{
                    backgroundColor: 'lightblue',
                    position: 'stick',
                }}
            >
                {workflowState.label}
            </Title>
            <Droppable droppableId={id}>
                {(provided, snapshot) => (
                    <TaskList
                        ref={provided.innerRef}
                        {...provided.droppableProps}
                        isdraggingover={snapshot.isDraggingOver}
                    >
                        {tasks.map((item, index) => (
                            <Task key={index} index={index} task={item} />
                            ))}
                        {provided.placeholder}
                    </TaskList>
                )}
            </Droppable>
        </Container>
    );
}
