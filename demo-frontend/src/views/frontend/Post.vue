<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { ref } from "vue";
import axios from "axios";

//預設傳值伺服器與[params]
const url = "localhost:8088";
const urlParams = "warning";
//接收的資料ref
const resData = ref();
const productsTotal = ref();

// const getAxios = function () {
//   axios
//     .get(`http://${url}/products`, { params: { status: urlParams } })
//     .then((res) => {
//       //獲取伺服器的回傳資料
//       resData.value = res.data;
//       productsTotal.value = res.data.total;
//     })
//     .catch((error) => {
//       console.log(error, "失敗");
//     });
// };
//執行Axios
// getAxios();
// For Filters
</script>
<template>
  <!-- Hero -->
  <!-- Page Content -->
  <div class="content">
    <!-- 上方主題分類 -->

    <div class="row justify-content-center text-center">
      <div class="col-md-6 mb-3">
        <BaseBackground>
          <button type="button" class="btn btn-primary">Left</button>
          <button type="button" class="btn btn-primary">Left</button>
          <button type="button" class="btn btn-primary">Middle</button>
          <button type="button" class="btn btn-primary">Right</button>
        </BaseBackground>
      </div>
    </div>
    <!-- Modern Design -->
    <div class="row">
      <div class="container">
        <!-- Card deck -->
        <div class="card-deck row">
          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/16.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">1 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>

          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card mb-4">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/14.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">2 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>

          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card mb-4">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/15.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">3 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>

          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/16.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">4 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>

          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card mb-4">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/14.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">5 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>

          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card mb-4">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/15.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">6 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>

          <div class="col-xs-12 col-sm-6 col-md-4">
            <!-- Card -->
            <div class="card mb-4">
              <!--Card image-->
              <div class="view overlay">
                <img
                  class="card-img-top"
                  src="https://mdbootstrap.com/img/Photos/Others/images/15.jpg"
                  alt="Card image cap"
                />
                <a href="#!">
                  <div class="mask rgba-white-slight"></div>
                </a>
              </div>

              <!--Card content-->
              <div class="card-body">
                <!--Title-->
                <h4 class="card-title">7 Card title</h4>
                <!--Text-->
                <p class="card-text">
                  Some quick example text to build on the card title and make up
                  the bulk of the card's content.
                </p>
                <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                <button type="button" class="btn btn-light-blue btn-md">
                  Read more
                </button>
              </div>
            </div>
            <!-- Card -->
          </div>
        </div>
        <!-- Card deck -->
      </div>
    </div>
  </div>

  <!-- END Page Content -->
</template>
