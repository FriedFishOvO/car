import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 7777,
    proxy: {
      '/api': { //任何以'/api'开头的请求都会被代理
        target: 'http://localhost:8080', //spring boot端口
        changeOrigin: true
      }
    }
  }
})
