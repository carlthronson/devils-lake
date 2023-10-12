export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <head><title>Minimal runnable nextjs app</title></head>
      <body>{children}</body>
    </html>
  )
}
