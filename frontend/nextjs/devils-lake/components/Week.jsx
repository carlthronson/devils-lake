import styled from 'styled-components';
import './style.css';
import Day from './Day.jsx';
import moment from 'moment';

export default function Week({ week, index }) {
    // console.log(`Week constructor: ${index}`);

    let currentDay = moment().date();

    return <div className='week'>
    {week.map((day, index) => (
        <Day day={day} style={day.date() == currentDay ? {'background-color':'yellow'} : {}} index={index} key={index}></Day>
        ))}
    </div>
}