// `app/task-board/page.js` is the UI for the `/task-board/` URL
'use client'
import React, { useState, useEffect } from 'react';
import 'react-calendar/dist/Calendar.css';
import Week from '../../components/Week';
import moment from 'moment';
import './style.css';

const getMonth = function () {
  let thisDay = moment();
  console.log(`This day ${thisDay}`);
  console.log(`This day local ${thisDay.local()}`);

  let month = [];
  let firstDay = false;
  let lastDay = false;
  for (let day = thisDay; !firstDay; day = day.clone().subtract(7, 'days')) {
    let week = getWeek(day);
    firstDay = week.find(day => day.date() === 1);
    month.unshift(week);
  }

  for (let day = thisDay.clone().add(7, 'days'); !lastDay; day = day.clone().add(7, 'days')) {
    let week = getWeek(day);
    lastDay = week.find(day => day.date() === thisDay.daysInMonth());
    month.push(week);
  }

  return month;
}

const getWeek = function (day) {
  let sunday = day.clone().startOf('week');
  let week = [];
  for (let x = 0; x < 7; x++) {
    week.push(sunday.clone().add(x, 'days'));
  }
  return week;
}

export default function Page() {

  return <div className='calendar-container'>
    {getMonth().map((week, index) => (
      <Week week={week} index={index} key={index}/>
    ))}
  </div>
}
