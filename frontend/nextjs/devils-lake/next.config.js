module.exports = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'https://a5m5m0ixjc.execute-api.us-west-2.amazonaws.com/dev/:path*' // Proxy to Backend
      }
    ]
  }
}
