const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({

  transpileDependencies: true,
  server: {
    proxy: {
      "/api": {
        target: "https://smms.app",
        changeOrigin: true,
        secure: false,
        headers: {
          Referer: "https://smms.app",
        },
      },
    },
  },
  /*devServer: {
    proxy: {
      '/': {
        target: 'http://8.130.81.23:8080',
        changeOrigin: true,
        // ws: true,
        // pathRewrite: {
        //   '^/': ''
        // }

      }
    }
  }
*/
})
