<script setup>
import { ref, inject, reactive, computed } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import axios from "axios";

import useVuelidate from "@vuelidate/core";
import { required, sameAs, helpers } from "@vuelidate/validators";

const router = useRouter();

function logOut() {
    localStorage.removeItem("access-user");
    router.push({ name: "index" });
    renovate();
}

function showPassword() {
    var x = document.getElementById("password");
    var y = document.getElementById("newPassword");
    var z = document.getElementById("confirmNewPassword");
    if (x.type === "password" && y.type === "password" && z.type === "password") {
        x.type = "text";
        y.type = "text";
        z.type = "text";
    } else {
        x.type = "password";
        y.type = "password";
        z.type = "password";
    }
}

const state = reactive({
    password: null,
    newPassword: null,
    confirmNewPassword: null,
});

// Validation rules
const pwRule = helpers.regex(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/)
const rules = computed(() => {
    return {
        password: {
            required,
        },
        newPassword: {
            required,
            pwRule,
        },
        confirmNewPassword: {
            required,
            sameAs: sameAs(state.newPassword),
        },
    };
});
const rule = computed(() => {
    return {
        name: {
            required,
        },
    };
});

// Use vuelidate
const v$ = useVuelidate(rules, state);
const $v = useVuelidate(rule, state);

// On form submission
async function onSubmit() {
    const result = await v$.value.$validate();
    var formData = new FormData();

    formData.append("pw", state.password);
    formData.append("newpw", state.newPassword);
    formData.append("id", uid);

    if (!result) {
        return;
    }

    axios
        .post("http://localhost:8088/user/updatePassword", formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data;charset=utf-8'
                }
            })
        .then(function (response) {
            if (response.status === 200) {
                hideBackdrop();
                Swal.fire("密碼修改成功,請重新登入!", "⁽⁽٩(๑˃̶͈̀ ᗨ ˂̶͈́)۶⁾⁾", "success");
                logOut();
                renovate();
            }
        })
        .catch(function (error) {
            if (error.response.status === 404) {
                Swal.fire("密碼錯誤", "╮(╯_╰)╭", "error");
            } else if (error.response.status === 400) {
                Swal.fire("修改失敗", "(;´༎ຶД༎ຶ`)", "error");
            } else {
                console.log(error.response.status);
                console.log(error.response.data.error);
            }
        });
}
async function Submit() {
    const result = await $v.value.$validate();
    var formData = new FormData();

    formData.append("name", state.name);
    formData.append("id", uid);

    if (!result) {
        return;
    }

    axios
        .patch("http://localhost:8088/user/updateUserName", formData,
            {
                headers: {
                    'Content-Type': 'multipart/form-data;charset=utf-8'
                }
            })
        .then(function (response) {
            if (response.status === 200) {
                hideBackdrop();
                Swal.fire("修改成功", "⁽⁽٩(๑˃̶͈̀ ᗨ ˂̶͈́)۶⁾⁾", "success");
                renovate();
            }
        })
        .catch(function (error) {
            if (error.response.status === 400) {
                Swal.fire("修改失敗", "(;´༎ຶД༎ຶ`)", "error");
            } else {
                console.log(error.response.status);
                console.log(error.response.data.error);
            }
        });
}

function hideBackdrop() {
    document.querySelector('.modal-backdrop').remove();
}

// $("#modal").bind('hide.bs.modal', function () {
//     $(".modal-backdrop").remove();
// })

const renovate = inject("reload");

const user = JSON.parse(localStorage.getItem("access-user"));
const uid = user.data.user.userId;

const email = ref();
const name = ref();
const picture = ref();
const registerTime = ref();
const lastLoginTime = ref();

const getUser = () => axios.get(`http://localhost:8088/users/${uid}`)
    .then(function (response) {

        if (response.status === 200) {
            email.value = response.data.email;
            name.value = response.data.userName;
            picture.value = response.data.userPic;
            registerTime.value = response.data.registerTime;
            lastLoginTime.value = response.data.lastLoginTime;
        }
    })
    .catch(function (error) {
        if (error.status === 404) {
            console.log(error.data)
        } else {
            console.log(error.code);
            console.log(error.message);
        }
    });

getUser();

function add() {
    // state.password = '10Sec8763';
    state.newPassword = 'Cccc8763';
    state.confirmNewPassword = 'Cccc8763';
}

function addName() {
    state.name = '聖誕狐';
}

// function clear() {
//     if (el) {
//         el.addEventListener('hidden.bs.modal', function (e) {
//             document.querySelector(this)
//                 .querySelector("input,textarea,select")
//                 .val('')
//                 .end()
//                 .querySelector("input[type=checkbox], input[type=radio]")
//                 .prop("checked", "")
//                 .end();
//         })
//     }
// }
// function clear1() {
//     document.querySelector('.modal1').addEventListener('hidden.bs.modal', function (e) {
//         document.querySelector(this)
//             .querySelector("input,textarea,select")
//             .val('')
//             .end()
//             .querySelector("input[type=checkbox], input[type=radio]")
//             .prop("checked", "")
//             .end();
//     })
// }
// var el = document.querySelector(".modal");
</script>
<style>
/*
.page button {
    display: inline-block;
    border-radius: 4px;
    border: none;
    text-align: center;
    font-size: 28px;
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

.main-content img {
    width: 17rem;
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    border-radius: 50%;
    border: 1px solid rgba(255, 0, 0, 1.00);
}
*/

.gradient-custom {
    /* fallback for old browsers */
    background: #f6d365;

    /* Chrome 10-25, Safari 5.1-6 */
    background: -webkit-linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));

    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    background: linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1))
}

.gradient-custom img {
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    border-radius: 50%;
    border: 1px solid rgba(255, 0, 0, 1.00);
}

.bg-image img {
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
}

.modal-header h3 {
    font-size: 30px;
    color: 003E3E;
    /* 置中 */
    position: absolute;
    top: 7%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.modal-content {
    background-color: white;
    text-align: center;
}

.modal-body .card input {
    margin: 30px;
    width: 90%;
}

.modal-body .card img {
    width: auto;
    height: 300px;
    margin: auto;
    margin-bottom: 10px;
    border-radius: 50%;
}

.modal-body .btn {
    margin: 20px 0px;
    font-size: 20px;
    color: white;
}

body.modal-open {
    overflow-y: auto !important;
    padding-right: 0 !important;
}

/* .modal-backdrop {
    opacity: 0 !important;
    filter: alpha(opacity=0) !important;
} */

/* body {
    overflow: hidden;
} */

/* header {
    height: calc(100vh);
} */

::-webkit-scrollbar {
    display: none;
}

/* 表單 */

a {
    text-decoration: none;
    /* font-size: 30px; */
    color: 8600FF;
    /* margin: 50px; */
}

/* .modal-header h3 {
    font-size: 30px;
    color: blueviolet;
    position: absolute;
    top: 7%;
    left: 50%;
    transform: translate(-50%, -50%);
} */

.modal-body form .account,
.modal-body form .password {
    margin: 30px;
    width: 90%;
    border-radius: 20px;
}

.modal-body form .btn {
    width: 100%;
    font-size: 25px;
    color: white;
}

/* 隔 */
</style>
<template>
    <section class="vh-100" style="background-color: #f4f5f7;">
        <div class="bg-image" style="background-image: url('https://img.freepik.com/free-vector/colorful-vegetables-background_1284-21278.jpg?w=740&t=st=1658201666~exp=1658202266~hmac=d7c616c3b429494a517e63cd1b2c5b602ae4ee648c0241594184988af73fe4e4');
            height: 100vh">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-lg-6 mb-4 mb-lg-0">
                        <div class="card mb-3" style="border-radius: .5rem; margin-top: -20px;">
                            <div class="row g-0">
                                <div class="col-md-4 gradient-custom text-center text-white"
                                    style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                                    <img v-if="picture" :src="`data:image/png;base64,${picture}`" class="img-fluid my-5"
                                        style="height: 100px;" />
                                    <img v-else
                                        src="https://icon-library.com/images/no-user-image-icon/no-user-image-icon-3.jpg"
                                        class="img-fluid my-5" style="height: 100px;" />
                                    <h5>{{ name }}</h5>
                                    <p></p>
                                    <!-- <div class="card bg-white">
                                        <input type="file" accept="image/*" @change="uploadImage" id="file">
                                        <img :src="previewImage" />
                                        <button type="button" @click="submitFile">上傳</button>
                                    </div> -->
                                    <i class="far fa-edit mb-5" type="button" data-bs-toggle="modal"
                                        data-bs-target="#uploadImage"></i>
                                    <br>
                                    <!-- 按鈕 -->

                                    <!-- <a href="#" type="button" data-bs-toggle="modal"
                                        data-bs-target="#loginModal">會員登入</a> -->
                                    <a href="#" type="button" data-bs-toggle="modal"
                                        data-bs-target="#updateName">修改名稱</a>
                                    <br>
                                    <a href="#" type="button" data-bs-toggle="modal"
                                        data-bs-target="#updatePassword">修改密碼</a>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body p-4">
                                        <h6>個人資訊</h6>
                                        <hr class="mt-0 mb-4">
                                        <div class="row pt-1">
                                            <div class="col-6 mb-3">
                                                <h6>Email</h6>
                                                <p class="text-muted">{{ email }}</p>
                                            </div>
                                            <div class="col-6 mb-3">
                                                <h6>用戶名稱</h6>
                                                <p class="text-muted">{{ name }}</p>
                                            </div>
                                        </div>
                                        <h6>紀錄</h6>
                                        <hr class="mt-0 mb-4">
                                        <div class="row pt-1">
                                            <div class="col-6 mb-3">
                                                <h6>註冊日期</h6>
                                                <p class="text-muted">{{ registerTime }}</p>
                                            </div>
                                            <div class="col-6 mb-3">
                                                <h6>登入時間</h6>
                                                <p class="text-muted">{{ lastLoginTime }}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 跳出視窗內容 -->

    <div class="modal fade" id="uploadImage" data-bs-backdrop="static" data-bs-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Header -->
                <div class="modal-header">
                    <h3 style="margin-top:10px">上傳圖片</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <!-- Body -->
                <div class="modal-body">
                    <!-- 登入表單 -->
                    <div class="card bg-white" style="border:none">
                        <input type="file" accept="image/*" @change="uploadImage" id="file">
                        <img :src="previewImage" />
                    </div>
                    <button type="button" class="btn btn-info" @click="submitFile" data-bs-dismiss="modal">
                        <i class="fa fa-fw fa-upload me-1 opacity-50"></i>
                        上傳 </button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="updateName" data-bs-backdrop="static" data-bs-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Header -->
                <div class="modal-header">
                    <h3 style="margin-top:30px">修改名稱</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <!-- Body -->
                <div class="modal-body">
                    <!-- 登入表單 -->
                    <form @submit.prevent="Submit">
                        <div class="py-3" style="margin-top: 30px;">
                            <div class="mb-4">
                                <input type="text" class="form-control form-control-lg form-control-alt"
                                    placeholder="使用者名稱" :class="{
                                        'is-invalid': $v.name.$errors.length,
                                    }" v-model="state.name" @blur="$v.name.$touch" />
                                <div v-if="$v.name.$errors.length" class="invalid-feedback animated fadeIn">
                                    請輸入名稱
                                </div>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <div class="col-md-6 col-xl-5">
                                <button type="submit" class="btn w-100 btn-primary">
                                    <i class="fa fa-fw fa-pen-to-square me-1 opacity-50"></i>
                                    修改
                                </button>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="col-md-6 col-xl-5">
                                <button type="button" class="btn w-100 btn-success" @click="addName">
                                    <i class="far fa-fw fa-keyboard me-1 opacity-50"></i>
                                    一鍵
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="updatePassword" data-bs-backdrop="static" data-bs-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Header -->
                <div class="modal-header">
                    <h3 style="margin-top:25px">修改密碼</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <!-- Body -->
                <div class="modal-body">
                    <!-- 登入表單 -->
                    <form @submit.prevent="onSubmit">
                        <div class="py-3" style="margin-top: 40px;">
                            <div class="mb-4">
                                <input type="password" class="form-control form-control-lg form-control-alt"
                                    id="password" name="signup-password" placeholder="密碼" :class="{
                                        'is-invalid': v$.password.$errors.length,
                                    }" v-model="state.password" @blur="v$.password.$touch" />
                                <div v-if="v$.password.$errors.length" class="invalid-feedback animated fadeIn">
                                    請輸入你的密碼
                                </div>
                            </div>
                            <div class="mb-4">
                                <input type="password" class="form-control form-control-lg form-control-alt"
                                    id="newPassword" name="signup-password-confirm" placeholder="新密碼" :class="{
                                        'is-invalid': v$.newPassword.$errors.length,
                                    }" v-model="state.newPassword" @blur="v$.newPassword.$touch" />
                                <div v-if="v$.newPassword.$errors.length" class="invalid-feedback animated fadeIn">
                                    至少包含1個大小寫字母和數字，且字元個數介於8~20
                                </div>
                            </div>
                            <div class="mb-4">
                                <input type="password" class="form-control form-control-lg form-control-alt"
                                    id="confirmNewPassword" name="signup-username" placeholder="再次輸入新密碼" :class="{
                                        'is-invalid': v$.confirmNewPassword.$errors.length,
                                    }" v-model="state.confirmNewPassword" @blur="v$.confirmNewPassword.$touch" />
                                <div v-if="v$.confirmNewPassword.$errors.length"
                                    class="invalid-feedback animated fadeIn">
                                    請確認與新密碼相符
                                </div>
                                <!-- <div>
                                    <input type="checkbox" onclick="showPassword()">顯示密碼
                                </div> -->
                                <br>
                                <div class="mb-4" style="text-align: left;">
                                    <!-- <div class="form-check"> -->
                                    <input class="form-check-input" type="checkbox"
                                        @click="showPassword()">&nbsp;&nbsp;顯示密碼
                                    <!-- </div> -->
                                </div>
                            </div>
                        </div>
                        <div class="row mb-4">
                            <div class="col-md-6 col-xl-5">
                                <button type="submit" class="btn w-100 btn-primary">
                                    <i class="fa fa-fw fa-pen-to-square me-1 opacity-50"></i>
                                    修改
                                </button>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="col-md-6 col-xl-5">
                                <button type="button" class="btn w-100 btn-success" @click="add">
                                    <i class="far fa-fw fa-keyboard me-1 opacity-50"></i>
                                    一鍵
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
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

            var formData = new FormData();

            var imageFile = document.querySelector('#file');

            console.log(this.previewImage);

            formData.append("img", imageFile.files[0]);
            formData.append("id", this.uid);

            axios.post("http://localhost:8088/user/uploadImage", formData,
                {
                    headers: {
                        'Content-Type': 'multipart/form-data;charset=utf-8'
                    }
                })
                .then(response => {
                    if (response.status === 200) {
                        Swal.fire("上傳成功", "༼ つ ◕_◕ ༽つ", "success");
                        this.renovate();
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