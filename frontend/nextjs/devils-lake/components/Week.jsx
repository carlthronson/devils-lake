import styled from 'styled-components';
import './style.css';
import Day from './Day.jsx';
import moment from 'moment';

export default function Week({ week, index }) {
    console.log(`Week constructor: ${index}`);

    return <div className='week'>
    {week.map((day, index) => (
        <Day day={day} index={index} key={index}></Day>
        ))}
    </div>
}