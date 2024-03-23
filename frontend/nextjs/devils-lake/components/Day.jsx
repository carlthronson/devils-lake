import styled from 'styled-components';
import './style.css';
import moment from 'moment';

const birthdays = {
    'Apr 1': 'Kris',
    'Mar 16': 'Kate',
    'Mar 24': 'Palm Sunday',
    'Mar 31': 'Easter'
}

let getNotes = function(day) {
    return [birthdays[day.format('MMM D')]];
}

let currentDay = moment().date();
console.log(`Current day: ${currentDay}`);

export default function Day({ day, index }) {
    // console.log(`Day: ${day.date()}`);
    // console.log(`Compare to: ${moment().local().date()}`);
    let today = day.date() == currentDay;

    console.log(`Day: ${day.date()}`);
    console.log(`Is this day the current day: ${today}`);
    let className = today ? 'today day' : 'day';
    console.log(`Class name: ${className}`);

    return <div className={className}>
        <div className='date'>{day.format('MMM D')}</div>
        <div className='notes'>
        {getNotes(day).map((note, index) => (
            <p key={index}>{note}</p>
        ))}
        </div>
    </div>
}