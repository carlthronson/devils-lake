export default function RootLayout({ children }) {
  let title = "Carls App";

  return (
    <html lang="en">
      <head><title>{title}</title></head>
      <body>{children}</body>
    </html>
  )
}
