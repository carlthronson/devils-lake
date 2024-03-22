import styled from 'styled-components';
import './style.css';

const birthdays = {
    'Apr 1': 'Kris',
    'Mar 16': 'Kate'
}

let getNotes = function(day) {
    return [birthdays[day.format('MMM D')]];
}

export default function Day({ day, index }) {
    console.log(`Day index: ${index}`);
    return <div className='day'>
        <div className='date'>{day.format('MMM D')}</div>
        <div className='notes'>
        {getNotes(day).map((note, index) => (
            <p key={index}>{note}</p>
        ))}
        </div>
    </div>
}