<!-- <template>
  <img src="https://i.imgur.com/X4z0q8g.png" />
  <br /><br />
  <input
    v-show="!imgurURL"
    type="file"
    id="image_file"
    @change="onFileChange"
    class="imgur"
    accept="image/*"
    required
  />
  <button v-show="imgurURL" type="button" @click="refreshNewImg">
    Upload New One
  </button>
  <br />
  <p>URL: {{ imgurURL }}</p>
  <br />
  <img :src="thumb" id="thumb" />
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      loading:
        "https://i.pinimg.com/originals/a4/f2/cb/a4f2cb80ff2ae2772e80bf30e9d78d4c.gif",
      thumbSample: "https://i.imgur.com/UoRevba.gif",
      thumb: null,
      imgurURL: null,
    };
  },
  methods: {
    onFileChange(event) {
      let files = event.target.files || e.dataTransfer.files;
      if (!files.length) {
        return;
      }

      this.thumb = this.loading;
      this.imgurURL = "Uploading...";

      let formdata = new FormData();

      formdata.append("image", files[0]);

      axios
        .post(`https://api.imgur.com/3/image`, formdata, {
          headers: {
            "Content-type": "application/x-www-form-urlencoded",
            Authorization: "Client-ID b455d92782e5284",
          },
          onUploadProgress: (percentage) => {
            let uploadPercent = Math.round(
              (percentage.loaded / percentage.total) * 100
            );
            console.log(`Uploaded ${uploadPercent} %`);
          },
        })
        .then((res) => {
          console.log(res.data);
          this.imgurURL = res.data.data.link;
          this.thumb = res.data.data.link;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    refreshNewImg() {
      this.imgurURL = "";
      document.getElementById("image_file").value = "";
      this.thumb = this.thumbSample;
    },
  },
  created() {
    this.thumb = this.thumbSample;
  },
};
</script> -->
