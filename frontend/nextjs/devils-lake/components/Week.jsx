import styled from 'styled-components';
import './style.css';
import Day from './Day.jsx';
import moment from 'moment';

export default function Week({ week, index }) {
    // console.log(`Week constructor: ${index}`);

    let currentDay = moment().date();

    return <div className='week'>
    {week.map((day, index) => (
        <Day day={day} backgroundColor={day.date() == currentDay ? 'aqua' : 'white'} index={index} key={index}></Day>
        ))}
    </div>
}