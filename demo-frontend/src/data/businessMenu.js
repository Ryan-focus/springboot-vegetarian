/*
 * Main and demo navigation arrays
 *
 * 'to' attribute points to the route name, not the path url
 */

export default {
  main: [
    {
      name: "總覽資訊",
      to: "business-backend-dashboard",
      icon: "si si-speedometer",
    },
    {
      name: "後台管理系統",
      heading: true,
    },
    {
      name: "訂位",
      to: "business-backend-reserve",
      icon: "far fa-calendar-check",
    },
    {
      name: "訂金管理",
      to: "business-backend-dashboard",
      icon: "fa fa-money-bill-1-wave",
    },
    {
      name: "營收報表",
      to: "business-backend-dashboard",
      icon: "far fa-rectangle-list",
    },
    {
      name: "搜尋",
      to: "business-backend-dashboard",
      icon: "si si-magnifier",
    },
    {
      name: "設定",
      to: "business-backend-dashboard",
      icon: "fa fa-user-gear",
    },
  ],
};
