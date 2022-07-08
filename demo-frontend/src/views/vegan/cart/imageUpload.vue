<script setup>
import { ref, reactive, computed, toRef } from "vue";
import axios from "axios";
import Swal from "sweetalert2";
// Set default properties
let toast = Swal.mixin({
  buttonsStyling: false,
  target: "#page-container",
  customClass: {
    confirmButton: "btn btn-success m-1",
    cancelButton: "btn btn-danger m-1",
    input: "form-control",
  },
});
const image = ref({
  imageUrl: null,
});

function fileUpload() {
  var files = document.getElementById("input").files;
  var params = new FormData();
  params.append("file", files[0]);
  console.log(params.get("file"));
  axios.post("http://localhost:8088/fileUpload", params).then((res) => {
    console.log(res.data);
    image.value = res.data;
    console.log(image);
  });
}
</script>

<template>
  <div>
    <h4>圖片上傳</h4>
    <input class="form-control" id="input" type="file" ref="myFile" />
    <div>
      <img :src="image.imageUrl" alt="..." />
      <div
        class="imagePreviewWrapper"
        :style="{ 'background-image': `url(${previewImage})` }"
        @click="selectImage"
      ></div>
      <br />

      <button class="btn btn-alt-primary" @click="fileUpload()">Submit</button>
    </div>
  </div>
</template>
