<script setup>
import { ref, reactive, computed, toRefs } from "vue";
import axios from "axios";
</script>
<template>
  <br />
  <div class="container">
    <div>
      <h2>圖片上傳</h2>
      <hr />
      <label
        >File
        <input
          ref="upload"
          type="file"
          name="file-upload"
          multiple=""
          accept="image/jpeg, image/png"
          @change="onUploadFiles"
        />
      </label>
      <br />
      <button type="submit" class="btn btn-primary" @click="fileOutput">
        Submit
      </button>
    </div>
  </div>
</template>

<!-- <script>
export default {
  data() {
    return {
      file: [],
    };
  },
  methods: {
    fileOutput() {
      file = this.file;
    },
  },
};

const id = "66438a9adb39fba"; // 填入 App 的 Client ID
const token = "50b0002237bd4a0296303edf550fda2ad07189a6"; // 填入 token
// const album = "XXXX"; // 若要指定傳到某個相簿，就填入相簿的 ID

let formData = new FormData();
formData.append("image", this.file); //required
formData.append("title", "test"); //optional
formData.append("description", "test"); //optional
axios({
  method: "POST",
  url: "https://api.imgur.com/3/image",
  data: formData,
  headers: {
    Authorization:
      "66438a9adb39fba" + "50b0002237bd4a0296303edf550fda2ad07189a6", //放置你剛剛申請的Client-ID
  },
  mimeType: "multipart/form-data",
})
  .then((res) => {
    console.log(res);
  })
  .catch((e) => {
    console.log(e);
  });
</script> -->
