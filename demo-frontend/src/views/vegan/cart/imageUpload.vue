<template>
  <div>
    <h4>檔案上傳測試</h4>
    <input id="input" type="file" @change="fileUpload()" />
  </div>
</template>

<script>
export default {
  methods: {
    fileUpload() {
      var files = document.getElementById("input").files;
      var params = new FormData();
      params.append("file", files[0]);
      console.log(params.get("file"));
      this.$axios({
        url: "http://localhost:8080/products/fileUpload",
        method: "post",
        data: params,
        headers: { "Content-Type": "multipart/form-data" },
      });
    },
  },
};
</script>

<style scoped></style>
