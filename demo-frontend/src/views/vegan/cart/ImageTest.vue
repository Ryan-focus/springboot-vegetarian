<template>
  <div id="uplaod-wrap">
    <!-- ...... -->

    <!-- 選取圖片的 input -->
    <label for="upload">選擇上傳圖檔</label>
    <input id="upload" type="file" accept="image/*" @change="showFile" />

    <!-- 圖片標題 -->
    <label for="title">圖片名稱</label>
    <input
      class="form-control"
      id="title"
      type="text"
      v-model="title"
      @keyup="submit"
      required
    />

    <!-- 圖片描述 -->
    <label for="des">圖片描述</label>
    <input
      class="form-control"
      id="des"
      type="text"
      v-model="des"
      @keyup="submit"
      required
    />

    <!-- 上傳按鈕 -->
    <button class="btn btn-primary" type="button" @click="submit">上傳</button>
  </div>
</template>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<script>
const id = "XXXX"; // 填入 App 的 Client ID
const token = "XXXXXXXXX"; // 填入 token
const album = "XXXX"; // 若要指定傳到某個相簿，就填入相簿的 ID

//- upload
const upload = new Vue({
  el: "#uplaod-wrap",
  data: {
    album: album, // 若要指定傳到某個相簿，就填入相簿的 ID
    file: null, // 準備拿 input type="file" 的值
    fs: {
      name: "", // input的圖檔名稱
      thumbnail: null, // input的圖片縮圖
      size: null, // input的圖片大小
    },
    title: "", // 圖片標題
    des: "", // 圖片描述
  },
  methods: {
    showFile(e) {
      this.file = e.target.files[0]; // input type="file" 的值
      this.fs.name = this.file.name; // input的圖檔名稱
      this.fs.size = Math.floor(this.file.size * 0.001) + "KB"; // input的圖片大小
      this.fs.thumbnail = window.URL.createObjectURL(this.file); // input的圖片縮圖
      this.title = this.fs.name; // 預設 input 的圖檔名稱為圖片上傳時的圖片標題
    },
    submit() {
      let settings = {
        async: true,
        crossDomain: true,
        processData: false,
        contentType: false,
        type: "POST",
        url: "https://api.imgur.com/3/image",
        headers: {
          Authorization: "Bearer " + token,
        },
        mimeType: "multipart/form-data",
      };

      let form = new FormData();
      form.append("image", this.file);
      form.append("title", this.title);
      form.append("description", this.des);
      form.append("album", album); // 有要指定的相簿就加這行

      settings.data = form;

      $.ajax(settings).done(function (res) {
        console.log(res); // 可以看見上傳成功後回的值
        alert("上傳完成，稍待一會兒就可以在底部的列表上看見了。");
      });
    },
  },
});
</script>
