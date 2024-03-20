export default function RootLayout({ children }) {
  let title = "Carl's App";
  return (
    <html lang="en">
      <head><title>{title}</title></head>
      <body>{children}</body>
    </html>
  )
}
