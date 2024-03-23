import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import './style.css';
import moment from 'moment';

const birthdays = {
    'Apr 1': 'Kris',
    'Mar 16': 'Kate',
    'Mar 24': 'Palm Sunday',
    'Mar 31': 'Easter'
}

let getNotes = function (day) {
    return [birthdays[day.format('MMM D')]];
}

// let currentDay = moment().date();
// console.log(`Current day: ${currentDay}`);

export default function Day({ day, backgroundColor, index }) {
    console.log(`Index: ${index} background-color: ${backgroundColor}`);
    // const [style, setStyle] = useState({});

    // useEffect(() => {
    //     setStyle({ 'backgroundColor': backgroundColor });
    // }, {});

    let style = {'backgroundColor': backgroundColor};

    return <div style={style} className={'day'}>
        <div className='date'>{day.format('MMM D')}</div>
        <div className='notes'>
            {/* <p>{backgroundColor}</p> when this is commented out, I see the problem.*/}
            {getNotes(day).map((note, index) => (
                <p key={index}>{note}</p>
            ))}
        </div>
    </div>
}