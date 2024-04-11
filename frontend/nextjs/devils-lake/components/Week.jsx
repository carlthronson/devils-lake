import styled from 'styled-components';
import './style.css';
import Day from './Day.jsx';
import Today from './Today.jsx';

export default function Week({ week, today, index }) {

    let days = [];
    let dayOfWeek = 1;
    for (const day of week) {
        let isToday = day.date() === today.date() && day.month() === today.month() && day.year() === today.year();
        if (isToday) {
            console.log(`This day is today: ${day}`);
            days.push(<div className='today day' day={day} index={dayOfWeek} key={dayOfWeek}>TODAY</div>);
        } else {
            days.push(<div className='day' day={day} index={dayOfWeek} key={dayOfWeek}>DAY</div>);
        }
        dayOfWeek++;
    }
    return <div className='week'>
        {days.map((day) => (
            day
        ))}
    </div>
}