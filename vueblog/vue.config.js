const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({

  transpileDependencies: true,
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
