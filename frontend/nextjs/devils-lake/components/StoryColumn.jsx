import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Droppable } from 'react-beautiful-dnd';
import Story from './Story';

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

const StoryList = styled.div`
    padding: 3px;
  transistion: background-color 0.2s ease;
  background-color: #f4f5f7;
    flex-grow: 1;
    min-height: 100px;
`   ;

export default function StoryColumn({ statuses, phase, id }) {
    const [stories, setStories] = useState([]);

    useEffect(() => {
        const url = "/api/story/phase/" + phase.name + "/?pageNumber=1&pageSize=3";
        console.log("url: " + url);
        fetch(url)
            .then((response) => {
                const json = response.json();
                const body = response.body;
                return json;
            })
            .then((data) => {
                setStories(data);
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
                {phase.label + ' - ' + stories.length}
            </Title>
            <Droppable droppableId={id}>
                {(provided, snapshot) => (
                    <StoryList
                        ref={provided.innerRef}
                        {...provided.droppableProps}
                        isdraggingover={snapshot.isDraggingOver}
                    >
                        {stories.map((item, index) => (
                            <Story key={index} index={index} statuses={statuses} story={item} />
                            ))}
                        {provided.placeholder}
                    </StoryList>
                )}
            </Droppable>
        </Container>
    );
}
