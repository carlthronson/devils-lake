// `app/task-board/page.js` is the UI for the `/task-board/` URL
'use client'
// import styled from 'styled-components';
import TaskBoard from '../../components/TaskBoard';

export default function Page() {
  return <div>
    <h1>Tasks</h1>
    <div>
      {/* Coming soon... */}
      <TaskBoard></TaskBoard>
    </div>
  </div>
}
