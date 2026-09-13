const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware('/stomp-ws', {
      target: 'http://localhost:8080',
      changeOrigin: true,
      ws: true,
    })
  );

  app.use(
    createProxyMiddleware('/api', {
      target: 'http://localhost:8080',
      changeOrigin: true,
    })
  );

  app.use(
    createProxyMiddleware('/images', {
      target: 'http://localhost:8080',
      changeOrigin: true,
    })
  );
};