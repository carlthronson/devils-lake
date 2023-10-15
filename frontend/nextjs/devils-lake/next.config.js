module.exports = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'https://zve6rqyqh8.execute-api.us-west-1.amazonaws.com/dev/:path*' // Proxy to Backend
      }
    ]
  }
}
