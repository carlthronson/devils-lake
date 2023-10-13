# nextjs

React/Nextjs is well documented with reasonably concise tutorials
There are some choices you need to make and the technology is changing

My app looks like this...
1. Use React Nextjs app
2. Create pages by creating folders with a page.jsx in them  /app/<?mypage?>/page.jsx
3. Create components by creating files /components/<?mycomp?>.jsx
4. Load data in components using useEffect and fetch /api/<?mycomp?>
5. Redirect /api urls to backend
6. drag and drop (react component)
7. Collapse expend (react component)

I had trouble to figure out how to redirect api calls to my AWS lambda backend API
I settled on this
1. Use /next.config.js to redirect /api to the backend AWS lambda https://<?myawsurl?>/dev/<?mycomp?>
2. The backend is another project inside this repo
