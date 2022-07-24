<script>
export default {
    name: "WebSocket",
    data() {
        return {
            account: null,
            // ws是否啟動
            wsIsRun: false,
            // 定義ws對象
            webSocket: null,
            // ws請求鏈接（類似於ws後台地址）
            ws: '',
            // ws定時器
            wsTimer: null,
        }
    },
    async mounted() {
        this.wsIsRun = true
        this.wsInit()
    },
    methods: {
        sendDataToServer() {
            if (this.webSocket.readyState === 1) {
                this.webSocket.send('來自前端的數據')
            } else {
                throw Error('服務未連接')
            }
        },
        /** 
         * 初始化ws 
         */
        wsInit() {
            const wsuri = `ws://localhost:8088/websocket/${this.account}`;
            this.ws = wsuri
            if (!this.wsIsRun) return
            // 銷毀ws 
            this.wsDestroy()
            // 初始化ws 
            this.webSocket = new WebSocket(this.ws)
            //ws連接建立時
            this.webSocket.addEventListener('open', this.wsOpenHanler)
            //wsServer推送 Client 推送消息
            this.webSocket.addEventListener('message', this.wsMessageHanler)
            //ws通訊發生錯誤時觸發
            this.webSocket.addEventListener('error', this.wsErrorHanler)
            // ws關閉時觸發
            this.webSocket.addEventListener('close', this.wsCloseHanler)

            // 檢查ws連接狀態,readyState值為0表示尚未連接，1表示建立連接，2正在關閉連接，3已經關閉或無法打開
            clearInterval(this.wsTimer)
            this.wsTimer = setInterval(() => {
                if (this.webSocket.readyState === 1) {
                    clearInterval(this.wsTimer)
                } else {
                    console.log('ws連接失敗')
                    this.wsInit()
                }
            }, 3000)
        },
        wsOpenHanler() {
            console.log('ws建立成功')
        },
        wsMessageHanler(e) {
            console.log('wsMessageHanler')
            console.log(e)
        },
        /** 
         * ws Error
         */
        wsErrorHanler(event) {
            console.log(event, '通訊中發生錯誤')
            this.wsInit()
        },
        /** 
         * ws Close
         */
        wsCloseHanler(event) {
            console.log(event, 'ws關閉')
            this.wsInit()
        },
        /** 
         * 銷毀ws 
         */
        wsDestroy() {
            if (this.webSocket !== null) {
                this.webSocket.removeEventListener('open', this.wsOpenHanler)
                this.webSocket.removeEventListener('message', this.wsMessageHanler)
                this.webSocket.removeEventListener('error', this.wsErrorHanler)
                this.webSocket.removeEventListener('close', this.wsCloseHanler)
                this.webSocket.close()
                this.webSocket = null
                clearInterval(this.wsTimer)
            }
        },
    }
} 
</script> 
