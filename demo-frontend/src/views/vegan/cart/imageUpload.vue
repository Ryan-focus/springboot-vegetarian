<template>
  <div>
    <h4>檔案上傳測試</h4>
    <input id="input" type="file" @change="fileUpload()" />
    <div>
      <br />
      <button class="btn btn-alt-primary">Submit</button>
    </div>
    <button></button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  methods: {
    fileUpload() {
      var files = document.getElementById("input").files;
      var params = new FormData();
      params.append("file", files[0]);
      console.log(params.get("file"));
      axios.post("http://localhost:8088/fileUpload", params);
    },
  },
};
</script>

<style scoped></style>
