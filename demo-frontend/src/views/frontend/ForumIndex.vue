<script setup>
import { ref, } from "vue";
import Swal from "sweetalert2";

import axios from "axios";
import moment from 'moment';


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

//預設傳值伺服器與[params]
const url = "localhost:8088";
//接收的資料ref
const resData = ref();
const total = ref();

//用reactive會無法及時反應所以用ref另外宣告
const image = ref({
  imageUrl: null,
});


const getAxios = function () {
  axios
    .get(`http://${url}/forums`)
    .then((res) => {
      var fourmTotal = 0;

      for (let i = 0; i <= res.data.results.length - 1; i++)
        fourmTotal++;

      console.log(res);

      total.value = fourmTotal;
      //獲取伺服器的回傳資料
      resData.value = res.data.results;
    })
    .catch((error) => {
      console.log(error, "失敗");
    });
};
//執行Axios

getAxios();



</script>

<template>
  <!-- Hero -->
  <!-- <BasePageHeading title="Search" subtitle="Vital page found in most web applications.">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="javascript:void(0)">Generic</a>
          </li>
          <li class="breadcrumb-item" aria-current="page">最新文章</li>
        </ol>
      </nav>
    </template>
  </BasePageHeading> -->
  <!-- END Hero -->

  <!-- Search -->
  <!-- <div class="content">
    <form @submit.prevent>
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search.." />
        <span class="input-group-text">
          <i class="fa fa-fw fa-search"></i>
        </span>
      </div>
    </form>
  </div> -->
  <!-- END Search -->

  <!-- Page Content -->
  <div class="content">



    <div id="one-vue-hero" class="bg-body-extra-light">

      <div class="content content-full text-center">
        <!-- <div class="my-1"> -->
        <h1 class="h1 text-white mb-3"><b><a href="# "> <img
                src="https://www.thenewslens.com/assets/images/tnl/header/tnl_logo.svg" alt=""></a></b></h1>
        <h3>
          <!-- <span class="text-white-75"><b>從超過 50 家精選餐廳中，探索您不知道的熱門素食。</b></span> -->
        </h3>
        <div class="row">
          <!-- Results -->
          <BaseBlock class="overflow-hidden">
            <template #content>
              <ul class="nav nav-tabs nav-tabs-block" role="tablist">
                <li class="nav-item">
                  <button type="button" class="nav-link active" id="search-projects-tab" data-bs-toggle="tab"
                    data-bs-target="#search-projects" role="tab" aria-controls="search-projects" aria-selected="true">
                    醉心文章
                  </button>
                </li>
                <!-- <li class="nav-item">
                  <button type="button" class="nav-link" id="search-users-tab" data-bs-toggle="tab"
                    data-bs-target="#search-users" role="tab" aria-controls="search-users" aria-selected="false">
                    Users
                  </button>
                </li>
                <li class="nav-item">
                  <button type="button" class="nav-link" id="search-classic-tab" data-bs-toggle="tab"
                    data-bs-target="#search-classic" role="tab" aria-controls="search-classic" aria-selected="false">
                    Classic
                  </button>
                </li>
                <li class="nav-item">
                  <button type="button" class="nav-link" id="search-photos-tab" data-bs-toggle="tab"
                    data-bs-target="#search-photos" role="tab" aria-controls="search-photos" aria-selected="false">
                    Photos
                  </button>
                </li> -->
              </ul>
              <div class="block-content tab-content overflow-hidden">
                <!-- Projects -->
                <div class="tab-pane fade fade-up show active" id="search-projects" role="tabpanel"
                  aria-labelledby="search-projects-tab">
                  <div class="fs-4 fw-semibold p-2 mb-4 border-start border-4 border-primary bg-body-light">
                    <span class="text-primary fw-bold">{{ total }}</span> 醉心文章 found for
                    <mark class="text-danger">HTML</mark>
                  </div>
                  <table class="table table-striped table-vcenter">
                    <!-- <thead>
                      <tr>
                        <th style="width: 50%">Project</th>
                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                          Status
                        </th>
                        <th class="d-none d-lg-table-cell text-center" style="width: 15%">
                          Sales
                        </th>
                        <th class="text-center" style="width: 20%">Earnings</th>
                      </tr>
                    </thead> -->
                    <tbody>
                      <tr tr v-for="(row, index)  in resData" :key="index">
                        <td class="col-md-6 col-lg-4 col-xl-3">
                          <a href="#" :title="`${row.forumTitle}`">
                            <img class="img-fluid" :src="`/assets/media/photos/photo${row.forumId}@2x.jpg`"
                              :alt="`${row.forumTitle}`" />
                          </a>
                        </td>

                        <td class="d-none d-sm-table-cell" style="overflow:auto;
                          white-space: nowrap;
                          text-overflow: ellipsis;
                          max-width: 110px;">
                          <span>{{ moment(row.forumUpdateTime).format('YYYY/MM/D') }}</span>
                          <h4 class="h5 mt-0 mb-3">
                            <a href="javascript:void(0)">{{ row.forumTitle }} </a>
                          </h4>
                          <p class="d-none d-sm-block text-muted" v-html="row.forumContent">
                          </p>
                        </td>

                        <!-- <td class="d-none d-lg-table-cell text-center text-sm">
                          <span
                            class="fw-semibold d-inline-block py-1 px-3 rounded-pill bg-success-light text-success fs-sm">Completed</span>
                        </td>
                        <td class="d-none d-lg-table-cell font-size-xl text-center fw-semibold">
                          1603
                        </td>
                        <td class="font-size-xl text-center fw-semibold">$ 35,287</td> -->
                      </tr>

                    </tbody>
                  </table>
                  <!-- <nav aria-label="Projects Search Navigation">
                    <ul class="pagination pagination-sm">
                      <li class="page-item">
                        <a class="page-link" href="javascript:void(0)" tabindex="-1" aria-label="Previous">
                          Prev
                        </a>
                      </li>
                      <li class="page-item active">
                        <a class="page-link" href="javascript:void(0)">1</a>
                      </li>
                      <li class="page-item">
                        <a class="page-link" href="javascript:void(0)">2</a>
                      </li>
                      <li class="page-item">
                        <a class="page-link" href="javascript:void(0)">3</a>
                      </li>
                      <li class="page-item">
                        <a class="page-link" href="javascript:void(0)">4</a>
                      </li>
                      <li class="page-item">
                        <a class="page-link" href="javascript:void(0)" aria-label="Next">
                          Next
                        </a>
                      </li>
                    </ul>
                  </nav> -->
                </div>
                <!-- END Projects -->
              </div>
            </template>
          </BaseBlock>
          <!-- END Results -->
        </div>
        <!-- END Page Content -->

      </div>
    </div>
  </div>
</template>
