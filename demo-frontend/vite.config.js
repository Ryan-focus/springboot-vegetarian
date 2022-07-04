import { fileURLToPath, URL } from "url";

// 幫手函式，這樣不用 jsdoc 註解也可以獲取型別提示
import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";

// https://vitejs.dev/config/
export default defineConfig({
  //配置需要使用的外掛列表
  plugins: [vue()],
  resolve: {
    alias: {
      "@": fileURLToPath(new URL("./src", import.meta.url)), // 別名
    },
  },
  //本地執行配置，以及反向代理配置 ，跨域代理
  server: {
    host: "localhost",
    https: false, //不允許https
    cors: true, //允許跨域
    open: true, // npm run dev之後直接開browser
    port: 8080, // 預設port 為8080
    strictPort: true, // 如果port被占用，直接退出 不會另外開新port ，預設為false
    Proxy: {
      //反向代理配置Axios
      "/api": {
        target: "http://localhost:8088/",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
});

/*  Vite.config 說明
使用 vite 建立專案完成後會自動生成 一個 vite.config.js 程式碼如下：

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
 plugins: [vue()],
})
當以命令方式執行 vite 時，vite 會自動解析專案根目錄下 vite.config.js 的檔案。配置不全時，在開發環境下執行都是正常的，但是打包上線的時候就會出現各種問題。如：

假設不配置 base 時，打包之後，訪問時出現白屏。
alias 不配置的時候，每次引入檔案需要找根目錄，比較麻煩。
以下是 vite.config.js 的更多常用引數配置以及意義：

import { defineConfig } from 'vite' // 幫手函式，這樣不用 jsdoc 註解也可以獲取型別提示
import vue from '@vitejs/plugin-vue'
const { resolve } = require('path')
import { viteMockServe } from "vite-plugin-mock"

const localEnabled = process.env.USE_MOCK || false;
const prodEnabled = process.env.USE_CHUNK_MOCK || false;

// https://vitejs.dev/config/
export default () => defineConfig({
 plugins: [ //配置需要使用的外掛列表
  vue(),
  viteMockServe({
   mockPath: "./src/server/mock",
   localEnabled: localEnabled, // 開發打包開關 true時開啟mock  false關閉mock
   prodEnabled: prodEnabled,//prodEnabled, // 生產打包開關
   // 這樣可以控制關閉mock的時候不讓mock打包到最終程式碼內
   injectCode: `
    import { setupProdMockServer } from './mockProdServer';
    setupProdMockServer();
   `,
   logger: false, //是否在控制檯顯示請求日誌
   supportTs:false //開啟後，可以讀取 ts 檔案模組 開啟後將無法監視 .js 檔案
  })
 ],
 // 強制預構建外掛包
 optimizeDeps: {
  //檢測需要預構建的依賴項
  entries: [],
  //預設情況下，不在 node_modules 中的，連結的包不會預構建
  include: ['axios'],
  exclude:['your-package-name'] //排除在優化之外
 },
 //靜態資源服務的資料夾
 publicDir: "public",
 base: './',
 //靜態資源處理
 assetsInclude: "",
 //控制檯輸出的級別 info 、warn、error、silent
 logLevel: "info",
 // 設為false 可以避免 vite 清屏而錯過在終端中列印某些關鍵資訊
 clearScreen:true,
 resolve: {
  alias: [//配置別名
   { find: '@', replacement: resolve(__dirname, 'src') }
  ],
  // 情景匯出 package.json 配置中的exports欄位
  conditions: [],
  // 匯入時想要省略的副檔名列表
  // 不建議使用 .vue 影響IDE和型別支援
  extensions:['.mjs','.js','.ts','.jsx','.tsx','.json']  
 },
 // css
 css: {
  // 配置 css modules 的行為
  modules: {  },
  // postCss 配置
  postcss: {
  },
  //指定傳遞給 css 前處理器的選項
  preprocessorOptions:{
   scss: {
    additionalData:`$injectedColor:orange;`
   }
  }
 },
 json: {
  //是否支援從 .json 檔案中進行按名匯入
  namedExports: true,
  //若設定為 true 匯入的json會被轉為 export default JSON.parse("..") 會比轉譯成物件字面量效能更好
  stringify:false
 },
 //繼承自 esbuild 轉換選項，最常見的用例是自定義 JSX
 esbuild: {
  jsxFactory: "h",
  jsxFragment: "Fragment",
  jsxInject:`import Vue from 'vue'`
 },
 //本地執行配置，以及反向代理配置
 server: {
  host: "localhost",
  https: false,//是否啟用 http 2
  cors: true,//為開發伺服器配置 CORS , 預設啟用並允許任何源
  open: true,//服務啟動時自動在瀏覽器中開啟應用
  port: "9000",
  strictPort: false, //設為true時埠被佔用則直接退出，不會嘗試下一個可用埠
  force: true,//是否強制依賴預構建
  hmr: false,//禁用或配置 HMR 連線
  // 傳遞給 chockidar 的檔案系統監視器選項
  watch: {
   ignored:["!node_modules/your-package-name"]
  },
  // 反向代理配置
  proxy: { 
   '/api': {
    target: "https://xxxx.com/",
    changeOrigin: true,
    rewrite: (path) => path.replace(/^\/api/, '')
   }
  }
 },
 //打包配置
 build: {
  //瀏覽器相容性  "esnext"|"modules"
  target: "modules",
  //指定輸出路徑
  outDir: "dist",
  //生成靜態資源的存放路徑
  assetsDir: "assets",
  //小於此閾值的匯入或引用資源將內聯為 base64 編碼，以避免額外的 http 請求。設定為 0 可以完全禁用此項
  assetsInlineLimit: 4096,
  //啟用/禁用 CSS 程式碼拆分
  cssCodeSplit: true,
  //構建後是否生成 source map 檔案
  sourcemap: false,
  //自定義底層的 Rollup 打包配置
  rollupOptions: {
  },
  //@rollup/plugin-commonjs 外掛的選項
  commonjsOptions: {
  },
  //構建的庫
  lib: {
  },
  //當設定為 true，構建後將會生成 manifest.json 檔案
  manifest: false,
  // 設定為 false 可以禁用最小化混淆，
  // 或是用來指定使用哪種混淆器
  // boolean | 'terser' | 'esbuild'
  minify: "terser", //terser 構建後文件體積更小
  //傳遞給 Terser 的更多 minify 選項。
  terserOptions: {
  },
  //設定為 false 來禁用將構建後的檔案寫入磁碟
  write: true,
  //預設情況下，若 outDir 在 root 目錄下，則 Vite 會在構建時清空該目錄。
  emptyOutDir: true,
  //啟用/禁用 brotli 壓縮大小報告
  brotliSize: true,
  //chunk 大小警告的限制
  chunkSizeWarningLimit: 500
 },
 ssr: {
  // 列出的是要為 SSR 強制外部化的依賴
  external: [],
  //列出的是防止被 SSR 外部化依賴項
  noExternal: [
  ]
 }
})
*/
