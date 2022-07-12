<script setup>
import { ref, reactive, onMounted } from "vue";

// FullCalendar, for more info and examples you can check out https://fullcalendar.io/
import "@fullcalendar/core/vdom"; // 解決 Vite 的問題
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin, { Draggable } from "@fullcalendar/interaction";
import resourceTimelinePlugin from '@fullcalendar/resource-timeline'
import listPlugin from "@fullcalendar/list";

// Helper variables輔助變量
const fullCalendar = ref(null);
const inputAddEvent = ref(null);

// 日曆選項、變量和事件
const calendarOptions = reactive({
  events: [],
  plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin, listPlugin, resourceTimelinePlugin],
  firstDay: 1,
  editable: true,
  aspectRatio: 2,
  droppable: true,
  headerToolbar: {
    left: "prev,today,next",
    center: "title",
    right: "timeGridWeek,timeGridDay,listWeek",
  },
  buttonText: {
    today: '今日',
    month: '月',
    week: '週',
    day: '日',
    list: '表單'
  },
  buttonIcons: {
    prev: 'chevron-left',
    next: 'chevron-right',
    prevYear: 'chevrons-left', // double chevron
    nextYear: 'chevrons-right' // double chevron
  },
  dateClick: handleDateClick,
  eventDrop: handleEventDrop,
  eventReceive: handleExternalDrop,
  timeZone: 'local',
  locale: 'zh-TW',
});

const calendarNewEvent = ref(null);
const calendarNewEvents = reactive([
  {
    title: "Codename X",
    color: "info",
  },
  {
    title: "Weekend Adventure",
    color: "success",
  },
  {
    title: "Project Mars",
    color: "info",
  },
  {
    title: "Meeting",
    color: "warning",
  },
  {
    title: "Walk the dog",
    color: "success",
  },
  {
    title: "Al schedule",
    color: "info",
  },
  {
    title: "Cinema",
    color: "success",
  },
  {
    title: "Project X",
    color: "danger",
  },
  {
    title: "Skype Meeting",
    color: "warning",
  },
]);

// 初始化日曆
// 獲取當前年月日
let date = new Date();

let y = date.getFullYear();
let m = date.getMonth();
let d = date.getDate();

// 使用預定義的事件填充日曆
calendarOptions.events = [
  {
    title: "Gaming Day",
    start: new Date(y, m, 1),
    allDay: true,
  },
  {
    title: "Skype Meeting",
    start: new Date(y, m, 3),
  },
  {
    title: "Project X",
    start: new Date(y, m, 9),
    end: new Date(y, m, 12),
    allDay: true,
    color: "#e04f1a",
  },
  {
    title: "Work",
    start: new Date(y, m, 17),
    end: new Date(y, m, 19),
    allDay: true,
    color: "#82b54b",
  },
  {
    id: 999,
    title: "Hiking (repeated)",
    start: new Date(y, m, d - 1, 15, 0),
  },
  {
    id: 999,
    title: "Hiking (repeated)",
    start: new Date(y, m, d + 3, 15, 0),
  },
  {
    title: "Landing Template",
    start: new Date(y, m, d - 3),
    end: new Date(y, m, d - 3),
    allDay: true,
    color: "#ffb119",
  },
  {
    title: "Lunch",
    start: new Date(y, m, d + 7, 15, 0),
    color: "#82b54b",
  },
  {
    title: "Coding",
    start: new Date(y, m, d, 8, 0),
    end: new Date(y, m, d, 14, 0),
  },
  {
    title: "Trip",
    start: new Date(y, m, 25),
    end: new Date(y, m, 27),
    allDay: true,
    color: "#ffb119",
  },
  {
    title: "Reading",
    start: new Date(y, m, d + 8, 20, 0),
    end: new Date(y, m, d + 8, 22, 0),
  },
  {
    title: "Follow us on Twitter",
    start: new Date(y, m, 22),
    allDay: true,
    url: "http://twitter.com/pixelcave",
    color: "#3c90df",
  },
];

// 單擊日期將新事件數據添加到日曆
function handleDateClick(arg) {
  if (confirm("Would you like to add an event to " + arg.dateStr + " ?")) {
    calendarOptions.events.push({
      title: "New Event",
      start: arg.date,
      allDay: arg.allDay,
    });
  }
}

// 將現有事件放到另一個日期時
function handleEventDrop(eventDropInfo) {
  window.console.log(eventDropInfo);
}

// 刪除外部事件並添加到日曆時
function handleExternalDrop(info) {
  window.console.log(info);
}

// 添加事件表單提交
function addNewEvent() {
  if (calendarNewEvent.value) {
    // Add the event to the draggable list
    calendarNewEvents.unshift({
      title: calendarNewEvent.value,
      color: "info",
    });

    // Reset and focus the input
    calendarNewEvent.value = "";
    inputAddEvent.value.focus();
  }
}

// Init draggable calendar events on content load
onMounted(() => {
  new Draggable(document.getElementById("js-events"), {
    itemSelector: ".js-event",
    eventData(eventEl) {
      return {
        title: eventEl.textContent,
        backgroundColor: getComputedStyle(eventEl).color,
        borderColor: getComputedStyle(eventEl).color,
      };
    },
  });
});
</script>

<style lang="scss">
// FullCalendar custom overrides
@import "@/assets/scss/vendor/fullcalendar";
</style>

<template>
  <!-- Hero -->
  <BasePageHeading title="訂位系統" subtitle="使用Calendar API">
    <template #extra>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb breadcrumb-alt">
          <li class="breadcrumb-item">
            <a class="link-fx" href="/#/business/backend/dashboard"> <i class="si si-speedometer" /> 總覽資訊</a>
          </li>
          <li class="breadcrumb-item" aria-current="page"><i class="far fa-calendar-check" /> 訂位</li>
        </ol>
      </nav>
    </template>
  </BasePageHeading>
  <!-- END Hero -->

  <!-- Page Content -->
  <div class="content">
    <!-- Calendar -->
    <BaseBlock>
      <div class="row items-push">

        <div class="col-md-4 col-lg-5 col-xl-3">
          <!-- Add Event Form -->
          <form class="push" @submit.prevent="addNewEvent">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="新增事件.." v-model="calendarNewEvent"
                ref="inputAddEvent" />
              <span class="input-group-text">
                <i class="fa fa-fw fa-plus-circle"></i>
              </span>
            </div>
          </form>
          <!-- END Add Event Form -->

          <!-- Event List -->
          <ul id="js-events" class="list list-events">
            <li v-for="(event, index) in calendarNewEvents" :key="`events-${index}`">
              <div class="js-event p-2 fs-sm fw-medium rounded" :class="`bg-${event.color}-light text-${event.color}`">
                {{ event.title }}
              </div>
            </li>
          </ul>
          <div class="text-center">
            <p class="fs-sm text-muted">
              <i class="fa fa-arrows-alt"></i> 拖放事件到日曆
            </p>
          </div>
          <!-- END Event List -->
        </div>
        <div class="col-md-8 col-lg-7 col-xl-9">
          <!-- Calendar Container -->
          <FullCalendar ref="fullCalendar" :options="calendarOptions"></FullCalendar>
        </div>

      </div>
    </BaseBlock>
    <!-- END Calendar -->
  </div>
  <!-- END Page Content -->
</template>
