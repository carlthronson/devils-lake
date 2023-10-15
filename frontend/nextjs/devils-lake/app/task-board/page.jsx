// `app/task-board/page.js` is the UI for the `/task-board/` URL
'use client'
// import styled from 'styled-components';
import StoryBoard from '../../components/StoryBoard';

export default function Page() {
  return <div>
    <h1>Tasks</h1>
    <div>
      {/* Coming soon... */}
      <StoryBoard></StoryBoard>
    </div>
  </div>
}
