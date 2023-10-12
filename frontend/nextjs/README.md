# nextjs

React/Nextjs is well documented with reasonably concise tutorials
There are some choices you need to make and the technology is changing

My app looks like this...
Use React Nextjs app
Create pages by creating folders with a page.jsx in them  /app/<?mypage?>/page.jsx
Create components by creating files /components/<?mycomp?>.jsx
Load data in components using useEffect and fetch /api/<?mycomp?>

I had trouble to figure out how to redirect api calls to my AWS lambda backend API
I settled on this
Use /next.config.js to redirect /api to the backend AWS lambda https://<?myawsurl?>/dev/<?mycomp?>
