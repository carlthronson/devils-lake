import styled from 'styled-components';
import './style.css';
import Day from './Day.jsx';

export default function Week({ week, today, index }) {
 
    return <div className='week'>
    {week.map((day, index) => (
        <Day day={day} today={today} index={index} key={index}></Day>
        ))}
    </div>
}