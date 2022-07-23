<script setup>
// 已經宣告但從未使用過的Value (請勿刪除)
import { useTemplateStore } from "@/stores/template";
import { reactive, computed, ref, onBeforeUnmount } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import { useLoading } from "vue3-loading-overlay";
// Vuelidate, for more info and examples you can check out https://github.com/vuelidate/vuelidate
import useVuelidate from "@vuelidate/core";
import {
  required,
  minLength,
  email,
  sameAs,
} from "@vuelidate/validators";

//using Axios 
import axios from "axios";

var restaurantNumber = ref(null);
var businessId = ref(null);
var adult = ref(null);
var kid = ref(null);
var reserveDate = ref(null);

//檢查localstorage裡面是否有東西，沒有設定為null不然直接抓會報錯
var reserveItemList = null;
if (window.sessionStorage.getItem("reserve") != null) {
  reserveItemList = JSON.parse(window.sessionStorage.getItem("reserve")).reserveItemList;
}

console.log("reserveItemList= " + reserveItemList);
//把session值帶入
restaurantNumber.value = reserveItemList[0];
businessId.value = reserveItemList[1];
adult.value = reserveItemList[2];
kid.value = reserveItemList[3];
reserveDate.value = reserveItemList[4];

const loader = useLoading({
  loader: 'dots',
  color: '#CCDBE2'
});
const reserveOrder = ref();
const restaurantName = ref();
const restaurantTel = ref();
const restaurantAddress = ref();
const restaurantCategory = ref();
const restaurantType = ref();
const restaurantBusinessHours = ref();
const restaurantScore = ref();
const imageUrl = ref();

const reserveRestaurant = function () {
  loader.show();
  axios
    .get(`http://localhost:8088/restaurants/${restaurantNumber.value}`)
    .then((res) => {
      //獲取伺服器的回傳資料
      reserveOrder.value = res.data;
      restaurantName.value = res.data.restaurantName;
      restaurantTel.value = res.data.restaurantTel;
      restaurantAddress.value = res.data.restaurantAddress;
      restaurantCategory.value = res.data.restaurantCategory;
      restaurantType.value = res.data.restaurantType;
      restaurantBusinessHours.value = res.data.restaurantBusinessHours;
      restaurantScore.value = res.data.restaurantScore;
      imageUrl.value = res.data.imageUrl;
    })
    .catch((error) => {
      console.log(error, "失敗");
    }).finally(() => {
      loader.hide();
    });
}
reserveRestaurant();
const store = useTemplateStore();
const router = useRouter();

const format = (d) => {
  const day = d.getDate();
  const month = d.getMonth() + 1;
  const days = d.getDay();
  const daysArray = ['週日', '週一', '週二', '週三', '週四', '週五', '週六'];

  return `${month}月${day}日 ${daysArray[days]}`;
}

const dataFormat = (dataDate) => {
  const day = dataDate.getDate();
  const month = dataDate.getMonth() + 1;
  const year = dataDate.getFullYear();

  return `${year}-${month}-${day}`;
}

// Input state variables
const state = reactive({
  username: null,
  userphone: null,
  email: null,
  password: null,
  confirmPassword: null,
  suggestions: null,
  skill: null,
  currency: null,
  website: null,
  digits: null,
  number: null,
  range: null,
  terms: null,
});
// Validation rules
const rules = computed(() => {
  return {
    username: {
      required,
      minLength: minLength(3),
    },
    userphone: {
      required,
      minLength: minLength(8),
    },
    email: {
      email,
    },
    terms: {
      sameAs: sameAs(true),
    },
  };
});

// Use vuelidate
const v$ = useVuelidate(rules, state);

// On form submission
async function onSubmit() {
  const result = await v$.value.$validate();

  if (!result) {
    // notify user form is invalid
    return;
  }

  // perform async actions
}

//離開頁面自動清除
onBeforeUnmount(() => {
  sessionStorage.removeItem("reserve");
})

function back() {
  router.go(-1);
}
// On form submission
async function sendData() {
  loader.show();
  const reserveData = {
    reserveDate: dataFormat(new Date(reserveDate.value)),
    adult: adult.value,
    child: kid.value,
    restaurantId: restaurantNumber.value,
    reserveName: state.username,
    reservePhone: state.userphone,
    reserveEmail: state.email
  };
  return axios.post(`http://localhost:8088/${businessId.value}/reserves`, reserveData)
    .then(response => {
      if (response.status === 201) {
        Swal.fire({
          title: "您的預訂已成功",
          timer: 1000,
          icon: "success"
        })
        loader.hide();
      }
    }).catch(err => {
      Swal.fire({
        title: "您的訂位失敗，請再試一次",
        timer: 1000,
        icon: "error"
      })
      console.log(err);
    }).finally(() => {
      loader.hide();
      location.replace("http://localhost:8080/#/");
    });
}

</script>

<template >
  <!-- Hero -->
  <BasePageHeading class="row">
    <div class="col-md-4 offset-md-6">
      <h1 class="mt-1 mb-0 p-0 fw-bold"> 確認訂位與填寫聯絡資訊</h1>
    </div>
  </BasePageHeading>
  <!-- END Hero -->
  <div class="content">
    <div class="row">
      <div class="col-lg-6">
        <div class="block-rounded block row g-0">
          <section class="">
            <BaseBlock class="mb-2" content-full>
              <img :src="`${imageUrl}`" class=" d-block w-100 img-fluid" :alt="`${restaurantName}`" />
              <ul class="nav nav-pills flex-column push">
                <li class="nav-item my-1">
                  <span class="nav-link d-flex justify-content-between align-items-center">
                    <i class="fa fa-location-dot fa-inbox me-1 opacity-50"> </i>
                    <h4 class="mt-3 fw-normal">{{ restaurantName }}</h4>
                  </span>
                </li>

                <li class="nav-item my-1">
                  <span class="nav-link d-flex justify-content-between align-items-center">
                    <i class="fa fa-user fa-inbox me-1 opacity-50"> </i>
                    <h5 class="mt-3 fw-normal">{{ adult }} 大 {{ kid }} 小</h5>
                  </span>
                </li>
                <li class="nav-item my-1">
                  <span class="nav-link d-flex justify-content-between align-items-center">
                    <i class="fa fa-calendar-days fa-inbox me-1 opacity-50"> </i>
                    <h5 class="mt-3 fw-normal">{{ format(new Date(reserveDate)) }}</h5>
                  </span>
                </li>
              </ul>
            </BaseBlock>
          </section>
        </div>
      </div>
      <form @submit.prevent="onSubmit" class="col-lg-6">
        <div class="block-rounded block row flex-md-row-reverse g-0">
          <div>
            <BaseBlock>

              <div class="row justify-content-center py-sm-3 py-md-5">
                <div class="col-sm-10 col-md-8">
                  <div class="mb-4 ">
                    <label class="form-label me-1" for="block-form5-username">訂位人姓名</label><span
                      class="text-warning">*</span>
                    <input type="text" id="val-username" class="form-control" :class="{
                      'is-invalid': v$.username.$errors.length,
                    }" v-model="state.username" @blur="v$.username.$touch" />
                    <div v-if="v$.username.$errors.length"
                      class="col-12 invalid-feedback animated fadeIn bg-secondary text-light">
                      <i class="fa fa-triangle-exclamation text-warning mx-2"></i>
                      訂位人姓名不可留空
                    </div>
                  </div>
                  <div class="mb-4 col-12">
                  </div>
                  <div class="mb-4">
                    <label class="form-label me-2" for="block-form5-phone">訂位人手機號碼</label><span
                      class="text-warning">*</span>
                    <input type="text" id="val-userphone" class="form-control " :class="{
                      'is-invalid': v$.userphone.$errors.length,
                    }" v-model="state.userphone" @blur="v$.userphone.$touch" />
                    <div v-if="v$.userphone.$errors.length"
                      class="col-12 invalid-feedback animated fadeIn bg-secondary text-light">
                      <i class="fa fa-triangle-exclamation text-warning mx-2"></i>
                      訂位人手機號碼不可留空
                    </div>
                  </div>
                  <div class="mb-4">
                    <label class="form-label" for="val-email">訂位人 Email </label>
                    <input type="text" id="val-email" class="form-control" :class="{
                      'is-invalid': v$.email.$errors.length, 'is-valid': !v$.email.$error && v$.email.$model
                    }" v-model="state.email" @blur="v$.email.$touch" />
                    <div v-if="v$.email.$errors.length"
                      class="invalid-feedback animated fadeIn bg-secondary text-light">
                      <i class="fa fa-triangle-exclamation text-warning mx-2"></i>
                      您填寫的 Email 格式有誤
                    </div>
                    <div v-if="!v$.email.$error && v$.email.$model"
                      class="valid-feedback animated fadeIn bg-secondary text-light">
                      <i class="far fa-envelope text-warning mx-2"></i>
                      訂位完成後，我們將發送訂位確認信到您的電子信箱，不再另寄簡訊通知。
                    </div>
                  </div>
                  <div class="mb-4">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" value="" id="block-form5-remember-me"
                        name="block-form5-remember-me" />
                      <label class="form-check-label" for="block-form5-remember-me">將訂位人資訊儲存在此瀏覽器</label>
                      <p class="form-check-label" for="block-form5-sub">
                        <small>(下次使用同一個瀏覽器訂位時即會自動帶入訂位人資訊，多人共用電腦請勿打勾。)</small>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </BaseBlock>
          </div>
        </div>
        <!-- 隱私條款，合法使用 -->
        <div class="block-rounded block row flex-md-row-reverse g-0">
          <BaseBlock>
            <div class="form-check">
              <input class="form-check-input" type="checkbox" value="" id="block-form5-privacy"
                name="block-form5-privacy" :class="{
                  'is-invalid': v$.terms.$errors.length,
                }" v-model="state.terms" @blur="v$.terms.$touch" />
              <label class="form-check-label" for="block-form5-privacy"> 我已閱讀並同意 <a
                  class="btn-block-option fs-sm text-warning " href="javascript:void(0)" data-bs-toggle="modal"
                  data-bs-target="#one-signup-terms">愛蔬網
                  隱私權條款</a></label>
              <div v-if="v$.terms.$errors.length"
                class="col-12 invalid-feedback animated fadeIn bg-secondary text-light">
                <i class="fa fa-triangle-exclamation text-warning mx-2"></i>
                請同意隱私權條款
              </div>
            </div>

            <!-- <div class="mb-4">
              <label class="form-label">Terms &amp; Conditions</label>
              <span class="text-danger">*</span>
              <div class="form-check" :class="{
                'is-invalid': v$.terms.$errors.length,
              }">
                <input class="form-check-input" type="checkbox" id="val-terms" :class="{
                  'is-invalid': v$.terms.$errors.length,
                }" v-model="state.terms" @blur="v$.terms.$touch" />
                <label class="form-check-label" for="val-terms">I agree</label>
              </div>
              <div v-if="v$.terms.$errors.length" class="invalid-feedback animated fadeIn">
                You must agree to the service terms!
              </div>
            </div> -->
          </BaseBlock>
        </div>
        <!-- 確認送出區塊 -->
        <div class="block-rounded block  flex-md-row-reverse">
          <div class="row justify-content-center" style="max-width:1140px ;  min-height: 50px;">
            <button id="book-now-action-button" class="content-center" type="submit" @click="sendData" style="
                    background-color: rgb(255, 133, 14);
                    border: none;
                    border-radius: 6px;
                    color: rgb(255, 255, 255);
                    text-align: center;
                    width: 100%;
                    padding-top: 0px;
                    padding-bottom: 0px;
                    outline: 0px;
                    cursor: pointer;
                ">
              <!-- <span>確認定位</span> -->
              <span class="h1">確認訂位</span>
            </button>
          </div>

        </div>

      </form>
      <!-- 返回上一步區塊 -->
      <div class="block-rounded block  flex-md-row-reverse">
        <div class="row justify-content-center" style="max-width:1140px ;  min-height: 50px;">
          <button @click="back()" id="book-now-goback-button" class="content-center" style="
                    background-color: rgb(255, 255, 255);
                    border: none;
                    border-radius: 6px;
                    color: rgb(0, 0, 0);
                    text-align: center;
                    width: 100%;
                    padding-top: 0px;
                    padding-bottom: 0px;
                    outline: 0px;
                    cursor: pointer;
                ">
            <!-- <span>確認定位</span> -->
            <span class="h6">回上一步</span>
          </button>
        </div>
      </div>
    </div>
  </div>

  <!-- Terms Modal -->
  <div class="modal fade" id="one-signup-terms" tabindex="-1" role="dialog" aria-labelledby="one-signup-terms"
    aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-popout" role="document">
      <div class="modal-content">
        <BaseBlock title="愛蔬網 &amp; 隱私權條款" transparent class="mb-0">
          <template #options>
            <button type="button" class="btn-block-option" data-bs-dismiss="modal" aria-label="Close">
              <i class="fa fa-fw fa-times"></i>
            </button>
          </template>

          <template #content>
            <div class="block-content">
              <h3>一、會員服務</h3>
              <p>
                一旦本公司完成並確認您的申請後，本公司將提供予您的會員服務內容有：部落格（Blog）、個人專頁（Profile）或其他未來可能新增之會員服務。會員服務僅依當時所提供之功能及狀態提供服務；本公司並保留新增、修改或取消會員服務內相關系統或功能之權利。
              </p>
              <p>
                會員服務之始期，係自使用者填妥申請資料並完成註冊程序後，本公司於完成相關系統設定、使會員服務達於可供使用之日開始。如您有購買或使用付費服務，您必須及時提供購買流程中所要求之資料、為適切地為您提供相關服務所必須的資料，您並應擔保您所提供的所有資料，都是正確且即時的資料，如果您所留存的資料事後有變更，您應立即更新您所留存的資料。
              </p>

              <p>
                因會員服務所提供之所有相關網域名稱、網路位址、功能以及其他一切因會員身分得使用之權益，均仍屬本公司或其他合法權利人所有，會員僅得於服務期間內依本服務條款之約定為使用，不得以任何方式將其會員權益轉讓、移轉、出租或出借予其他任何第三人。
              </p>

              <h3>
                二、資料空間與儲存 本公司保留新增或修改儲存空間大小之權利。
              </h3>
              <p>
                本系統不擔保您所上載的資料將被正常顯示、亦不擔保資料傳輸的正確性；如果您發現本系統有錯誤或疏失，請立即通知本公司。
              </p>
              <h3>三、帳號、密碼與安全性</h3>
              <p>
                在您使用會員服務以前，必須經過本公司所訂的註冊手續，在註冊過程中您必須填入完整、而且正確的資料。如因資料錯誤或未更新所造成的任何問題，您須自行負擔責任。
              </p>
              <p>
                對於您所取得的使用者名稱和密碼，您必須負妥善保管和保密的義務，並應為使用此使用者名稱和密碼登入後於本網站之一切行為負責。建議於使用完畢後確實登出，並請勿轉讓、出借予第三者使用，如果您的這個使用者名稱和密碼被其他人冒用或不當使用，本公司會儘速協助您解決，但不對您負有任何形式之賠償或補償之責任或義務。
              </p>
              <h3>四、關於愛蔬網會員服務的廣告</h3>
              <p>
                愛蔬網所有頁面之相關所有權及廣告版面經營權，包括但不限於網頁以及App應用程式中之原生廣告，以及其他本公司認為適當之位置或方式所出現之橫幅式廣告、跳躍式廣告或其他形式廣告與訊息等，均仍歸本公司所有，除另行取得本公司事前同意外，會員或第三人均不得以任何方式自行或以第三人名義銷售、經營、或對第三人提供此等網路廣告或類似業務。所有於愛食記中出現之廣告資訊，均由個別廣告主或贊助廠商自行負完全之責任，與本公司無關。
              </p>
              <h3>五、使用者的行為</h3>
              <p>您必須遵守相關法令規範，且您同意不從事以下的行為：</p>
              <h6>
                (1)刊載、傳輸、發送或儲存任何誹謗、詐欺、傷害、猥褻、色情、賭博、謾罵、不實或其他一切違反中華民國法令之檔案或資料。
              </h6>
              <h6>
                (2)刊載、傳輸、發送或儲存任何侵害他人智慧財產權或其他權益的資料。
              </h6>
              <h6>
                (3)
                未經同意擅自摘錄或使用會員服務內任何資料庫內容之全部或一部，包括但不限於使用爬蟲程式抓取愛食記頁面上之資料。
              </h6>
              <h6>
                (4)
                刊載、傳輸、發送、儲存病毒、或其他任何足以破壞或干擾電腦系統或資料的程式或訊息。
              </h6>
              <h6>
                (5) 破壞或干擾會員服務的系統運作或違反一般網路禮節之行為。
              </h6>
              <h6>
                (6)在未經授權下進入會員服務系統或冒用他人帳號或偽造他人辨識資料。
              </h6>
              <h6>(7) 任何竊取會員服務之會員帳號、密碼或存取權限之行為。</h6>
              <h6>(8)任何妨礙或干擾其他使用者使用會員服務之行為。</h6>
              <h6>(9)其他不符合會員服務所提供的使用目的之行為。</h6>
              <h6>
                (10)破壞及干擾本服務所提供之各項
                資料、活動或功能，或以任何方式侵入、試圖侵入、破壞本服務之任何系統，或藉由本服務為任何侵害或破壞行為。
              </h6>
              <h6>(11) 從事不法交易行 為或刊載引人犯罪之訊息或買賣行為。</h6>
              <p>
                會員服務內之各類文字、圖檔、圖片及其他著作或資料，其著作權或其他相關智慧財產權均仍屬於合法權利人所有，由合法權利人依照當時所定之授權條件及範圍授權會員使用，但僅限於您個人於愛食記網站上使用，未經事前授權您不可以將這些文字、圖檔、圖片或其他著作或資料為重製、公開口述、公開播送、公開上映、公開演出、公開傳輸、改作、散布、發行、公開發表、銷售、轉讓、出租、出借、轉授權、隨書附贈或其他任何形式的使用，亦不得上載於其他任何網站、或以其他方式提供予其他人使用。
                本公司不保證針對會員所傳送、張貼或發表之內容做任何形式或實質之審查，如果任何文字、圖檔、圖片或其他著作或資料發生權利歸屬或其他合法性爭議，本公司有權隨時不經通知，直接停止提供會員服務、或直接刪除該文字、圖檔、圖片或其他著作或資料、或通知會員停止使用會員服務。
                如您認為愛食記中之任何網頁內容或其他會員使用愛食記服務已侵害您的著作權，請您至愛食記客服中心與客服人員聯繫，客服人員將會儘速為您處理。
                若有發現任何色情、侮辱、誹謗等不合規定的圖片，本公司將立即刪除，並砍除帳號，不另通知。
              </p>
              <h3>六、責任限制</h3>
              <p>
                對於本公司所提供之各項會員服務，均僅依該服務當時之功能及現況提供使用；對於使用者之特定要求或需求，包括但不限於速度、安全性、可靠性、完整性、正確性及不會斷線和出錯等，本公司不負任何形式或內容之擔保或保證責任。
              </p>
              <p>
                對於您所上傳的圖片或文字等資料，您應自行判斷是否適宜公開傳送至網站上，本公司會盡最大努力保證您資料的安全性，但無法保證任檔案或資料於傳送過程均係可靠且正確無誤，亦不保證所儲存的檔案或資料之安全性、可靠性、完整性、正確性及不會斷線和出錯等。
              </p>
              <p>
                對於您於本服務中所下載之電子郵件、檔案及其他資料，您應該自行考量其風險，如因下載而造成您電腦系統的損壞或電腦中資料的遺失，本公司不負任何擔保責任。
              </p>
              <p>
                在本服務中，由您及其他會員上載、公開傳輸、公開發表或私下傳送的資訊、資料、文字、軟體、音樂、音訊、照片、圖形、視訊、信息或其他資料（以下簡稱「使用者內容」），均由「使用者內容」的提供者自負責任。本服務無法控制經由本服務而張貼之「使用者內容」，因此不保證其正確性、完整性或品質。您了解使用本服務時，可能會接觸到令人不快或厭惡的「使用者內容」。在任何情況下，愛食記均不為任何「使用者內容」負責，包含但不限於任何錯誤或遺漏，以及經由本服務張貼、發送電子郵件或傳送而衍生之任何損失或損害。
              </p>
              <h3>七、服務暫停或中斷</h3>
              <p>
                會員服務系統或功能『例行性』之維護、改置或變動所發生之服務暫停或中斷，本公司將於該暫停或中斷前以電子郵件、公告或其他適當之方式告知會員，您同意本公司不須因此負損害賠償責任。
              </p>
              <h5>
                在下列情形，本公司將暫停或中斷本服務之全部或一部，且對使用者任何直接或間接之損害，均不負任何責任：
              </h5>
              <h6>
                (1) 對本服務相關軟硬體設備進行搬遷、更換、升級、保養或維修時
              </h6>
              <h6>(2) 使用者有任何違反政府法令或本服務條款情形</h6>
              <h6>(3) 天災或其他不可抗力所致之服務停止或中斷</h6>
              <h6>(4) 任何不可歸責於本公司之事由所致之服務停止或中斷。</h6>
              <h3>八、終止服務</h3>
              <p>
                基於公司的運作，會員服務有可能停止提供服務之全部或一部，使用者不可以因此而要求任何賠償或補償。
              </p>
              <p>
                如果您違反了本服務條款，本公司保留隨時暫時停止提供服務、或終止提供服務之權利，您不可以因此而要求任何賠償或補償。
              </p>
              <p>
                您同意如果您在會員服務上所刊載、傳輸、發送或儲存的檔案或資料，有任何違反法令、違反本服務條款、或有侵害第三人權益之情事者，本公司得隨時得不經通知直接加以移動、刪除或停止服務提供之權利，且不負損害賠償責任。若本公司因此受到任何損害，您應對本公司負損害賠償之責。
              </p>
              <p>
                會員服務系統會自動偵測沒有使用的會員帳號，如連續超過一年均未使用，系統將自動將您於該帳號內之所有檔案、資料及使用設定資料檔全數刪除且不予另行備份，並將您的會員帳號暫停使用。上述有無使用之紀錄，均以本公司會員服務系統內所留存之紀錄為準。
              </p>
              <h3>九、準據法及管轄權</h3>
              <p>本服務條款解釋、補充及適用均以中華民國法令為準據法。</p>
              <p>
                因本服務條款所發生之訴訟，以台灣台北地方法院為第一審管轄法院。
              </p>
            </div>
            <div class="block-content block-content-full text-end bg-body">
              <button type="button" class="btn btn-sm btn-alt-secondary me-1" data-bs-dismiss="modal">
                我同意且關閉
              </button>
              <button type="button" class="btn btn-sm btn-primary" data-bs-dismiss="modal">
                我同意
              </button>
            </div>
          </template>
        </BaseBlock>
      </div>
    </div>
  </div>
  <!-- END Terms Modal -->


  <!-- Footer -->
  <footer id="page-footer" class="bg-body-light">
    <div class="content py-5">
      <div class="row fs-sm fw-medium">
        <div class="col-sm-6 order-sm-2 py-1 text-center text-sm-end"> 本網站僅作為 <i class="fa fa-heart text-danger"></i>
          <a class="fw-semibold" href="https://www.ispan.com.tw/" target="_blank">資展國際</a>專題使用
        </div>
        <div class="col-sm-6 order-sm-1 py-1 text-center text-sm-start"><a class="fw-semibold"
            href="https://github.com/Ryan-focus/springboot-vegetarian"> EEIT45 - 跨域JAVA班 - 第一組 </a> © {{
                store.app.copyright
            }}</div>
      </div>
    </div>
  </footer>
  <!-- END Footer -->
</template>
