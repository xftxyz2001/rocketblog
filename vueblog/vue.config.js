// const { defineConfig } = require('@vue/cli-service')
// var webpack = require('webpack')
// module.exports = defineConfig({
//   plugins: [
//     new webpack.ProvidePlugin({
//       'window.Quill': 'quill'
//     })
//   ],
//   transpileDependencies: true,
//   // server: {
//   //   proxy: {
//   //     "/api": {
//   //       target: "https://smms.app",
//   //       changeOrigin: true,
//   //       secure: false,
//   //       headers: {
//   //         Referer: "https://smms.app",
//   //       },
//   //     },
//   //   },
//   // },
//   /*devServer: {
//     proxy: {
//       '/': {
//         target: '',
//         changeOrigin: true,
//         // ws: true,
//         // pathRewrite: {
//         //   '^/': ''
//         // }

//       }
//     }
//   }
// */
// })
const webpack = require('webpack')
module.exports = {
  chainWebpack: config => {
    config.plugin('provide').use(webpack.ProvidePlugin, [{
      'window.Quill': 'quill'
    }]);
  },
}
