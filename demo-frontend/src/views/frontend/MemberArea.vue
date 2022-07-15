<script setup>
import { reactive, computed } from "vue";
import { useRouter } from "vue-router";
import { useTemplateStore } from "@/stores/template";
import Swal from "sweetalert2";
import axios from "axios";

// Main store
// const store = useTemplateStore();
// const router = useRouter();

</script>
<style>
/* .content {
    height: 704px;
} */
/* .page button {
    width: 100%;
} */

/* .page button:hover {
    color: white;
    background-color: skyblue;
    transition: 0.8s;
} */
.page button {
    /* display: inline-block; */
    border-radius: 4px;
    border: none;
    text-align: center;
    /* font-size: 28px; */
    padding: 10px;
    width: 100%;
    transition: 0.7s;
}

.mt-10 {
    margin-top: 10rem;
}

.bg-white {
    background-color: #fff;
}

.card {
    height: 10rem;
    width: 20rem;
    border-radius: 10px;
    padding: 20px;
    text-align: center;
}

img {
    width: 17rem;
}
</style>
<template>
    <div class="container-fluid">
        <div class="row content">
            <div class="col-sm-3 sidenav">
                <ul class="nav nav-tabs flex-column page">
                    <li><button class="nav-link active" data-bs-toggle="tab" data-bs-target="#menu1"
                            aria-selected="true">個人檔案</button></li>
                    <li><button class="nav-link" data-bs-toggle="tab" data-bs-target="#menu2"
                            aria-selected="false">訂位</button></li>
                    <li><button class="nav-link" data-bs-toggle="tab" data-bs-target="#menu3"
                            aria-selected="false">商品訂單</button></li>
                    <li><button class="nav-link" data-bs-toggle="tab" data-bs-target="#menu4"
                            aria-selected="false">文章</button></li>
                    <li><button class="nav-link" data-bs-toggle="tab" data-bs-target="#menu5"
                            aria-selected="false">網誌</button></li>
                </ul>
            </div>
            <div class="col-sm-9 main-content text-left">
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="menu1">
                        <!-- <div class="container mt-10">
                            <div class="card bg-white">
                                <img style="" :src="image" alt="">
                                <input @change="handleImage" class="custom-input" type="file" accept="image/*">
                            </div>
                        </div> -->
                        <p>
                            <input type="file" accept="image/*" @change="uploadImage" id="file">
                            <img :src="previewImage" />
                            <button type="button" @click="submitFile">上傳</button>
                        </p>
                        <span>個人檔案</span>
                    </div>
                    <div class="tab-pane fade" id="menu2">
                        <span>訂位</span>
                    </div>
                    <div class="tab-pane fade" id="menu3">
                        <span>商品訂單</span>
                    </div>
                    <div class="tab-pane fade" id="menu4">
                        <span>文章</span>
                    </div>
                    <div class="tab-pane fade" id="menu5">
                        <span>網誌</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
// export default {
//     data() {
//         return {
//             image: ''
//         }
//     },
//     methods: {
//         handleImage(e) {
//             const selectImage = e.target.files[0];
//             this.createBase64Image(selectImage);
//         },
//         createBase64Image(fileObject) {
//             const reader = new FileReader();

//             reader.onload = (e) => {
//                 this.image = e.target.result;
//                 console.log(e.target.result);
//             };
//             reader.readAsBinaryString(fileObject);
//         },
//     },
// }
export default {
    data() {
        return {
            previewImage: undefined
        }
    },
    methods: {
        uploadImage(e) {
            const [image] = e.target.files;
            const reader = new FileReader();
            reader.readAsDataURL(image);
            reader.onload = e => {
                this.previewImage = e.target.result;
                // console.log(this.previewImage);
            };
        },
        submitFile() {

            // const dataURLtoFile = (dataurl) => {
            //     const arr = dataurl.split(',')
            //     const mime = arr[0].match(/:(.*?);/)[1]
            //     const bstr = atob(arr[1])
            //     let n = bstr.length
            //     const u8arr = new Uint8Array(n)
            //     while (n) {
            //         u8arr[n - 1] = bstr.charCodeAt(n - 1)
            //         n -= 1 // to make eslint happy
            //     }
            //     return new File([u8arr], { type: mime })
            // }

            // const file = dataURLtoFile(this.previewImage);

            // console.log(this.previewImage);

            // const data = new FormData();
            // data.append('img', file);

            // let formData = new FormData();

            // formData.append('img', this.previewImage);

            // console.log(this.previewImage)

            const admin = JSON.parse(window.localStorage.getItem('access-user'))
            const id = admin.data.user.userId;

            var formData = new FormData();

            var imageFile = document.querySelector('#file');

            console.log(this.previewImage);

            formData.append("img", imageFile.files[0]);
            formData.append("id", id);

            axios.post("http://localhost:8088/user/uploadImage", formData,
                {
                    headers: {
                        'Content-Type': 'multipart/form-data;charset=utf-8'
                    }
                })
                .then(response => {
                    if (response.status === 200) {
                        Swal.fire("上傳成功", "༼ つ ◕_◕ ༽つ", "success");
                    }
                })
                .catch(function (error) {
                    if (error.response.status === 400) {
                        Swal.fire("上傳失敗", "◢▆▅▄▃崩╰(〒皿〒)╯潰▃▄▅▇◣", "error");
                    } else {
                        console.log(error.response.status)
                        console.log(error.response.data.error)
                    }
                })
        }
    }
}
</script>