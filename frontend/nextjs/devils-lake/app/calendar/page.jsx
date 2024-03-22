// `app/task-board/page.js` is the UI for the `/task-board/` URL
'use client'
import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import 'react-calendar/dist/Calendar.css';
import Week from '../../components/Week';
import moment from 'moment';

const CalendarContainer = styled.div`
  height: 100%;
  height: 100vh;
  background-color: antiquewhite;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`   ;

const getMonth = function () {
  // return [];
  let thisDay = moment();
  // let thisWeek = getWeek(thisDay);
  let thisMonth = thisDay.month();

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

  let current = moment();
  let weeks = [];

  let sunday = current.clone().startOf('week');

  let days = [];
  for (let x = 0; x < 7; x++) {
    days.push(sunday.clone().add(x, 'days'));
  }

  // days.map((day, index) => {
  //   console.log(`Day: ${index} - ${day.format('D')}`);
  // })

  weeks.push(days);

  // weeks.map((week, index) => {
  //   console.log(`Week: ${index}`);
  // })

  return <CalendarContainer>
    {getMonth().map((week, index) => (
      <Week week={week} index={index} key={index}/>
    ))}
  </CalendarContainer>
}
