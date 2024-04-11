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

export default function Day({ day, today, index }) {

    return <div className={day.date() === today.date() && day.month() === today.month()
        && day.year() === today.year() ? 'today day' : 'day'}>
        <div className='date'>{day.format('MMM D')}</div>
        <div className='notes'>
            {getNotes(day).map((note, index) => (
                <p key={index}>{note}</p>
            ))}
        </div>
    </div>
}